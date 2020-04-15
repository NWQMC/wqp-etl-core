package gov.acwi.wqp.etl.activityMetric;

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
import gov.acwi.wqp.etl.activityMetric.index.BuildActivityMetricIndexesFlowIT;

public class AfterLoadActivityMetricFlowIT extends BaseFlowIT {

	public static final String TABLE_NAME = "'act_metric_swap_testsrc'";
	public static final String EXPECTED_DATABASE_QUERY_ANALYZE = BASE_EXPECTED_DATABASE_QUERY_ANALYZE + TABLE_NAME;
	public static final String EXPECTED_DATABASE_QUERY_FOREIGN_KEY = BASE_EXPECTED_DATABASE_QUERY_FOREIGN_KEY
			+ EQUALS_QUERY + TABLE_NAME;

	@Autowired
	@Qualifier(EtlConstantUtils.AFTER_LOAD_ACTIVITY_METRIC_FLOW)
	private Flow afterLoadActivityMetricFlow;

	@BeforeEach
	public void setUp() {
		testJob = jobBuilderFactory.get("afterLoadActivityMetricFlowTest")
				.start(afterLoadActivityMetricFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/activityMetric.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void analyzeActivityMetricStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("analyzeActivityMetricStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/activityMetric/foreignKey.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_FOREIGN_KEY,
			query=EXPECTED_DATABASE_QUERY_FOREIGN_KEY)
	public void addActivityMetricForeignKeyActivityStepTest() {
		try {
			jdbcTemplate.execute("select add_activity_primary_key('testsrc', 'wqp', 'activity')");
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("addActivityMetricForeignKeyActivityStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/activityMetric/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildActivityMetricIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/activityMetric.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/activityMetric/foreignKey.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_FOREIGN_KEY,
			query=EXPECTED_DATABASE_QUERY_FOREIGN_KEY)
	public void afterLoadActivityMetricFlowTest() {
		try {
			jdbcTemplate.execute("select add_activity_primary_key('testsrc', 'wqp', 'activity')");
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
