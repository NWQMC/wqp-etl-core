package gov.acwi.wqp.etl.summaries.mlGrouping.index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.acwi.wqp.etl.BaseFlowIT;

public class BuildMlGroupingIndexesFlowIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'ml_grouping_swap_testsrc'";

	@Autowired
	@Qualifier("buildMlGroupingIndexesFlow")
	private Flow buildMlGroupingIndexesFlow;

	@BeforeEach
	public void setUp() {
		testJob = jobBuilderFactory.get("BuildMlGroupingIndexesFlowTest")
				.start(buildMlGroupingIndexesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@ExpectedDatabase(value="classpath:/testResult/wqp/mlGrouping/indexes/stationId.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX, query=EXPECTED_DATABASE_QUERY + " and indexname='ml_grouping_swap_testsrc_station_id'")
	public void buildMlGroupingStationIdIndexStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("buildMlGroupingStationIdIndexStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(value="classpath:/testResult/wqp/mlGrouping/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX, query=EXPECTED_DATABASE_QUERY)
	public void buildMlGroupingIndexesFlowTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
