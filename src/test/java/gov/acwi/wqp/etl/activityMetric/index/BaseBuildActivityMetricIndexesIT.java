package gov.acwi.wqp.etl.activityMetric.index;

import org.junit.Before;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import gov.acwi.wqp.etl.BaseFlowIT;
import gov.acwi.wqp.etl.EtlConstantUtils;

public abstract class BaseBuildActivityMetricIndexesIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY_ANALYZE = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'act_metric_swap_testsrc'";

	@Autowired
	@Qualifier(EtlConstantUtils.BUILD_ACTIVITY_METRIC_INDEXES_FLOW)
	private Flow buildActivityMetricMetricIndexesFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("BuildActivityMetricIndexesFlowTest")
				.start(buildActivityMetricMetricIndexesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

}
