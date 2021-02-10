package gov.acwi.wqp.etl.result.table;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import gov.acwi.wqp.etl.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Disabled;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

//See ConfigurationService:
//Env. Config params to control how result partitions are created, ensuring the partition structure is known & repeatable.
//ETL_RUN_TIME is used to construct unique partition names and overrides the actual runTime of the ETL job.
@TestPropertySource(properties = {"ETL_RESULT_PARTITION_START_DATE=2005-01-01",
                                  "ETL_RESULT_PARTITION_ONE_YEAR_BREAK=2015-01-01",
                                  "ETL_RESULT_PARTITION_QUARTER_BREAK=2017-01-01",
                                  "ETL_RESULT_PARTITION_END_DATE=2018-06-01",
                                  "ETL_RUN_TIME=2021-01-01T10:15:30"})
public class SetupResultSwapTablePartitionDataIT extends BaseFlowIT {

	@Autowired
	@Qualifier(EtlConstantUtils.SETUP_RESULT_SWAP_TABLE_FLOW)
	private Flow setupResultSwapTableFlow;

	/* Create result_swap_testsrc and all its child partitions before each test */
	@BeforeEach
	public void setUp() throws Exception {

		resetTestJob();

		//Drop the result table so we start clean
		//Ideal to do this after each test, however, dbUnit seems to run @AfterEach before @ExpectedDatabase :-/
		JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
		assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());

		//Can't reuse that job, so create a new unique one in case a test wants to run a job
		resetTestJob();
	}


	/**
	 * A clean new test job
	 */
	protected void resetTestJob() {

		baseSetup();    //Just gives us a new unique set of job params

		testJob = jobBuilderFactory.get("setupResultSwapTableFlowTest")
				          .start(setupResultSwapTableFlow)
				          .build()
				          .build();
		jobLauncherTestUtils.setJob(testJob);
	}

	/*  This test is not working for reasons I cannot figure out, apparently related to how DBUnit is configured.
	    The 'manual' dataInserted...Test below proves the same thing and is working. */
	@Disabled
	@Test
	@DatabaseSetup(connection=BaseFlowIT.CONNECTION_WQP, value="classpath:/testData/wqp/result/partitioned/")
	@ExpectedDatabase(connection=BaseFlowIT.CONNECTION_WQP, value="classpath:/testResult/wqp/result/partitioned/",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void setupResultSwapTableDateTest() {
		//no op - inserting the data happens in @DatabaseSetup, checking the individual partition tables in @ExpectedDatabase
	}

	@Test
	public void dataInsertedIntoResultTableShouldEndUpInPartitionTablesTest() {

		//In groups by targeted partitioned table

		//result_testsrc_2021010110_minvalue_to_2005_01
		insertSampleIntoResult(LocalDate.of(1200, 1, 1));
		insertSampleIntoResult(LocalDate.of(2004, 12, 31));
		//result_testsrc_2021010110_2005_01_to_2010_01
		insertSampleIntoResult(LocalDate.of(2005, 1, 1));
		insertSampleIntoResult(LocalDate.of(2009, 12, 31));
		//result_testsrc_2021010110_2010_01_to_2015_01
		insertSampleIntoResult(LocalDate.of(2010, 1, 1));
		insertSampleIntoResult(LocalDate.of(2014, 12, 31));
		//result_testsrc_2021010110_2015_01_to_2016_01
		insertSampleIntoResult(LocalDate.of(2015, 1, 1));
		insertSampleIntoResult(LocalDate.of(2015, 12, 31));
		//result_testsrc_2021010110_2016_01_to_2017_01
		insertSampleIntoResult(LocalDate.of(2016, 1, 1));
		insertSampleIntoResult(LocalDate.of(2016, 12, 31));
		//result_testsrc_2021010110_2017_01_to_2017_04
		insertSampleIntoResult(LocalDate.of(2017, 1, 1));
		insertSampleIntoResult(LocalDate.of(2017, 3, 31));
		//result_testsrc_2021010110_2017_04_to_2017_07
		insertSampleIntoResult(LocalDate.of(2017, 4, 1));
		insertSampleIntoResult(LocalDate.of(2017, 6, 30));
		//result_testsrc_2021010110_2017_07_to_2017_10
		insertSampleIntoResult(LocalDate.of(2017, 7, 1));
		insertSampleIntoResult(LocalDate.of(2017, 9, 30));
		//result_testsrc_2021010110_2017_10_to_2018_01
		insertSampleIntoResult(LocalDate.of(2017, 10, 1));
		insertSampleIntoResult(LocalDate.of(2017, 12, 31));
		//result_testsrc_2021010110_2018_01_to_2018_04
		insertSampleIntoResult(LocalDate.of(2018, 1, 1));
		insertSampleIntoResult(LocalDate.of(2018, 3, 31));
		//result_testsrc_2021010110_2018_04_to_maxvalue
		insertSampleIntoResult(LocalDate.of(2018, 4, 1));
		insertSampleIntoResult(LocalDate.of(2018, 6, 30));
		insertSampleIntoResult(LocalDate.of(2018, 7, 1));
		insertSampleIntoResult(LocalDate.of(2999, 1, 1));


		//All records should be in the parent table
		assertEquals(24, countInTable("result_swap_testsrc"));

		//Five yr partitions
		assertEquals(2, countInTable("result_testsrc_2021010110_minvalue_to_2005_01"));
		assertEquals(2, countInTable("result_testsrc_2021010110_2005_01_to_2010_01"));
		assertEquals(2, countInTable("result_testsrc_2021010110_2010_01_to_2015_01"));

		//One year partititions
		assertEquals(2, countInTable("result_testsrc_2021010110_2015_01_to_2016_01"));
		assertEquals(2, countInTable("result_testsrc_2021010110_2016_01_to_2017_01"));

		//quarters 2017
		assertEquals(2, countInTable("result_testsrc_2021010110_2017_01_to_2017_04"));
		assertEquals(2, countInTable("result_testsrc_2021010110_2017_04_to_2017_07"));
		assertEquals(2, countInTable("result_testsrc_2021010110_2017_07_to_2017_10"));
		assertEquals(2, countInTable("result_testsrc_2021010110_2017_10_to_2018_01"));

		//quarters 2018
		assertEquals(2, countInTable("result_testsrc_2021010110_2018_01_to_2018_04"));
		assertEquals(4, countInTable("result_testsrc_2021010110_2018_04_to_maxvalue"));

	}

	int insertSampleIntoResult(LocalDate date) {
		String dateStr = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
		String sql = "Insert into wqp.result_swap_testsrc (data_source_id, data_source, station_id, site_id, event_date) "
				             + "values (0,'TESTSRC',5,'ARS-INSJ-INSJALG','" + dateStr + "')";

		return jdbcTemplate.update(sql);
	}

	int countInTable(String tableName) {
		return jdbcTemplate.queryForObject("select count(*) from wqp." + tableName, Integer.TYPE);
	}
}
