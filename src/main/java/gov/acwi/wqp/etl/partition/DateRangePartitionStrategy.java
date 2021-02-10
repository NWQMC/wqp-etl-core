package gov.acwi.wqp.etl.partition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * A strategy for partitioning based on date ranges.
 *
 * This strategy creates sets of partitions in these groups:
 * - From antiquity to the startDate
 * - From the startDate to oneYearBreak in five year partitions
 * - From oneYearBreak to quarterBreak in one year partitions
 * - From quarterBreak to endDate (and beyond) in quarter (three month) partitions.
 *
 * The last (most recent date) partition will use the MAXVALUE keyword to include all dates beyond endDate (and into the future)
 * The first (earliest date) partition will use the MINVALUE keyword to include all dates before the startDate (and into the past)
 */
public class DateRangePartitionStrategy implements RangePartitionStrategy<PgDateRangePart> {

	private static final Logger LOG = LoggerFactory.getLogger(DateRangePartitionStrategy.class);

	//User configuration as received
	private final Config userConfig;

	//Calculated configuration, from userConfig, to ensure that configuration always works, even if weird or non-ideal
	private Config runtimeConfig;



	/**
	 * This minimal constructor creates 'breaks' based on the state of the nwis result table at the beginning of 2021.
	 * The defaults will work (ie not cause errors) but will not be ideal for other data sources (stewards especially).
	 *
	 * The dates auto-adjust based on the passed runTime, but the default parameters feed into this strategy may not
	 * fit your data.  If that is the case, use the more detailed constructor, or write your own strategy.
	 *
	 * @param runTime
	 * @param baseTableName
	 */
	public DateRangePartitionStrategy(LocalDateTime runTime, String baseTableName) {
		this(runTime, baseTableName, null, null, null, null);
	}

	/**
	 * Creates a list of Range objects representing the partition dates for the Result table.
	 *
	 * All values can be null except the runTime.  See calcRuntimeConfig for descriptions of how missing values are
	 * calculated.
	 *
	 * @param runTime Nominally the run date and time of this job.  It is used to build unique table names.
	 *                For testing, it does not need to be the real dateTime - it can be some arbitrary time to create
	 *                repeatable table names.  See getTableNamePrefix
	 * @param baseTableName Nominally the parent table name.  It will be merged together with the runTime to create
	 *                  a unique prefix for all table names.  See getTableNamePrefix.
	 * @param startDate This date and earlier are thrown into a single partition using MINVALUE.  Required.
	 *                  Must be Jan 1st of some year ending in 5 or 0.
	 *                  Dates between startDate and oneYearBreak are thrown into five year bins.
	 * @param oneYearBreak This date and after are placed in one year bins.  Must be Jan 1 of some year ending in 5 or 0.  Required.
	 * @param quarterBreak This date and after are placed in quarters (3 months).  Must be Jan 1 of some year.  Required.
	 * @param endDate This date and after are placed in a single bin using MAXVALUE.  Required.
	 *                Its OK if the quarters don't come out exactly at the end date, i.e., its ok for the endDate to
	 *                just be today's date and not the first of the month.  In that case, the quarters may extend a bit
	 *                into the future and there will still be an additional bin from the last created quarter bin and
	 *                into the future.
	 * @return
	 */
	public DateRangePartitionStrategy(LocalDateTime runTime, String baseTableName, LocalDate startDate,
	                               LocalDate oneYearBreak, LocalDate quarterBreak, LocalDate endDate) {

		userConfig = new Config(runTime, baseTableName, startDate, oneYearBreak, quarterBreak, endDate);

		runtimeConfig = calcRuntimeConfig(userConfig);

	}

