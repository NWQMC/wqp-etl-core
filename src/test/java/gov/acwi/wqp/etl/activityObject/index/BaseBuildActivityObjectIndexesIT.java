package gov.acwi.wqp.etl.activityObject.index;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import gov.acwi.wqp.etl.BaseFlowIT;

public abstract class BaseBuildActivityObjectIndexesIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'activity_object_swap_testsrc'";

	@Autowired
	@Qualifier("buildActivityObjectIndexesFlow")
	private Flow buildActivityObjectIndexesFlow;

	@BeforeEach
	public void setUp() {
		testJob = jobBuilderFactory.get("BuildActivityObjectIndexesFlowTest")
				.start(buildActivityObjectIndexesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}
}
