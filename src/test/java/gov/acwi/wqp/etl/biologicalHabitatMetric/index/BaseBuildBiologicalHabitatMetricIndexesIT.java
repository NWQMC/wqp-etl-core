package gov.acwi.wqp.etl.biologicalHabitatMetric.index;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import gov.acwi.wqp.etl.BaseFlowIT;

public abstract class BaseBuildBiologicalHabitatMetricIndexesIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'bio_hab_metric_swap_testsrc'";

	@Autowired
	@Qualifier("buildBiologicalHabitatMetricIndexesFlow")
	private Flow buildBiologicalHabitatMetricIndexesFlow;

	@BeforeEach
	public void setUp() {
		testJob = jobBuilderFactory.get("BuildBuildBiologicalHabitatMetricIndexesFlowTest")
				.start(buildBiologicalHabitatMetricIndexesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}
}
