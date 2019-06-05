package gov.acwi.wqp.etl.summaries.mlGrouping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
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
import gov.acwi.wqp.etl.summaries.mlGrouping.index.BuildMlGroupingIndexesFlowIT;
import gov.acwi.wqp.etl.summaries.mlGrouping.table.SetupMlGroupingSwapTableFlowIT;

public class TransformMlGroupingIT extends BaseFlowIT {

	public static final String TABLE_NAME = "'ml_grouping_swap_testsrc'";
	public static final String EXPECTED_DATABASE_QUERY_ANALYZE = BASE_EXPECTED_DATABASE_QUERY_ANALYZE + TABLE_NAME;
	public static final String EXPECTED_DATABASE_QUERY_FOREIGN_KEY = BASE_EXPECTED_DATABASE_QUERY_FOREIGN_KEY
			+ EQUALS_QUERY + TABLE_NAME;

	@Autowired
	@Qualifier("mlGroupingFlow")
	private Flow mlGroupingFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("mlGroupingFlowTest")
				.start(mlGroupingFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/mlGrouping/empty.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/result/csv/")
	@ExpectedDatabase(value="classpath:/testResult/wqp/mlGrouping/csv/", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void transformMlGroupingStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformMlGroupingStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/mlGrouping.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void analyzeMlGroupingStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("analyzeMlGroupingStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@DatabaseSetup(value="classpath:/testData/wqp/mlGrouping/mlGrouping.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/result/csv/")
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/mlGrouping/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildMlGroupingIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(
			connection=CONNECTION_INFORMATION_SCHEMA,
			value="classpath:/testResult/wqp/mlGrouping/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupMlGroupingSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/mlGrouping/csv/", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/mlGrouping.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void mlGroupingFlowTest() {
		Job mlGroupingFlowTest = jobBuilderFactory.get("mlGroupingFlowTest")
					.start(mlGroupingFlow)
					.build()
					.build();
		jobLauncherTestUtils.setJob(mlGroupingFlowTest);
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
