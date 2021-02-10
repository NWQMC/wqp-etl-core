package gov.acwi.wqp.etl.activity.index;

import gov.acwi.wqp.etl.result.index.BuildResultIndexes;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import gov.acwi.wqp.etl.BaseFlowIT;

public abstract class BaseBuildActivityIndexesIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'activity_swap_testsrc'";

	//The configuration class that has the build[Step Name] methods & definition of all step beans
	@Autowired
	BuildActivityIndexes buildIndexes;

	@Autowired
	@Qualifier("buildActivityIndexesFlow")
	private Flow buildActivityIndexesFlow;

	@BeforeEach
	public void setUp() {
		testJob = jobBuilderFactory.get("BuildActivityIndexesFlowTest")
				.start(buildActivityIndexesFlow)
				.build()
				.build();

		jobLauncherTestUtils.setStepCreator(buildIndexes);
		jobLauncherTestUtils.setJob(testJob);
	}
}
