package gov.acwi.wqp.etl.resDetectQntLimit.index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

public class BuildResDetectQntLimitAssemblageSampledNameIndexIT extends BaseBuildResDetectQntLimitIndexesIT {

	@Test
	@ExpectedDatabase(value="classpath:/testResult/wqp/resDetectQntLimit/indexes/assemblageSampledName.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=EXPECTED_DATABASE_QUERY + " and indexname='r_detect_qnt_lmt_swap_testsrc_assemblage_sampled_name'")
	public void buildResDetectQntLimitAssemblageSampledNameIndexStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("buildResDetectQntLimitAssemblageSampledNameIndexStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
