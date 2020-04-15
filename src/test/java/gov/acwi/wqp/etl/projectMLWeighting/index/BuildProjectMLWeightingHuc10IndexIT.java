package gov.acwi.wqp.etl.projectMLWeighting.index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

public class BuildProjectMLWeightingHuc10IndexIT extends BaseBuildProjectMLWeightingIndexesIT {

	@Test
	@ExpectedDatabase(value="classpath:/testResult/wqp/projectMLWeighting/indexes/huc10.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=EXPECTED_DATABASE_QUERY + " and indexname='prj_ml_weighting_swap_testsrc_huc_10'")
	public void buildProjectMLWeightingHuc10IndexStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("buildProjectMLWeightingHuc10IndexStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