	/**
	 * Convert the user supplied parameters into 'no fail' runtime params.
	 * We should always be able to run, even if the partition configuration doesn't make sense or is non-optimal.
	 *
	 * Defaults descriptions:
	 * * If endDate is null, set to runTime.
	 * * If startDate is null, set to be 65 years before endDate, adjusted to be jan 1 of a year divisible by 5 (going back in time)
	 * * If quarterBreak is null, after the endDate or before the startDate, set to be Jan 1 of the year before endDate.
	 * * If yearBreak is null, set to be 1/3 the way from startDate to quarterBreak.  Round down to be Jan 1 of a year divisible by five.
	 * * If yearBreak is before the startDate, set to be the same as the startDate (there will be no 5 year partitions).
	 * * If yearBreak is after the quarterBreak, set to be the same as the quarterBreak (there will be no quarter partitions).
	 */
	protected Config calcRuntimeConfig(Config config) {

		//
		//Do some cleanup to be as 'no fail' as possible for config params

		//Adjust all non-null dates to be Jan 1st.  Start date and oneYearBreak must also be years divisible by 5.
		LocalDate start = (config.startDate != null)?findFiveYearDate(config.startDate):null;
		LocalDate yearBreak = (config.oneYearBreak != null)?findFiveYearDate(config.oneYearBreak):null;
		LocalDate qtrBreak = (config.quarterBreak != null)?janOne(config.quarterBreak):null;

		//If no end date, default to the runTime (OK if its not jan 1)
		LocalDate end = (config.endDate != null)?config.endDate:config.runTime.toLocalDate();

		//The start date is always adjusted to jan 1st of a year divisible by 5.
		//If no startDate, the start date is set to 65 years before the endDate.
		//If the start date isn't at least 5 years before Jan 1 of the endDate, adjust at least a full year back.
		if (start == null) {
			start = findFiveYearDate(end.minusYears(65));
		} else if (start.isAfter(janOne(end).minusYears(5))) {
			start = findFiveYearDate(end.minusYears(1));
			LOG.warn("The startDate '{}' is within 5 year of the endDate '{}'.  Adjusted startDate to '{}'",
					config.startDate, end, start);
		}

		//If no quarterBreak or it comes after the endDate or before the startDate, make it Jan 1 of the year before the endDate
		if (qtrBreak == null) {
			qtrBreak = janOne(end.minusYears(1));
		} else if (qtrBreak.isAfter(end)) {
			qtrBreak = janOne(end.minusYears(1));
			LOG.warn("The quarterBreak '{}' is after the endDate '{}'.  Adjusted quarterBreak to '{}'",
					config.quarterBreak, end, qtrBreak);
		} else if (qtrBreak.isBefore(start)) {
			qtrBreak = janOne(end.minusYears(1));
			LOG.warn("The quarterBreak '{}' is before the startDate '{}'.  Adjusted quarterBreak to '{}'",
					config.quarterBreak, start, qtrBreak);
		}

		//The oneYearBreak is always adjusted to jan 1st of a year divisible by 5.
		//If null, set it 1/3rd the way from startDate to quarterBreak (ie, the first third of that period will be 5 year partitions)
		//If before  startDate offset 5 years.
		//If after quarterBreak, round down to an even 5 year before
		if (yearBreak == null) {
			int yr = start.getYear() + ((qtrBreak.getYear() - start.getYear()) / 3);
			yearBreak = findFiveYearDate(LocalDate.of(yr, 1, 1));
		} else if (yearBreak.isAfter(qtrBreak)) {
			yearBreak = findFiveYearDate(qtrBreak);
			LOG.warn("The oneYearBreak '{}' is after the quarterBreak '{}'.  Adjusted oneYearBreak to '{}'",
					config.oneYearBreak, qtrBreak, yearBreak);
		} else if (yearBreak.isBefore(start)) {
			yearBreak = start;
			LOG.warn("The oneYearBreak '{}' is before the startDate '{}'.  Adjusted oneYearBreak to '{}' (no 5yr partitions will be created)",
					config.oneYearBreak, start, yearBreak);
		}

		return new Config(config.runTime, config.baseTableName, start, yearBreak, qtrBreak, end);
	}

	/**
	 * Finds the first date that is divisible by 5 years (ie years ends in 0 or 5) that is the passed date or earlier.
	 * The date is adjusted to be on Jan 1.
	 * @param ref
	 * @return A Jan 1 date of a year that is divisible by 5.
	 */
	protected LocalDate findFiveYearDate(LocalDate ref) {
		int year = ref.getYear();

		while (year % 5 != 0) { year--;	}

		return LocalDate.of(year, 1, 1);
	}

	/**
	 * Return Jan 1 of the same year.
	 * @param ref
	 * @return
	 */
	protected LocalDate janOne(LocalDate ref) {
		return LocalDate.of(ref.getYear(), 1, 1);
	}

	/**
	 * Returns the prefix to add to each partition table name, including a separator.
	 * The table name prefix is formed as:
	 * [baseTableName]_[runTime formatted as YYYYMMDDHH]_  (Year, Month, Day and Hour, no separators)
	 * baseTableName should already include the datasource.  For instance, if 'result_nwis' is the baseName and
	 * '2000-01-01T10:50:00' is the runTime, the prefix for all table names would be:
	 * 'result_nwis_2001010110_'
	 * @return
	 */
	String getTableNamePrefix() {
		return runtimeConfig.baseTableName + "_" + runtimeConfig.runTime.format(DateTimeFormatter.ofPattern("YYYYMMDDHH")) + "_";
	}

