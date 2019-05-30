package gov.acwi.wqp.etl.codes.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.nio.charset.Charset;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.acwi.wqp.etl.BaseFlowIT;
import gov.acwi.wqp.etl.codes.state.index.BuildStateIndexesFlowIT;
import gov.acwi.wqp.etl.codes.state.table.SetupStateSwapTableFlowIT;

public class TransformStateIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY_ANALYZE = BASE_EXPECTED_DATABASE_QUERY_ANALYZE + "'state_swap_testsrc'";

	@Autowired
	@Qualifier("createStateFlow")
	private Flow createStateFlow;

	@PostConstruct
	public void beforeClass() throws ScriptException, SQLException {
		EncodedResource encodedResource = new EncodedResource(resource, Charset.forName("UTF-8"));
		ScriptUtils.executeSqlScript(dataSource.getConnection(), encodedResource);
	}

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("createStateFlowTest")
				.start(createStateFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/state/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/monitoringLocationSum/monitoringLocationSum.xml")
	@ExpectedDatabase(value="classpath:/testResult/wqp/state/state.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void transformStateStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformStateStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(value="classpath:/testResult/analyze/state.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=TABLE_NAME_PG_STAT_ALL_TABLES,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void analyzeStateStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("analyzeStateStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/monitoringLocationSum/monitoringLocationSum.xml")
	@ExpectedDatabase(value="classpath:/testResult/wqp/state/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildStateIndexesFlowIT.EXPECTED_DATABASE_QUERY_ANALYZE)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/state/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupStateSwapTableFlowIT.EXPECTED_DATABASE_QUERY_ANALYZE)
	@ExpectedDatabase(value="classpath:/testResult/wqp/state/state.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/analyze/state.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=TABLE_NAME_PG_STAT_ALL_TABLES,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	public void stateFlowTest() {
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
