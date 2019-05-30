package gov.acwi.wqp.etl.summaries.monitoringLocationSum.index;

import org.junit.Before;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import gov.acwi.wqp.etl.BaseFlowIT;

public abstract class BaseBuildMonitoringLocationSumIndexesIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY_ANALYZE = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'station_sum_swap_testsrc'";

	@Autowired
	@Qualifier("buildMonitoringLocationSumIndexesFlow")
	private Flow buildMonitoringLocationSumIndexesFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("BuildMonitoringLocationSumIndexesFlowTest")
				.start(buildMonitoringLocationSumIndexesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}
}