	@Override
	public List<PgDateRangePart> getPartitions() {

		LOG.info("{} is using these parameters to determine partitions for the base table name '{}':\n" +
				         "\t\tstartDate (calculated) : '{}' : user spec'ed value: '{}'\n" +
				         "\t\toneYearBreak (calculated) : '{}' : user spec'ed value: '{}'\n" +
				         "\t\tquarterBreak (calculated) : '{}' : user spec'ed value: '{}'\n" +
				         "\t\tendDate (calculated) : '{}' : user spec'ed value: '{}'\n",
				this.getClass().getSimpleName(), runtimeConfig.baseTableName,
				runtimeConfig.startDate, userConfig.startDate,
				runtimeConfig.oneYearBreak, userConfig.oneYearBreak,
				runtimeConfig.quarterBreak, userConfig.quarterBreak,
				runtimeConfig.endDate, userConfig.endDate);

		ArrayList<PgDateRangePart> list = new ArrayList();


		LocalDate lastHandledDate = runtimeConfig.startDate;

		PgDateRangePart oldest = new PgDateRangePart(getTableNamePrefix(), null, runtimeConfig.startDate);
		list.add(oldest);

		//Create 5 year partitions for the time between the last date handled and oneYearBreak
		while (lastHandledDate.isBefore(runtimeConfig.oneYearBreak)) {
			PgDateRangePart fiveYear = new PgDateRangePart(getTableNamePrefix(), lastHandledDate, lastHandledDate.plusYears(5));

			list.add(fiveYear);
			lastHandledDate = lastHandledDate.plusYears(5);
		}

		//Create 1 year partitions for the time between the last date handled and quarterBreak
		while (lastHandledDate.isBefore(runtimeConfig.quarterBreak)) {
			PgDateRangePart oneYear = new PgDateRangePart(getTableNamePrefix(), lastHandledDate, lastHandledDate.plusYears(1));
			lastHandledDate = lastHandledDate.plusYears(1);
			list.add(oneYear);
		}

		//Create quarter (3 month) partitions for the time between the last date handled and endDate
		while (lastHandledDate.isBefore(runtimeConfig.endDate)) {
			PgDateRangePart oneQuater = new PgDateRangePart(getTableNamePrefix(), lastHandledDate, lastHandledDate.plusMonths(3));
			lastHandledDate = lastHandledDate.plusMonths(3);
			list.add(oneQuater);
		}

		//The last bin created either ends on the endDate or goes beyond it.
		//Modify it to go into the future.
		list.get(list.size() - 1).setRangeEnd(null);

		debugLogTableList(list);

		return list;
	}

	protected void debugLogTableList(List<PgDateRangePart> list) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("{} determined these partitions for base table name '{}'", this.getClass().getSimpleName(), runtimeConfig.baseTableName);
			for (PgDateRangePart p : list) {
				LOG.debug("Partition from {} to {} in partition table named '{}'", p.getSqlFormatRangeStart(), p.getSqlFormatRangeEnd(), p.getTableName());
			}
		}
	}

	/**
	 * Bean class to represent the config state.
	 * There are two instances of this:  One spec'ed by the user (or system config) and another calculated one that
	 * is 'smoothed over' to ensure we have a no-fail configuration.
	 */
	public static class Config {
		LocalDateTime runTime; //Time of the run (used for unique table names and to build start/end times if not spec'ed
		String baseTableName; //Used to build table names
		LocalDate startDate;  //Always jan 1 of a year divisible by 5, before or equal to all other dates & 5 year before endDate
		LocalDate oneYearBreak; //Always jan 1 of a year divisible by 5, before or equal to quarterBreak.
		LocalDate quarterBreak; //Always jan 1 of a year, before or equal to endDate.
		LocalDate endDate; //Any date

		public Config(final LocalDateTime runTime, final String baseTableName, final LocalDate startDate,
		              final LocalDate oneYearBreak, final LocalDate quarterBreak, final LocalDate endDate) {
			this.runTime = runTime;
			this.baseTableName = baseTableName;
			this.startDate = startDate;
			this.oneYearBreak = oneYearBreak;
			this.quarterBreak = quarterBreak;
			this.endDate = endDate;
		}
	}
}
