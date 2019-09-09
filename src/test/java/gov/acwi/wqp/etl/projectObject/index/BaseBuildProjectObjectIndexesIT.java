package gov.acwi.wqp.etl.projectObject.index;

import org.junit.Before;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import gov.acwi.wqp.etl.BaseFlowIT;

public abstract class BaseBuildProjectObjectIndexesIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'project_object_swap_testsrc'";

	@Autowired
	@Qualifier("buildProjectObjectIndexesFlow")
	private Flow buildProjectObjectIndexesFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("BuildProjectObjectIndexesFlowTest")
				.start(buildProjectObjectIndexesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}
}
