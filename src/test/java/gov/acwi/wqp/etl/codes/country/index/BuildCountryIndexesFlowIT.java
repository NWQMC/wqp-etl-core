package gov.acwi.wqp.etl.codes.country.index;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.acwi.wqp.etl.BaseFlowIT;

public class BuildCountryIndexesFlowIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = "select tablename, indexname, indexdef from pg_indexes where tablename='country_swap_stewards'";
	public static final String EXPECTED_DATABASE_TABLE = "pg_indexes";

	@Autowired
	@Qualifier("buildCountryIndexesFlow")
	private Flow buildCountryIndexesFlow;

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("BuildCountryIndexesFlowTest")
				.start(buildCountryIndexesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@ExpectedDatabase(value="classpath:/testResult/wqp/country/indexes/codeValue.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE, query=EXPECTED_DATABASE_QUERY + " and indexname='country_swap_stewards_code_value'")
	public void buildCountryCodeValueIndexStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("buildCountryCodeValueIndexStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(value="classpath:/testResult/wqp/country/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE, query=EXPECTED_DATABASE_QUERY)
	public void buildCountryIndexesFlowTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
