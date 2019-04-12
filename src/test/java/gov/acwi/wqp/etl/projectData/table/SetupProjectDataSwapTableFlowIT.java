package gov.acwi.wqp.etl.projectData.table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.acwi.wqp.etl.BaseFlowIT;

public class SetupProjectDataSwapTableFlowIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE + "'project_data_swap_stewards'";

	@Autowired
	@Qualifier("setupProjectDataSwapTableFlow")
	private Flow setupProjectDataSwapTableFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("setupProjectDataSwapTableFlowTest")
				.start(setupProjectDataSwapTableFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/projectData/drop.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE, query=EXPECTED_DATABASE_QUERY)
	public void dropProjectDataSwapTableStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("dropProjectDataSwapTableStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/projectData/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE, query=EXPECTED_DATABASE_QUERY)
	public void createProjectDataSwapTableStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("createProjectDataSwapTableStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/projectData/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE, query=EXPECTED_DATABASE_QUERY)
	public void setupProjectDataSwapTableFlowTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
