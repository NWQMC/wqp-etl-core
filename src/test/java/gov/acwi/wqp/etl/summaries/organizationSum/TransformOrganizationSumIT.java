package gov.acwi.wqp.etl.summaries.organizationSum;

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
import gov.acwi.wqp.etl.summaries.organizationSum.index.BuildOrganizationSumIndexesFlowIT;
import gov.acwi.wqp.etl.summaries.organizationSum.table.SetupOrganizationSumSwapTableFlowIT;

public class TransformOrganizationSumIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY_ANALYZE = BASE_EXPECTED_DATABASE_QUERY_ANALYZE + "'organization_sum_swap_testsrc'";

	@Autowired
	@Qualifier("organizationSumFlow")
	private Flow organizationSumFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("organizationSumFlowTest")
				.start(organizationSumFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

//TODO - WQP-1406
	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/organizationSum/empty.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/orgData/orgDataOld.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/result/yearsWindow/csv/")
	@ExpectedDatabase(value="classpath:/testResult/wqp/organizationSum/withFineGrainedYears.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void transformOrganizationSumStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformOrganizationSumStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/organizationSum.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void analyzeOrganizationSumStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("analyzeOrganizationSumStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
//TODO - WQP-1406
	@DatabaseSetup(value="classpath:/testData/wqp/orgData/orgDataOld.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/result/yearsWindow/csv/")
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/organizationSum/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildOrganizationSumIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(
			connection=CONNECTION_INFORMATION_SCHEMA,
			value="classpath:/testResult/wqp/organizationSum/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupOrganizationSumSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/organizationSum/withFineGrainedYears.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/organizationSum.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void organizationSumFlowTest() {
		Job organizationSumFlowTest = jobBuilderFactory.get("organizationSumFlowTest")
					.start(organizationSumFlow)
					.build()
					.build();
		jobLauncherTestUtils.setJob(organizationSumFlowTest);
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
