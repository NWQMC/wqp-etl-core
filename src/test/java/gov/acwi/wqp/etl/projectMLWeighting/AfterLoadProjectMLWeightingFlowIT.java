package gov.acwi.wqp.etl.projectMLWeighting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.acwi.wqp.etl.BaseFlowIT;
import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.projectMLWeighting.index.BuildProjectMLWeightingIndexesFlowIT;

public class AfterLoadProjectMLWeightingFlowIT extends BaseFlowIT {

	public static final String TABLE_NAME = "'prj_ml_weighting_swap_testsrc'";
	public static final String EXPECTED_DATABASE_QUERY_ANALYZE = BASE_EXPECTED_DATABASE_QUERY_ANALYZE + TABLE_NAME;
	public static final String EXPECTED_DATABASE_QUERY_FOREIGN_KEY = BASE_EXPECTED_DATABASE_QUERY_FOREIGN_KEY
			+ EQUALS_QUERY + TABLE_NAME;
	public static final String EXPECTED_DATABASE_QUERY_FOREIGN_KEY_MONITORING_LOCATION = EXPECTED_DATABASE_QUERY_FOREIGN_KEY
			+ " and conname='prj_ml_weighting_testsrc_fk_station'";
	public static final String EXPECTED_DATABASE_QUERY_FOREIGN_KEY_PROJECT_DATA = EXPECTED_DATABASE_QUERY_FOREIGN_KEY
			+ " and conname='prj_ml_weighting_testsrc_fk_project_data'";

	@Autowired
	@Qualifier(EtlConstantUtils.AFTER_LOAD_PROJECT_ML_WEIGHTING_FLOW)
	private Flow afterLoadProjectMLWeightingFlow;

	@BeforeEach
	public void setUp() {
		testJob = jobBuilderFactory.get("afterLoadProjectMLWeightingFlowTest")
				.start(afterLoadProjectMLWeightingFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/projectMLWeighting.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void analyzeProjectMLWeightingStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("analyzeProjectMLWeightingStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/projectMLWeighting/foreignKeyMonitoringLocation.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_FOREIGN_KEY,
			query=EXPECTED_DATABASE_QUERY_FOREIGN_KEY_MONITORING_LOCATION)
	public void addProjectMLWeightingForeignKeyMonitoringLocationStepTest() {
		try {
			jdbcTemplate.execute("select add_monitoring_location_primary_key('testsrc', 'wqp', 'station')");
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("addProjectMLWeightingForeignKeyMonitoringLocationStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/projectMLWeighting/foreignKeyProjectData.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_FOREIGN_KEY,
			query=EXPECTED_DATABASE_QUERY_FOREIGN_KEY_PROJECT_DATA)
	public void addProjectMLWeightingForeignKeyProjectDataStepTest() {
		try {
			jdbcTemplate.execute("select add_project_data_primary_key('testsrc', 'wqp', 'project_data')");
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("addProjectMLWeightingForeignKeyProjectDataStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/projectMLWeighting/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildProjectMLWeightingIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/projectMLWeighting.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/projectMLWeighting/foreignKeyMonitoringLocation.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_FOREIGN_KEY,
			query=EXPECTED_DATABASE_QUERY_FOREIGN_KEY_MONITORING_LOCATION)
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/projectMLWeighting/foreignKeyProjectData.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_FOREIGN_KEY,
			query=EXPECTED_DATABASE_QUERY_FOREIGN_KEY_PROJECT_DATA)
	public void afterLoadProjectMLWeightingFlowTest() {
		try {
			jdbcTemplate.execute("select add_monitoring_location_primary_key('testsrc', 'wqp', 'station')");
			jdbcTemplate.execute("select add_project_data_primary_key('testsrc', 'wqp', 'project_data')");
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
