package gov.acwi.wqp.etl.dbFinalize;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.acwi.wqp.etl.BaseFlowIT;
import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.dbFinalize.install.InstallIT;

public class DatabaseFinalizeFlowIT extends BaseFlowIT {

	@Autowired
	@Qualifier(EtlConstantUtils.CREATE_DATABASE_FINALIZE_FLOW)
	private Flow databaseFinalizeFlow;

	@Value("classpath:db/testInstall/setup.sql")
	protected Resource setupScript;

	@PostConstruct
	public void beforeThisClass() throws ScriptException, SQLException {
		EncodedResource encodedResource = new EncodedResource(setupScript, Charset.forName("UTF-8"));
		ScriptUtils.executeSqlScript(dataSource.getConnection(), encodedResource);
	}

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("databaseFinalizeFlowTest")
				.start(databaseFinalizeFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@DatabaseSetup(value="classpath:/testData/wqp/install.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/lastEtl/lastEtl.xml")

	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/installTables/",
		assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
		table=EXPECTED_DATABASE_TABLE_CHECK_TABLE, query=InstallIT.EXPECTED_DATABASE_QUERY_TABLE)

	@ExpectedDatabase(value="classpath:/testResult/wqp/install.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/lastEtl/lastEtl.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=UpdateLastETLIT.TABLE_NAME_LAST_ETL, query=UpdateLastETLIT.EXPECTED_DATABASE_QUERY_LAST_ETL)
	public void databaseFinalizeFlowTest() {
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
