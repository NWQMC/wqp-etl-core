package gov.acwi.wqp.etl.summaries.activitySum.index;

import org.junit.Before;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import gov.acwi.wqp.etl.BaseFlowIT;

public abstract class BaseBuildActivitySumIndexesIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'activity_sum_swap_testsrc'";

	@Autowired
	@Qualifier("buildActivitySumIndexesFlow")
	private Flow buildActivitySumIndexesFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("BuildActivitySumIndexesFlowTest")
				.start(buildActivitySumIndexesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}
}
