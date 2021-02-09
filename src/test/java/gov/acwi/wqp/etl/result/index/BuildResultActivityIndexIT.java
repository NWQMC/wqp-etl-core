package gov.acwi.wqp.etl.result.index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import gov.acwi.wqp.etl.ConfigurationService;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.springframework.beans.factory.annotation.Autowired;

public class BuildResultActivityIndexIT extends BaseBuildResultIndexesIT {

	@Autowired
	private ConfigurationService config;

	//This test really could be moved to a ConfigurationService test, but that test exists in a parallel merge request.
	@Test
	public void indexConcurrencyShouldBDefaultTo3() {
		assertEquals(3, config.getDbOperationConcurrency());
	}

	@Test
	@ExpectedDatabase(value="classpath:/testResult/wqp/result/indexes/activity.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=EXPECTED_DATABASE_QUERY + " and indexname='result_swap_testsrc_activity'")
	public void buildResultActivityIndexStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("buildResultActivityIndexStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
