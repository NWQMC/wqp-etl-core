package gov.acwi.wqp.etl.projectMLWeighting.index;

import org.junit.Before;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import gov.acwi.wqp.etl.BaseFlowIT;
import gov.acwi.wqp.etl.EtlConstantUtils;

public abstract class BaseBuildProjectMLWeightingIndexesIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'prj_ml_weighting_swap_testsrc'";

	@Autowired
	@Qualifier(EtlConstantUtils.BUILD_PROJECT_ML_WEIGHTING_INDEXES_FLOW)
	private Flow buildProjectMLWeightingIndexesFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("BuildBuildProjectMLWeightingIndexesFlowTest")
				.start(buildProjectMLWeightingIndexesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}
}
