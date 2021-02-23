package gov.acwi.wqp.etl.result;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.result.index.BaseBuildResultIndexesIT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//See BaseForTestsNeedingPartitionedResultTables@TestPropertySource for config params
public class AfterLoadResultFlowIT extends BaseForTestsNeedingPartitionedResultTables {

	public static final String TABLE_NAME = "'result_swap_testsrc'";
	public static final String EXPECTED_DATABASE_QUERY_ANALYZE =
			"select schemaname, relname, (now() - last_analyze) < make_interval(mins => 1) within_one_minute " +
					"from pg_stat_all_tables where relname like 'result_testsrc_%' and relname != 'result_testsrc_old'";
	public static final String EXPECTED_DATABASE_QUERY_FOREIGN_KEY = BASE_EXPECTED_DATABASE_QUERY_FOREIGN_KEY
			+ EQUALS_QUERY + TABLE_NAME;

	@Autowired
	@Qualifier(EtlConstantUtils.AFTER_LOAD_RESULT_FLOW)
	private Flow afterLoadResultFlow;

	@BeforeEach
	public void setUp() {
		testJob = jobBuilderFactory.get("afterLoadResultFlowTest")
				.start(afterLoadResultFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	/*
	Its weird, but the pg_stat_all_tables does not include the parent table of a partition - only the child tables are there.
	 */
	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/result.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void analyzeResultStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("analyzeResultStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/result/foreignKey.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_FOREIGN_KEY,
			query=EXPECTED_DATABASE_QUERY_FOREIGN_KEY)
	public void addResultForeignKeyMonitoringLocationStepTest() {
		try {
			jdbcTemplate.execute("select add_monitoring_location_primary_key('testsrc', 'wqp', 'station')");
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("addResultForeignKeyMonitoringLocationStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/result/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query= BaseBuildResultIndexesIT.EXPECTED_ALL_INDEXES_QUERY)
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/result.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/result/foreignKey.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_FOREIGN_KEY,
			query=EXPECTED_DATABASE_QUERY_FOREIGN_KEY)
	public void afterLoadResultFlowTest() {
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
