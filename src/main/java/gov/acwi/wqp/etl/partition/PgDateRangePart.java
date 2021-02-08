package gov.acwi.wqp.etl.partition;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PgDateRangePart implements RangePartition<LocalDate> {

	//Used for date formatting in the range specification
	private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_DATE;

	//Used for date formatting in the name of the table (year and month only)
	private static final DateTimeFormatter TABLE_NAME_FORMATTER = DateTimeFormatter.ofPattern("YYYY_MM");

	public static final String MIN_VALUE = "MINVALUE";
	public static final String MAX_VALUE = "MAXVALUE";

	private String baseName;
	private LocalDate start;
	private LocalDate end;

	public PgDateRangePart(String baseTableName, LocalDate rangeStart, LocalDate rangeEnd) {
		baseName = baseTableName;
		start = rangeStart;
		end = rangeEnd;
	}

	@Override
	public LocalDate getRangeStart() {
		return start;
	}


	@Override
	public void setRangeStart(final LocalDate rangeStart) {
		start = rangeStart;
	}

	@Override
	public LocalDate getRangeEnd() {
		return end;
	}


	@Override
	public void setRangeEnd(final LocalDate rangeEnd) {
		end = rangeEnd;
	}

	@Override
	public String getTableName() {
		return baseName + formatForRangeTableName(start, MIN_VALUE) + "_to_" + formatForRangeTableName(end, MAX_VALUE);
	}

	@Override
	public String getSqlFormatRangeStart() {
		return formatForRangeValue(start, MIN_VALUE);
	}

	@Override
	public String getSqlFormatRangeEnd() {
		return formatForRangeValue(end, MAX_VALUE);
	}

	/**
	 * PostGres treats MINVALUE and MAXVALUE as keywords that are not quoted, while date strings must be ISO format
	 * and quoted.  This method returns the value as needed for the db.
	 *
	 * @param date The date to format, including adding single quotes for use in the db.
	 * @param defaultStr A default string to return if the date is null (i.e., it is a min or max value)
	 */
	private String formatForRangeValue(LocalDate date, String defaultStr) {
		if (date != null) {
			return "'" + date.format(ISO_FORMATTER) + "'";
		} else {
			return defaultStr;
		}
	}

	/**
	 * Format a to be used as part of the table name suffix, which is based on the start and end dates of the table.
	 * A start date might be formatted as '1997_01' of 'MINVALUE' if null.
	 *
	 * @param date The date to format
	 * @param defaultStr A default string if the date is null (i.e., it is a min or max value)
	 */
	private String formatForRangeTableName(LocalDate date, String defaultStr) {
		if (date != null) {
			return date.format(TABLE_NAME_FORMATTER);
		} else {
			return defaultStr;
		}
	}
}
