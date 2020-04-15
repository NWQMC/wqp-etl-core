package gov.acwi.wqp.etl.activity;

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
import gov.acwi.wqp.etl.activity.index.BuildActivityIndexesFlowIT;

public class AfterLoadActivityFlowIT extends BaseFlowIT {

	public static final String TABLE_NAME = "'activity_swap_testsrc'";
	public static final String EXPECTED_DATABASE_QUERY_ANALYZE = BASE_EXPECTED_DATABASE_QUERY_ANALYZE + TABLE_NAME;
	public static final String EXPECTED_DATABASE_QUERY_PRIMARY_KEY = BASE_EXPECTED_DATABASE_QUERY_PRIMARY_KEY
			+ EQUALS_QUERY + TABLE_NAME;
	public static final String EXPECTED_DATABASE_QUERY_FOREIGN_KEY = BASE_EXPECTED_DATABASE_QUERY_FOREIGN_KEY
			+ EQUALS_QUERY + TABLE_NAME;

	@Autowired
	@Qualifier(EtlConstantUtils.AFTER_LOAD_ACTIVITY_FLOW)
	private Flow afterLoadActivityFlow;

	@BeforeEach
	public void setUp() {
		testJob = jobBuilderFactory.get("afterLoadActivityFlowTest")
				.start(afterLoadActivityFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/activity.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void analyzeActivityStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("analyzeActivityStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/activity/indexes/pk.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX_PK + TABLE_NAME)
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/activity/primaryKey.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_PRIMARY_KEY,
			query=EXPECTED_DATABASE_QUERY_PRIMARY_KEY)
	public void addActivityPrimaryKeyStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("addActivityPrimaryKeyStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/activity/foreignKey.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_FOREIGN_KEY,
			query=EXPECTED_DATABASE_QUERY_FOREIGN_KEY)
	public void addActivityForeignKeyMonitoringLocationStepTest() {
		try {
			jdbcTemplate.execute("select add_monitoring_location_primary_key('testsrc', 'wqp', 'station')");
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("addActivityForeignKeyMonitoringLocationStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/activity/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildActivityIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/activity/indexes/pk.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX_PK + TABLE_NAME)
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/activity/primaryKey.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_PRIMARY_KEY,
			query=EXPECTED_DATABASE_QUERY_PRIMARY_KEY)
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/activity.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/activity/foreignKey.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_FOREIGN_KEY,
			query=EXPECTED_DATABASE_QUERY_FOREIGN_KEY)
	public void afterLoadActivityFlowTest() {
		try {
			jdbcTemplate.execute("select add_monitoring_location_primary_key('testsrc', 'wqp', 'station')");
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
