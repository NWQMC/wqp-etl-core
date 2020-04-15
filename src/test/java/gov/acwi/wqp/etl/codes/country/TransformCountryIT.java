package gov.acwi.wqp.etl.codes.country;

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
import gov.acwi.wqp.etl.codes.country.index.BuildCountryIndexesFlowIT;
import gov.acwi.wqp.etl.codes.country.table.SetupCountrySwapTableFlowIT;

public class TransformCountryIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY_ANALYZE = BASE_EXPECTED_DATABASE_QUERY_ANALYZE + "'country_swap_testsrc'";

	@Autowired
	@Qualifier("createCountryFlow")
	private Flow createCountryFlow;

	@BeforeEach
	public void setUp() {
		testJob = jobBuilderFactory.get("createCountryFlowTest")
				.start(createCountryFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/country/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/monitoringLocationSum/monitoringLocationSum.xml")
	@ExpectedDatabase(value="classpath:/testResult/wqp/country/country.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void transformCountryStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformCountryStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/country.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void analyzeCountryStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("analyzeCountryStep", testJobParameters);
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
			value="classpath:/testResult/wqp/country/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildCountryIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(
			connection=CONNECTION_INFORMATION_SCHEMA,
			value="classpath:/testResult/wqp/country/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupCountrySwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/country/country.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(
			value="classpath:/testResult/analyze/country.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void countryFlowTest() {
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
