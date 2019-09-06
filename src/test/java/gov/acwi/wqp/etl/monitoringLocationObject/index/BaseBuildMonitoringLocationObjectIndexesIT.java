package gov.acwi.wqp.etl.monitoringLocationObject.index;

import org.junit.Before;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import gov.acwi.wqp.etl.BaseFlowIT;

public abstract class BaseBuildMonitoringLocationObjectIndexesIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'station_object_swap_testsrc'";

	@Autowired
	@Qualifier("buildMonitoringLocationObjectIndexesFlow")
	private Flow buildMonitoringLocationObjectIndexesFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("BuildMonitoringLocationObjectIndexesFlowTest")
				.start(buildMonitoringLocationObjectIndexesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}
}
