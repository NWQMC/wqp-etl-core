package gov.acwi.wqp.etl.monitoringLocation.index;

import org.junit.Before;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import gov.acwi.wqp.etl.BaseFlowIT;
import gov.acwi.wqp.etl.EtlConstantUtils;

public abstract class BaseBuildMonitoringLocationIndexesIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'station_swap_testsrc'";

	@Autowired
	@Qualifier(EtlConstantUtils.BUILD_MONITORING_LOCATION_INDEXES_FLOW)
	private Flow buildMonitoringLocationIndexesFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("BuildMonitoringLocationIndexesFlowTest")
				.start(buildMonitoringLocationIndexesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}
}
