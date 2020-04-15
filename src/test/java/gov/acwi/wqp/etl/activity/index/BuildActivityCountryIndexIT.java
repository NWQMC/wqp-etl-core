package gov.acwi.wqp.etl.activity.index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

public class BuildActivityCountryIndexIT extends BaseBuildActivityIndexesIT {

	@Test
	@ExpectedDatabase(value="classpath:/testResult/wqp/activity/indexes/country.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=EXPECTED_DATABASE_QUERY + " and indexname='activity_swap_testsrc_country'")
	public void buildActivityCountryIndexStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("buildActivityCountryIndexStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
