package gov.acwi.wqp.etl.resDetectQntLimit.index;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

public class BuildResDetectQntLimitCharacteristicTypeIndexIT extends BaseBuildResDetectQntLimitIndexesIT {

	@Test
	@ExpectedDatabase(value="classpath:/testResult/wqp/resDetectQntLimit/indexes/characteristicType.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=EXPECTED_DATABASE_QUERY_ANALYZE + " and indexname='r_detect_qnt_lmt_swap_testsrc_characteristic_type'")
	public void buildResDetectQntLimitCharacteristicTypeIndexStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("buildResDetectQntLimitCharacteristicTypeIndexStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
