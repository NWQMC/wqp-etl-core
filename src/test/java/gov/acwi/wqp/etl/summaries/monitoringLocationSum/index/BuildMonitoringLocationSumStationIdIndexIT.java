package gov.acwi.wqp.etl.summaries.monitoringLocationSum.index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

public class BuildMonitoringLocationSumStationIdIndexIT extends BaseBuildMonitoringLocationSumIndexesIT {

	@Test
	@ExpectedDatabase(value="classpath:/testResult/wqp/monitoringLocationSum/indexes/stationId.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=EXPECTED_DATABASE_QUERY + " and indexname='station_sum_swap_testsrc_station_id'")
	public void buildMonitoringLocationSumStationIdIndexStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("buildMonitoringLocationSumStationIdIndexStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
