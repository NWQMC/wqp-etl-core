package gov.acwi.wqp.etl.resultSum.index;

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

public class BuildResultSumIndexesFlowIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = "select tablename, indexname, indexdef from pg_indexes where tablename='result_sum_swap_stewards'";
	public static final String EXPECTED_DATABASE_TABLE = "pg_indexes";

	@Autowired
	@Qualifier("buildResultSumIndexesFlow")
	private Flow buildResultSumIndexesFlow;

	@Before
	public void setup() {
		testJob = jobBuilderFactory.get("BuildResultSumIndexesFlowTest")
				.start(buildResultSumIndexesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@ExpectedDatabase(value="classpath:/testResult/wqp/resultSum/indexes/organization.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE, query=EXPECTED_DATABASE_QUERY + " and indexname='result_sum_swap_stewards_organization'")
	public void buildResultSumOrganizationIndexStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("buildResultSumOrganizationIndexStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(value="classpath:/testResult/wqp/resultSum/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE, query=EXPECTED_DATABASE_QUERY)
	public void buildResultSumIndexesFlowTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
