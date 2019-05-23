package gov.acwi.wqp.etl.activity.index;

import org.junit.Before;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import gov.acwi.wqp.etl.BaseFlowIT;
import gov.acwi.wqp.etl.EtlConstantUtils;

public abstract class BaseBuildActivityIndexesIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'activity_swap_testsrc'";

	@Autowired
	@Qualifier(EtlConstantUtils.BUILD_ACTIVITY_INDEXES_FLOW)
	private Flow buildActivityIndexesFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("BuildActivityIndexesFlowTest")
				.start(buildActivityIndexesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}
}
