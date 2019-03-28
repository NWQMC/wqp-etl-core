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
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.acwi.wqp.etl.BaseFlowIT;

public class DatabaseFinalizeIT extends BaseFlowIT {

	@Autowired
	@Qualifier("databaseFinalizeFlow")
	private Flow databaseFinalizeFlow;

	@PostConstruct
	public void beforeClass() throws ScriptException, SQLException {
		EncodedResource encodedResource = new EncodedResource(resource, Charset.forName("UTF-8"));
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
	@DatabaseSetup(value="classpath:/testResult/wqp/lastEtl/empty.xml")
	@ExpectedDatabase(value="classpath:/testResult/wqp/lastEtl/lastEtl.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void finalizeStepEmptyTest() {
		//TODO check new time.
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("finalizeStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/lastEtl/lastEtl.xml")
	@ExpectedDatabase(value="classpath:/testResult/wqp/lastEtl/lastEtl.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void finalizeStepNotEmptyTest() {
		//TODO fill in old time and check new time.
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("finalizeStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

//	@Test
//	@DatabaseSetup(value="classpath:/testResult/wqp/taxaName/empty.xml")
//	@DatabaseSetup(value="classpath:/testResult/wqp/resultSum/resultSum.xml")
//	@ExpectedDatabase(value="classpath:/testResult/wqp/taxaName/indexes/all.xml",
//			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
//			table=BuildTaxaNameIndexesFlowIT.EXPECTED_DATABASE_TABLE,
//			query=BuildTaxaNameIndexesFlowIT.EXPECTED_DATABASE_QUERY)
//	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/taxaName/create.xml",
//			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
//			table=SetupTaxaNameSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
//			query=SetupTaxaNameSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
//	@ExpectedDatabase(value="classpath:/testResult/wqp/taxaName/taxaName.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
//	public void taxaNameFlowTest() {
//		try {
//			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
//			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail(e.getLocalizedMessage());
//		}
//	}

}
