package gov.acwi.wqp.etl.summaries;

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
import gov.acwi.wqp.etl.summaries.mlGrouping.TransformMlGroupingIT;
import gov.acwi.wqp.etl.summaries.resultSum.TransformResultSumIT;

public class AddSummariesForeignKeysIT extends BaseFlowIT {

	@Autowired
	@Qualifier("addSummariesForeignKeysFlow")
	private Flow addSummariesForeignKeysFlow;

	@BeforeEach
	public void setUp() {
		testJob = jobBuilderFactory.get("addSummariesForeignKeysFlowTest")
				.start(addSummariesForeignKeysFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/resultSum/foreignKey.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_FOREIGN_KEY,
			query=TransformResultSumIT.EXPECTED_DATABASE_QUERY_FOREIGN_KEY)
	public void addResultSumForeignKeyMonitoringLocationSumStepTest() {
		try {
			jdbcTemplate.execute("select add_monitoring_location_sum_primary_key('testsrc', 'wqp', 'station_sum')");
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("addResultSumForeignKeyMonitoringLocationSumStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/mlGrouping/foreignKey.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_FOREIGN_KEY,
			query=TransformMlGroupingIT.EXPECTED_DATABASE_QUERY_FOREIGN_KEY)
	public void addMlGroupingForeignKeyMonitoringLocationSumStepTest() {
		try {
			jdbcTemplate.execute("select add_monitoring_location_sum_primary_key('testsrc', 'wqp', 'station_sum')");
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("addMlGroupingForeignKeyMonitoringLocationSumStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/resultSum/foreignKey.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_FOREIGN_KEY,
			query=TransformResultSumIT.EXPECTED_DATABASE_QUERY_FOREIGN_KEY)
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/mlGrouping/foreignKey.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_FOREIGN_KEY,
			query=TransformMlGroupingIT.EXPECTED_DATABASE_QUERY_FOREIGN_KEY)
	public void addSummariesForeignKeysFlowTest() {
		try {
			jdbcTemplate.execute("select add_monitoring_location_sum_primary_key('testsrc', 'wqp', 'station_sum')");
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
