package gov.acwi.wqp.etl.summaries.monitoringLocationSum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.acwi.wqp.etl.BaseFlowIT;
import gov.acwi.wqp.etl.summaries.monitoringLocationSum.index.BuildMonitoringLocationSumIndexesFlowIT;
import gov.acwi.wqp.etl.summaries.monitoringLocationSum.table.SetupMonitoringLocationSumSwapTableFlowIT;

public class TransformMonitoringLocationSumIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_TABLE_STATION_SUM = "station_sum_swap_testsrc";
	public static final String TABLE_NAME = "'" + EXPECTED_DATABASE_TABLE_STATION_SUM + "'";
	public static final String EXPECTED_DATABASE_QUERY_ANALYZE = BASE_EXPECTED_DATABASE_QUERY_ANALYZE + TABLE_NAME;
	public static final String EXPECTED_DATABASE_QUERY_PRIMARY_KEY = BASE_EXPECTED_DATABASE_QUERY_PRIMARY_KEY
			+ EQUALS_QUERY + TABLE_NAME;

	public static final String EXPECTED_DATABASE_QUERY_STATION_SUM = BASE_EXPECTED_DATABASE_QUERY_STATION_SUM
			+ EXPECTED_DATABASE_TABLE_STATION_SUM + EXPECTED_DATABASE_QUERY_STATION_SUM_ORDER_BY;

	@Autowired
	@Qualifier("monitoringLocationSumFlow")
	private Flow monitoringLocationSumFlow;

	@BeforeEach
	public void setUp() {
		testJob = jobBuilderFactory.get("monitoringLocationSumFlowTest")
				.start(monitoringLocationSumFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/monitoringLocationSum/empty.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/monitoringLocation/monitoringLocation.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/activity/csv/")
	@DatabaseSetup(value="classpath:/testData/wqp/result/csv/")
	@DatabaseSetup(value="classpath:/testResult/wqp/mlGrouping/csv/")
	@DatabaseSetup(connection=CONNECTION_NWIS, value="classpath:/testData/nwis/country/country.xml")
	@DatabaseSetup(connection=CONNECTION_NWIS, value="classpath:/testData/nwis/state/state.xml")
	@DatabaseSetup(connection=CONNECTION_NWIS, value="classpath:/testData/nwis/county/county.xml")
	@DatabaseSetup(value="classpath:/testData/wqx/country/country.xml")
	@DatabaseSetup(value="classpath:/testData/wqx/state/state.xml")
	@DatabaseSetup(value="classpath:/testData/wqx/county/county.xml")
	@ExpectedDatabase(value="classpath:/testResult/wqp/monitoringLocationSum/monitoringLocationSum.xml",
			table=EXPECTED_DATABASE_TABLE_STATION_SUM,
			query=EXPECTED_DATABASE_QUERY_STATION_SUM)
	public void transformMonitoringLocationSumStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformMonitoringLocationSumStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/monitoringLocationSum/indexes/pk.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX_PK + TABLE_NAME)
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/monitoringLocationSum/primaryKey.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_PRIMARY_KEY,
			query=EXPECTED_DATABASE_QUERY_PRIMARY_KEY)
	public void addMonitoringLocationSumPrimaryKeyStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("addMonitoringLocationSumPrimaryKeyStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/monitoringLocationSum.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void analyzeMonitoringLocationSumStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("analyzeMonitoringLocationSumStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@DatabaseSetup(value="classpath:/testData/wqp/monitoringLocation/monitoringLocation.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/activity/csv/")
	@DatabaseSetup(value="classpath:/testData/wqp/result/csv/")
	@DatabaseSetup(value="classpath:/testResult/wqp/mlGrouping/csv/")
	@DatabaseSetup(connection=CONNECTION_NWIS, value="classpath:/testData/nwis/country/country.xml")
	@DatabaseSetup(connection=CONNECTION_NWIS, value="classpath:/testData/nwis/state/state.xml")
	@DatabaseSetup(connection=CONNECTION_NWIS, value="classpath:/testData/nwis/county/county.xml")
	@DatabaseSetup(value="classpath:/testData/wqx/country/country.xml")
	@DatabaseSetup(value="classpath:/testData/wqx/state/state.xml")
	@DatabaseSetup(value="classpath:/testData/wqx/county/county.xml")
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/monitoringLocationSum/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildMonitoringLocationSumIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(
			connection=CONNECTION_INFORMATION_SCHEMA,
			value="classpath:/testResult/wqp/monitoringLocationSum/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupMonitoringLocationSumSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/monitoringLocationSum/monitoringLocationSum.xml",
			table=EXPECTED_DATABASE_TABLE_STATION_SUM,
			query=EXPECTED_DATABASE_QUERY_STATION_SUM)
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/monitoringLocationSum.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/monitoringLocationSum/indexes/pk.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX_PK + TABLE_NAME)
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/monitoringLocationSum/primaryKey.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_PRIMARY_KEY,
			query=EXPECTED_DATABASE_QUERY_PRIMARY_KEY)
	public void monitoringLocationSumFlowTest() {
		Job monitoringLocationSumFlowTest = jobBuilderFactory.get("monitoringLocationSumFlowTest")
					.start(monitoringLocationSumFlow)
					.build()
					.build();
		jobLauncherTestUtils.setJob(monitoringLocationSumFlowTest);
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

}
