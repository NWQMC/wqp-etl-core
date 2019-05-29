package gov.acwi.wqp.etl.resDetectQntLimit;

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
import gov.acwi.wqp.etl.EtlConstantUtils;

public class AnalyzeResDetectQntLimitIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = EXPECTED_DATABASE_QUERY_ANALYZE + "'r_detect_qnt_lmt_swap_testsrc'";

	@Autowired
	@Qualifier(EtlConstantUtils.ANALYZE_RES_DETECT_QNT_LIMIT_FLOW)
	private Flow analyzeResDetectQntLimitFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("analyzeResDetectQntLimitFlowTest")
				.start(analyzeResDetectQntLimitFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/resDetectQntLimit.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=TABLE_NAME_PG_STAT_ALL_TABLES,
			query=EXPECTED_DATABASE_QUERY)
	public void analyzeResDetectQntLimitFlowTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

}
