package gov.acwi.wqp.etl.summaries.orgGrouping;

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
import gov.acwi.wqp.etl.summaries.orgGrouping.index.BuildOrgGroupingIndexesFlowIT;
import gov.acwi.wqp.etl.summaries.orgGrouping.table.SetupOrgGroupingSwapTableFlowIT;

public class TransformOrgGroupingIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY_ANALYZE = BASE_EXPECTED_DATABASE_QUERY_ANALYZE + "'org_grouping_swap_testsrc'";

	@Autowired
	@Qualifier("orgGroupingFlow")
	private Flow orgGroupingFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("orgGroupingFlowTest")
				.start(orgGroupingFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/orgGrouping/empty.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/result/csv/")
	@ExpectedDatabase(value="classpath:/testResult/wqp/orgGrouping/csv/", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void transformOrgGroupingStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformOrgGroupingStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/orgGrouping.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void analyzeOrgGroupingStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("analyzeOrgGroupingStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@DatabaseSetup(value="classpath:/testData/wqp/result/csv/")
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/orgGrouping/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildOrgGroupingIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(
			connection=CONNECTION_INFORMATION_SCHEMA,
			value="classpath:/testResult/wqp/orgGrouping/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupOrgGroupingSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/orgGrouping/csv/", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/orgGrouping.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void orgGroupingFlowTest() {
		Job orgGroupingFlowTest = jobBuilderFactory.get("orgGroupingFlowTest")
					.start(orgGroupingFlow)
					.build()
					.build();
		jobLauncherTestUtils.setJob(orgGroupingFlowTest);
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
