package gov.acwi.wqp.etl.result.index;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import gov.acwi.wqp.etl.BaseFlowIT;

public abstract class BaseBuildResultIndexesIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'result_swap_testsrc'";

	//The configuration class that has the build[Step Name] methods & definition of all step beans
	@Autowired
	BuildResultIndexes buildIndexes;

	@Autowired
	@Qualifier("buildResultIndexesFlow")
	private Flow buildResultIndexesFlow;

	@BeforeEach
	public void setUp() {
		testJob = jobBuilderFactory.get("BuildResultIndexesFlowTest")
				.start(buildResultIndexesFlow)
				.build()
				.build();

		jobLauncherTestUtils.setStepCreator(buildIndexes);
		jobLauncherTestUtils.setJob(testJob);
	}
}
