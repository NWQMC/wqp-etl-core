package gov.acwi.wqp.etl.biologicalHabitatMetric.index;

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

public class BuildBiologicalHabitatMetricIndexesFlowIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'bio_hab_metric_swap_testsrc'";

	@Autowired
	@Qualifier(EtlConstantUtils.BUILD_BIOLOGICAL_HABITAT_METRIC_INDEXES_FLOW)
	private Flow buildBiologicalHabitatMetricIndexesFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("BuildBuildBiologicalHabitatMetricIndexesFlowTest")
				.start(buildBiologicalHabitatMetricIndexesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@ExpectedDatabase(value="classpath:/testResult/wqp/biologicalHabitatMetric/indexes/organization.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=EXPECTED_DATABASE_QUERY + " and indexname='bio_hab_metric_swap_testsrc_organization'")
	public void buildBiologicalHabitatMetricOrganizationIndexStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("buildBiologicalHabitatMetricOrganizationIndexStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(value="classpath:/testResult/wqp/biologicalHabitatMetric/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=EXPECTED_DATABASE_QUERY)
	public void buildBiologicalHabitatMetricIndexesFlowTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
