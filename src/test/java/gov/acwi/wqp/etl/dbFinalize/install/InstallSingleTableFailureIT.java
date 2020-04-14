package gov.acwi.wqp.etl.dbFinalize.install;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.charset.Charset;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

// NOTE: These tests do operate on all of the tables, but we are only checking the one.
public class InstallSingleTableFailureIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY_INDEX = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX_LIKE + "'project_data%'";
	public static final String EXPECTED_DATABASE_QUERY_TABLE = BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE_LIKE + "'project_data%'";

	@Autowired
	@Qualifier(EtlConstantUtils.CREATE_DATABASE_FINALIZE_FLOW)
	private Flow databaseFinalizeFlow;

	@Value("classpath:db/testInstall/single/common.sql")
	protected Resource commonSetup;

	@Value("classpath:db/testInstall/single/noOldNoPartitionNoIndexes.sql")
	protected Resource noOldNoPartitionNoIndexes;

	@PostConstruct
	public void beforeThisClass() throws ScriptException, SQLException {
		EncodedResource encodedResource = new EncodedResource(commonSetup, Charset.forName("UTF-8"));
		ScriptUtils.executeSqlScript(dataSource.getConnection(), encodedResource);
	}

	@BeforeEach
	public void setUp() {
		testJob = jobBuilderFactory.get("databaseFinalizeFlowTest")
				.start(databaseFinalizeFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@DatabaseSetup(value="classpath:/testData/wqp/projectData/install/projectDataOthersrc.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/projectData/install/projectDataSwap.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/projectData/install/projectDataSwapBad.xml")
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/projectData/install/tables/badData.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE, query=EXPECTED_DATABASE_QUERY_TABLE)
	@ExpectedDatabase(value="classpath:/testResult/wqp/projectData/install/indexes/allOthersrc.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX, query=EXPECTED_DATABASE_QUERY_INDEX)
	@ExpectedDatabase(value="classpath:/testData/wqp/projectData/install/projectDataOthersrc.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void badDataTest() {
		//This test shows that the install will fail if the wrong datasource got loaded into the swap table.
		//Moved to a separate class due to connection closed issues with the sql error thrown.
		try {
			ScriptUtils.executeSqlScript(dataSource.getConnection(), new EncodedResource(noOldNoPartitionNoIndexes, Charset.forName("UTF-8")));
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("installStep", testJobParameters);
			assertEquals(ExitStatus.FAILED.getExitCode(), jobExecution.getExitStatus().getExitCode());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
