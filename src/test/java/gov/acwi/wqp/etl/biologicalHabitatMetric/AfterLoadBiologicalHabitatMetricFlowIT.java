package gov.acwi.wqp.etl.biologicalHabitatMetric;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.acwi.wqp.etl.BaseFlowIT;
import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.biologicalHabitatMetric.index.BuildBiologicalHabitatMetricIndexesFlowIT;

public class AfterLoadBiologicalHabitatMetricFlowIT extends BaseFlowIT {

	public static final String TABLE_NAME = "'bio_hab_metric_swap_testsrc'";
	public static final String EXPECTED_DATABASE_QUERY_ANALYZE = BASE_EXPECTED_DATABASE_QUERY_ANALYZE + TABLE_NAME;
	public static final String EXPECTED_DATABASE_QUERY_PRIMARY_KEY = BASE_EXPECTED_DATABASE_QUERY_PRIMARY_KEY
			+ EQUALS_QUERY + TABLE_NAME;

	@Autowired
	@Qualifier(EtlConstantUtils.AFTER_LOAD_BIOLOGICAL_HABITAT_METRIC_FLOW)
	private Flow afterLoadBiologicalHabitatMetricFlow;

	@BeforeEach
	public void setUp() {
		testJob = jobBuilderFactory.get("afterLoadBiologicalHabitatMetricFlowTest")
				.start(afterLoadBiologicalHabitatMetricFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/biologicalHabitatMetric.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void analyzeBiologicalHabitatMetricStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("analyzeBiologicalHabitatMetricStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/biologicalHabitatMetric/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildBiologicalHabitatMetricIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/biologicalHabitatMetric.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void afterLoadBiologicalHabitatMetricFlowTest() {
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
