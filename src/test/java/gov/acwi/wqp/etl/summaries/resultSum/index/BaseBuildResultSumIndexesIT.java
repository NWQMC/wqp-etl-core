package gov.acwi.wqp.etl.summaries.resultSum.index;

import org.junit.Before;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import gov.acwi.wqp.etl.BaseFlowIT;

public abstract class BaseBuildResultSumIndexesIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'result_sum_swap_testsrc'";

	@Autowired
	@Qualifier("buildResultSumIndexesFlow")
	private Flow buildResultSumIndexesFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("BuildResultSumIndexesFlowTest")
				.start(buildResultSumIndexesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}
}
