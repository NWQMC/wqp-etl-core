package gov.acwi.wqp.etl.summaries.resultSum.table;

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

public class SetupResultSumSwapTableFlowIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = "select table_catalog, table_schema, table_name, table_type from information_schema.tables where table_name='result_sum_swap_stewards'";
	public static final String EXPECTED_DATABASE_TABLE = "tables";

	@Autowired
	@Qualifier("setupResultSumSwapTableFlow")
	private Flow setupResultSumSwapTableFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("setupResultSumSwapTableFlowTest")
				.start(setupResultSumSwapTableFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/resultSum/drop.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE, query=EXPECTED_DATABASE_QUERY)
	public void dropResultSumSwapTableStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("dropResultSumSwapTableStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/resultSum/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE, query=EXPECTED_DATABASE_QUERY)
	public void createResultSumSwapTableStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("createResultSumSwapTableStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/resultSum/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE, query=EXPECTED_DATABASE_QUERY)
	public void setupResultSumSwapTableFlowTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
