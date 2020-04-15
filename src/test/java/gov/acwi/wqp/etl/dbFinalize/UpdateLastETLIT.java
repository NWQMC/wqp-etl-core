package gov.acwi.wqp.etl.dbFinalize;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.acwi.wqp.etl.BaseFlowIT;
import gov.acwi.wqp.etl.EtlConstantUtils;

public class UpdateLastETLIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY_LAST_ETL = "select data_source_id, (now() - completed_utc) < make_interval(mins => 1) within_one_minute from last_etl";
	public static final String TABLE_NAME_LAST_ETL = "last_etl";

	@Autowired
	@Qualifier(EtlConstantUtils.CREATE_DATABASE_FINALIZE_FLOW)
	private Flow databaseFinalizeFlow;

	@BeforeEach
	public void setUp() {
		testJob = jobBuilderFactory.get("databaseFinalizeFlowTest")
				.start(databaseFinalizeFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@DatabaseSetup(value="classpath:/testData/wqp/lastEtl/empty.xml")
	@ExpectedDatabase(value="classpath:/testResult/wqp/lastEtl/lastEtl.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=TABLE_NAME_LAST_ETL, query=EXPECTED_DATABASE_QUERY_LAST_ETL)
	public void finalizeStepEmptyTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("updateLastETLStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@DatabaseSetup(value="classpath:/testData/wqp/lastEtl/lastEtl.xml")
	@ExpectedDatabase(value="classpath:/testResult/wqp/lastEtl/lastEtl.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=TABLE_NAME_LAST_ETL, query=EXPECTED_DATABASE_QUERY_LAST_ETL)
	public void finalizeStepNotEmptyTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("updateLastETLStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
