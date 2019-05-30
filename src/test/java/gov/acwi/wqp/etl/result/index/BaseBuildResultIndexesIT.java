package gov.acwi.wqp.etl.result.index;

import org.junit.Before;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import gov.acwi.wqp.etl.BaseFlowIT;
import gov.acwi.wqp.etl.EtlConstantUtils;

public abstract class BaseBuildResultIndexesIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'result_swap_testsrc'";

	@Autowired
	@Qualifier(EtlConstantUtils.BUILD_RESULT_INDEXES_FLOW)
	private Flow buildResultIndexesFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("BuildResultIndexesFlowTest")
				.start(buildResultIndexesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}
}
