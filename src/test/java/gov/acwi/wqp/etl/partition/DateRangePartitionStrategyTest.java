package gov.acwi.wqp.etl.partition;

import static gov.acwi.wqp.etl.partition.DateRangePartitionStrategy.Config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DateRangePartitionStrategyTest {

	private static final String tblName = "MyTab";
	private static final LocalDate start = LocalDate.of(2005, 1, 1);
	private static final LocalDate yearBreak = LocalDate.of(2015, 1, 1);
	private static final LocalDate qtrBreak = LocalDate.of(2018, 1, 1);
	private static final LocalDate end = LocalDate.of(2019, 1, 1);

	private static final LocalDateTime A_RUN_TIME = LocalDateTime.of(2021, 01, 29, 10, 20, 30);

	Config config = null;
	DateRangePartitionStrategy strategy = null; //uses the config state

	@BeforeEach
	public void init() {

		config = new Config(A_RUN_TIME, tblName, start, yearBreak, qtrBreak, end);

		strategy = new DateRangePartitionStrategy(A_RUN_TIME, tblName, start, yearBreak, qtrBreak, end);
	}


	@Test
	void findFiveYearStartTest() {

		assertEquals(LocalDate.of(2000, 1, 1),
				strategy.findFiveYearDate(LocalDate.of(2001, 2, 2)));

		assertEquals(LocalDate.of(2000, 1, 1),
				strategy.findFiveYearDate(LocalDate.of(2004, 12, 25)));

		assertEquals(LocalDate.of(2000, 1, 1),
				strategy.findFiveYearDate(LocalDate.of(2000, 1, 1)));

		assertEquals(LocalDate.of(1995, 1, 1),
				strategy.findFiveYearDate(LocalDate.of(1999, 12, 31)));
	}

	@Test
	void tableNamesAreCorrectFormat() {
		assertTrue(strategy.getPartitions().get(0).getTableName().matches("MyTab_2021012910_.*"));

		//Try a different month to ensure we have day of month and not day of year
		strategy = new DateRangePartitionStrategy(LocalDateTime.of(2021, 02, 1, 13, 20, 30), tblName, start, yearBreak, qtrBreak, end);

		assertTrue(strategy.getPartitions().get(0).getTableName().matches("MyTab_2021020113_.*"));
	}

	@Test
	void calcRuntimeConfigStartDateShouldBeAdjustedToFirstDayOfYear() {

		config.startDate = LocalDate.of(2005, 2, 2);

		DateRangePartitionStrategy.Config calc = strategy.calcRuntimeConfig(config);

		assertEquals(LocalDate.of(2005, 1, 1), calc.startDate); //adjusted
		assertEquals(config.oneYearBreak, calc.oneYearBreak);
		assertEquals(config.quarterBreak, calc.quarterBreak);
		assertEquals(config.endDate, calc.endDate);
	}

	@Test
	void calcRuntimeConfigStartDateShouldBeAdjustedToAYearDivisibleByFive() {

		config.startDate = LocalDate.of(2006, 2, 2);

		DateRangePartitionStrategy.Config calc = strategy.calcRuntimeConfig(config);

		assertEquals(LocalDate.of(2005, 1, 1), calc.startDate); //adjusted
		assertEquals(config.oneYearBreak, calc.oneYearBreak);
		assertEquals(config.quarterBreak, calc.quarterBreak);
		assertEquals(config.endDate, calc.endDate);
	}

	@Test
	void calcRuntimeConfigYearBreakShouldBeAdjustedToTheFirstDayOfTheYear() {

		config.oneYearBreak = LocalDate.of(2015, 2, 2);

		DateRangePartitionStrategy.Config calc = strategy.calcRuntimeConfig(config);

		assertEquals(config.startDate, calc.startDate);
		assertEquals(LocalDate.of(2015, 1, 1), calc.oneYearBreak); //adjusted
		assertEquals(config.quarterBreak, calc.quarterBreak);
		assertEquals(config.endDate, calc.endDate);
	}

	@Test
	void calcRuntimeConfigYearBreakShouldBeAdjustedToAYearDivisibleByFive() {

		config.oneYearBreak = LocalDate.of(2016, 2, 2);

		DateRangePartitionStrategy.Config calc = strategy.calcRuntimeConfig(config);

		assertEquals(config.startDate, calc.startDate);
		assertEquals(LocalDate.of(2015, 1, 1), calc.oneYearBreak); //adjusted
		assertEquals(config.quarterBreak, calc.quarterBreak);
		assertEquals(config.endDate, calc.endDate);
	}

	@Test
	void calcRuntimeConfigQuarterBreakShouldBeAdjustedToJanOne() {

		config.quarterBreak = LocalDate.of(2018, 2, 2);

		DateRangePartitionStrategy.Config calc = strategy.calcRuntimeConfig(config);

		assertEquals(config.startDate, calc.startDate);
		assertEquals(config.oneYearBreak, calc.oneYearBreak);
		assertEquals(LocalDate.of(2018, 1, 1), calc.quarterBreak); //adjusted
		assertEquals(config.endDate, calc.endDate);
	}

	@Test
	void calcRuntimeConfigJumbledDates1() {

		//Dates are way out of order
		config.startDate = LocalDate.of(2020, 2, 1);
		config.oneYearBreak = LocalDate.of(1994, 9, 1);
		config.quarterBreak = LocalDate.of(1800, 6, 1);
		config.endDate = LocalDate.of(1995, 2, 2);

		DateRangePartitionStrategy.Config calc = strategy.calcRuntimeConfig(config);

		assertEquals(LocalDate.of(1990, 1, 1), calc.startDate);
		assertEquals(LocalDate.of(1990, 1, 1), calc.oneYearBreak);
		assertEquals(LocalDate.of(1994, 1, 1), calc.quarterBreak);
		assertEquals(config.endDate, calc.endDate);
	}

	@Test
	void calcRuntimeConfigJumbledDates2() {

		//Dates are way out of order
		config.startDate = LocalDate.of(2020, 2, 1);
		config.oneYearBreak = LocalDate.of(1801, 9, 1);
		config.quarterBreak = LocalDate.of(1800, 6, 1);
		config.endDate = LocalDate.of(1995, 2, 2);

		DateRangePartitionStrategy.Config calc = strategy.calcRuntimeConfig(config);

		assertEquals(LocalDate.of(1990, 1, 1), calc.startDate);
		assertEquals(LocalDate.of(1990, 1, 1), calc.oneYearBreak);
		assertEquals(LocalDate.of(1994, 1, 1), calc.quarterBreak);
		assertEquals(config.endDate, calc.endDate);
	}

	@Test
	void calcRuntimeConfigJumbledDatesAndNulls1() {

		//Dates are way out of order
		config.startDate = LocalDate.of(2020, 2, 1);
		config.oneYearBreak = null;
		config.quarterBreak = LocalDate.of(1800, 6, 1);
		config.endDate = LocalDate.of(1995, 2, 2);

		DateRangePartitionStrategy.Config calc = strategy.calcRuntimeConfig(config);

		assertEquals(LocalDate.of(1990, 1, 1), calc.startDate);
		assertEquals(LocalDate.of(1990, 1, 1), calc.oneYearBreak);
		assertEquals(LocalDate.of(1994, 1, 1), calc.quarterBreak);
		assertEquals(config.endDate, calc.endDate);
	}

	@Test
	void calcRuntimeConfigJumbledDatesAndNulls2() {

		//Dates are way out of order
		config.startDate = LocalDate.of(2020, 2, 1);
		config.oneYearBreak = null;
		config.quarterBreak = null;
		config.endDate = LocalDate.of(1995, 2, 2);

		DateRangePartitionStrategy.Config calc = strategy.calcRuntimeConfig(config);

		assertEquals(LocalDate.of(1990, 1, 1), calc.startDate);
		assertEquals(LocalDate.of(1990, 1, 1), calc.oneYearBreak);
		assertEquals(LocalDate.of(1994, 1, 1), calc.quarterBreak);
		assertEquals(config.endDate, calc.endDate);
	}

	@Test
	void calcRuntimeConfigJumbledDatesAndNulls3() {

		//Dates are way out of order
		config.startDate = null;
		config.oneYearBreak = LocalDate.of(1801, 9, 1);
		config.quarterBreak = LocalDate.of(1800, 6, 1);
		config.endDate = LocalDate.of(1995, 2, 2);

		DateRangePartitionStrategy.Config calc = strategy.calcRuntimeConfig(config);

		assertEquals(LocalDate.of(1930, 1, 1), calc.startDate);
		assertEquals(LocalDate.of(1930, 1, 1), calc.oneYearBreak);
		assertEquals(LocalDate.of(1994, 1, 1), calc.quarterBreak);
		assertEquals(config.endDate, calc.endDate);
	}

	@Test
	void calcRuntimeConfigJumbledDatesAndNulls4() {

		//Dates are way out of order
		config.startDate = null;
		config.oneYearBreak = LocalDate.of(1801, 9, 1);
		config.quarterBreak = LocalDate.of(1800, 6, 1);
		config.endDate = null;  //This defaults to runTime now, which is A_RUN_TIME (2021-01-29)

		DateRangePartitionStrategy.Config calc = strategy.calcRuntimeConfig(config);

		assertEquals(LocalDate.of(1955, 1, 1), calc.startDate);
		assertEquals(LocalDate.of(1955, 1, 1), calc.oneYearBreak);
		assertEquals(LocalDate.of(2020, 1, 1), calc.quarterBreak);
		assertEquals(LocalDate.of(2021, 1, 29), calc.endDate);
	}

	@Test
	void calcRuntimeConfigJumbledDatesAndNulls5() {

		//Dates are way out of order
		config.startDate = null;
		config.oneYearBreak = null;
		config.quarterBreak = LocalDate.of(1800, 6, 1);
		config.endDate = null;  //This defaults to runTime now, which is A_RUN_TIME (2021-01-29)

		DateRangePartitionStrategy.Config calc = strategy.calcRuntimeConfig(config);

		assertEquals(LocalDate.of(1955, 1, 1), calc.startDate);
		assertEquals(LocalDate.of(1975, 1, 1), calc.oneYearBreak);
		assertEquals(LocalDate.of(2020, 1, 1), calc.quarterBreak);
		assertEquals(LocalDate.of(2021, 1, 29), calc.endDate);
	}


	@Test
	void getPartitionsWithAllConfigurationRequiringNoAdjustmentsAndDatesComingOutEvenly() {

		List<PgDateRangePart> ranges = strategy.getPartitions();

		assertNull(ranges.get(0).getRangeStart());
		assertEquals(PgDateRangePart.MIN_VALUE, ranges.get(0).getSqlFormatRangeStart());
		assertEquals("'2005-01-01'", ranges.get(0).getSqlFormatRangeEnd());
		assertEquals("MyTab_2021012910_MINVALUE_to_2005_01", ranges.get(0).getTableName() );

		assertEquals("'2005-01-01'", ranges.get(1).getSqlFormatRangeStart());
		assertEquals("'2010-01-01'", ranges.get(1).getSqlFormatRangeEnd());
		assertEquals("MyTab_2021012910_2005_01_to_2010_01", ranges.get(1).getTableName() );

		assertEquals("'2010-01-01'", ranges.get(2).getSqlFormatRangeStart());
		assertEquals("'2015-01-01'", ranges.get(2).getSqlFormatRangeEnd());
		assertEquals("MyTab_2021012910_2010_01_to_2015_01", ranges.get(2).getTableName() );

		assertEquals("'2015-01-01'", ranges.get(3).getSqlFormatRangeStart());
		assertEquals("'2016-01-01'", ranges.get(3).getSqlFormatRangeEnd());
		assertEquals("MyTab_2021012910_2015_01_to_2016_01", ranges.get(3).getTableName() );

		assertEquals("'2016-01-01'", ranges.get(4).getSqlFormatRangeStart());
		assertEquals("'2017-01-01'", ranges.get(4).getSqlFormatRangeEnd());
		assertEquals("MyTab_2021012910_2016_01_to_2017_01", ranges.get(4).getTableName() );

		assertEquals("'2017-01-01'", ranges.get(5).getSqlFormatRangeStart());
		assertEquals("'2018-01-01'", ranges.get(5).getSqlFormatRangeEnd());
		assertEquals("MyTab_2021012910_2017_01_to_2018_01", ranges.get(5).getTableName() );

		assertEquals("'2018-01-01'", ranges.get(6).getSqlFormatRangeStart());
		assertEquals("'2018-04-01'", ranges.get(6).getSqlFormatRangeEnd());
		assertEquals("MyTab_2021012910_2018_01_to_2018_04", ranges.get(6).getTableName() );

		assertEquals("'2018-04-01'", ranges.get(7).getSqlFormatRangeStart());
		assertEquals("'2018-07-01'", ranges.get(7).getSqlFormatRangeEnd());
		assertEquals("MyTab_2021012910_2018_04_to_2018_07", ranges.get(7).getTableName() );

		assertEquals("'2018-07-01'", ranges.get(8).getSqlFormatRangeStart());
		assertEquals("'2018-10-01'", ranges.get(8).getSqlFormatRangeEnd());
		assertEquals("MyTab_2021012910_2018_07_to_2018_10", ranges.get(8).getTableName() );

		assertEquals("'2018-10-01'", ranges.get(9).getSqlFormatRangeStart());
		assertEquals(PgDateRangePart.MAX_VALUE, ranges.get(9).getSqlFormatRangeEnd());
		assertEquals("MyTab_2021012910_2018_10_to_MAXVALUE", ranges.get(9).getTableName() );

		assertEquals(10, ranges.size());
	}

	@Test
	void getPartitionsWithAllConfigurationRequiringNoAdjustmentsAndDateNotEven() {

		LocalDate endDate = LocalDate.of(2019, 2, 2);   //Doesn't come out evenly, requiring an extra partition

		DateRangePartitionStrategy strategy = new DateRangePartitionStrategy(A_RUN_TIME, "MyTab",
				config.startDate, config.oneYearBreak, config.quarterBreak, endDate);

		List<PgDateRangePart> ranges = strategy.getPartitions();

		assertNull(ranges.get(0).getRangeStart());
		assertEquals(PgDateRangePart.MIN_VALUE, ranges.get(0).getSqlFormatRangeStart());
		assertEquals("'2005-01-01'", ranges.get(0).getSqlFormatRangeEnd());

		assertEquals("'2005-01-01'", ranges.get(1).getSqlFormatRangeStart());
		assertEquals("'2010-01-01'", ranges.get(1).getSqlFormatRangeEnd());

		assertEquals("'2010-01-01'", ranges.get(2).getSqlFormatRangeStart());
		assertEquals("'2015-01-01'", ranges.get(2).getSqlFormatRangeEnd());

		assertEquals("'2015-01-01'", ranges.get(3).getSqlFormatRangeStart());
		assertEquals("'2016-01-01'", ranges.get(3).getSqlFormatRangeEnd());

		assertEquals("'2016-01-01'", ranges.get(4).getSqlFormatRangeStart());
		assertEquals("'2017-01-01'", ranges.get(4).getSqlFormatRangeEnd());

		assertEquals("'2017-01-01'", ranges.get(5).getSqlFormatRangeStart());
		assertEquals("'2018-01-01'", ranges.get(5).getSqlFormatRangeEnd());

		assertEquals("'2018-01-01'", ranges.get(6).getSqlFormatRangeStart());
		assertEquals("'2018-04-01'", ranges.get(6).getSqlFormatRangeEnd());

		assertEquals("'2018-04-01'", ranges.get(7).getSqlFormatRangeStart());
		assertEquals("'2018-07-01'", ranges.get(7).getSqlFormatRangeEnd());

		assertEquals("'2018-07-01'", ranges.get(8).getSqlFormatRangeStart());
		assertEquals("'2018-10-01'", ranges.get(8).getSqlFormatRangeEnd());

		assertEquals("'2018-10-01'", ranges.get(9).getSqlFormatRangeStart());
		assertEquals("'2019-01-01'", ranges.get(9).getSqlFormatRangeEnd());

		assertEquals("'2019-01-01'", ranges.get(10).getSqlFormatRangeStart());
		assertEquals(PgDateRangePart.MAX_VALUE, ranges.get(10).getSqlFormatRangeEnd());    //This would be 2019-04-01, but adjusts to MAX

		assertEquals(11, ranges.size());
	}


	@Test
	void getPartitionsWithNoSingleYears() {

		LocalDate startDate = LocalDate.of(2005, 1, 1);
		LocalDate oneYearBreak = LocalDate.of(2015, 1, 1);
		LocalDate quarterBreak = LocalDate.of(2015, 1, 1);
		LocalDate endDate = LocalDate.of(2016, 2, 2);   //Some random end date that doesn't come out evenly

		DateRangePartitionStrategy strategy = new DateRangePartitionStrategy(A_RUN_TIME, "MyTab",
				startDate, oneYearBreak, quarterBreak, endDate);

		List<PgDateRangePart> ranges = strategy.getPartitions();

		assertNull(ranges.get(0).getRangeStart());
		assertEquals(PgDateRangePart.MIN_VALUE, ranges.get(0).getSqlFormatRangeStart());
		assertEquals("'2005-01-01'", ranges.get(0).getSqlFormatRangeEnd());

		assertEquals("'2005-01-01'", ranges.get(1).getSqlFormatRangeStart());
		assertEquals("'2010-01-01'", ranges.get(1).getSqlFormatRangeEnd());

		assertEquals("'2010-01-01'", ranges.get(2).getSqlFormatRangeStart());
		assertEquals("'2015-01-01'", ranges.get(2).getSqlFormatRangeEnd());

		assertEquals("'2015-01-01'", ranges.get(3).getSqlFormatRangeStart());
		assertEquals("'2015-04-01'", ranges.get(3).getSqlFormatRangeEnd());

		assertEquals("'2015-04-01'", ranges.get(4).getSqlFormatRangeStart());
		assertEquals("'2015-07-01'", ranges.get(4).getSqlFormatRangeEnd());

		assertEquals("'2015-07-01'", ranges.get(5).getSqlFormatRangeStart());
		assertEquals("'2015-10-01'", ranges.get(5).getSqlFormatRangeEnd());

		assertEquals("'2015-10-01'", ranges.get(6).getSqlFormatRangeStart());
		assertEquals("'2016-01-01'", ranges.get(6).getSqlFormatRangeEnd());

		assertEquals("'2016-01-01'", ranges.get(7).getSqlFormatRangeStart());
		assertEquals(PgDateRangePart.MAX_VALUE, ranges.get(7).getSqlFormatRangeEnd());    //This would be 2016-04-01, but adjusts to MAX

		assertEquals(8, ranges.size());
	}

}
