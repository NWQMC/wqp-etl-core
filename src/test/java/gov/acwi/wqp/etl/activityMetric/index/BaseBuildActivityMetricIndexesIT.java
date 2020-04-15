package gov.acwi.wqp.etl.activityMetric.index;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import gov.acwi.wqp.etl.BaseFlowIT;

public abstract class BaseBuildActivityMetricIndexesIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'act_metric_swap_testsrc'";

	@Autowired
	@Qualifier("buildActivityMetricIndexesFlow")
	private Flow buildActivityMetricMetricIndexesFlow;

	@BeforeEach
	public void setUp() {
		testJob = jobBuilderFactory.get("BuildActivityMetricIndexesFlowTest")
				.start(buildActivityMetricMetricIndexesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

}
