package gov.acwi.wqp.etl.result.index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

public class BuildResultSampleTissueTaxonomicNameIndexIT extends BaseBuildResultIndexesIT {

	@Test
	@ExpectedDatabase(value="classpath:/testResult/wqp/result/indexes/sampleTissueTaxonomicName.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=EXPECTED_DATABASE_QUERY + " and indexname='result_swap_testsrc_sample_tissue_taxonomic_name'")
	public void buildResultSampleTissueTaxonomicNameIndexStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("buildResultSampleTissueTaxonomicNameIndexStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
