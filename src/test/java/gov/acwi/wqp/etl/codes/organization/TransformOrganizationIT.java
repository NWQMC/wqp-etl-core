package gov.acwi.wqp.etl.codes.organization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.acwi.wqp.etl.BaseFlowIT;
import gov.acwi.wqp.etl.codes.organization.index.BuildOrganizationIndexesFlowIT;
import gov.acwi.wqp.etl.codes.organization.table.SetupOrganizationSwapTableFlowIT;

public class TransformOrganizationIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY_ANALYZE = BASE_EXPECTED_DATABASE_QUERY_ANALYZE + "'organization_swap_testsrc'";

	@Autowired
	@Qualifier("createOrganizationFlow")
	private Flow createOrganizationFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("createOrganizationFlowTest")
				.start(createOrganizationFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/organization/empty.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/organizationSum/organizationSum.xml")
	@ExpectedDatabase(value="classpath:/testResult/wqp/organization/organization.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void transformOrganizationStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformOrganizationStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/organization.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void analyzeOrganizationStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("analyzeOrganizationStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@DatabaseSetup(value="classpath:/testData/wqp/organizationSum/organizationSum.xml")
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/organization/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildOrganizationIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(
			connection=CONNECTION_INFORMATION_SCHEMA,
			value="classpath:/testResult/wqp/organization/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupOrganizationSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/organization/organization.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/organization.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void organizationFlowTest() {
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
