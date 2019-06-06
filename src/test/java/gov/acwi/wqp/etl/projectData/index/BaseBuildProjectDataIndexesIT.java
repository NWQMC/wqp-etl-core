package gov.acwi.wqp.etl.projectData.index;

import org.junit.Before;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import gov.acwi.wqp.etl.BaseFlowIT;

public abstract class BaseBuildProjectDataIndexesIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'project_data_swap_testsrc'";

	@Autowired
	@Qualifier("buildProjectDataIndexesFlow")
	private Flow buildProjectDataIndexesFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("BuildProjectDataIndexesFlowTest")
				.start(buildProjectDataIndexesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}
}
