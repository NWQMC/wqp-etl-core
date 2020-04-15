package gov.acwi.wqp.etl.codes.county;

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
import gov.acwi.wqp.etl.codes.county.index.BuildCountyIndexesFlowIT;
import gov.acwi.wqp.etl.codes.county.table.SetupCountySwapTableFlowIT;

public class TransformCountyIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY_ANALYZE = BASE_EXPECTED_DATABASE_QUERY_ANALYZE + "'county_swap_testsrc'";

	@Autowired
	@Qualifier("createCountyFlow")
	private Flow createCountyFlow;

	@BeforeEach
	public void setUp() {
		testJob = jobBuilderFactory.get("createCountyFlowTest")
				.start(createCountyFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/county/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/monitoringLocationSum/monitoringLocationSum.xml")
	@ExpectedDatabase(value="classpath:/testResult/wqp/county/county.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void transformCountyStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformCountyStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/county.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void analyzeCountyStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("analyzeCountyStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/monitoringLocationSum/monitoringLocationSum.xml")
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/county/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildCountyIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(
			connection=CONNECTION_INFORMATION_SCHEMA,
			value="classpath:/testResult/wqp/county/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupCountySwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/county/county.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/county.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void countyFlowTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

}
