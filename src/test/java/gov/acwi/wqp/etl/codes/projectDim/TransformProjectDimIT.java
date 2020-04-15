package gov.acwi.wqp.etl.codes.projectDim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.acwi.wqp.etl.BaseFlowIT;
import gov.acwi.wqp.etl.codes.projectDim.index.BuildProjectDimIndexesFlowIT;
import gov.acwi.wqp.etl.codes.projectDim.table.SetupProjectDimSwapTableFlowIT;

public class TransformProjectDimIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY_ANALYZE = BASE_EXPECTED_DATABASE_QUERY_ANALYZE + "'project_dim_swap_testsrc'";

	@Autowired
	@Qualifier("createProjectDimFlow")
	private Flow createProjectDimFlow;

	@BeforeEach
	public void setUp() {
		testJob = jobBuilderFactory.get("createProjectDimFlowTest")
				.start(createProjectDimFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/projectDim/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/activitySum/activitySum.xml")
	@ExpectedDatabase(value="classpath:/testResult/wqp/projectDim/projectDim.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void transformProjectDimStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformProjectDimStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/projectDim.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void analyzeProjectDimStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("analyzeProjectDimStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/activitySum/activitySum.xml")
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/projectDim/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildProjectDimIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(
			connection=CONNECTION_INFORMATION_SCHEMA,
			value="classpath:/testResult/wqp/projectDim/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupProjectDimSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/projectDim/projectDim.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/projectDim.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void projectDimFlowTest() {
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
