package gov.acwi.wqp.etl.result.index;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import gov.acwi.wqp.etl.ConfigurationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BuildResultIndexesFlowIT extends BaseBuildResultIndexesIT {

	@Autowired
	private ConfigurationService config;

	@Test
	public void indexConcurrencyShouldBeSetTo2FromTestProperties() {
		assertEquals(2, config.getDbOperationConcurrency());    //Set as a param in parent class
	}

	//This is really a duplicate test (see AfterLoadResultFlowIT), but it does test just index creation and uses
	//the same 'all.xml' result, so perhaps worth keeping.
	@Test
	@ExpectedDatabase(value="classpath:/testResult/wqp/result/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX, query=EXPECTED_ALL_INDEXES_QUERY)
	public void buildResultIndexesFlowTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
