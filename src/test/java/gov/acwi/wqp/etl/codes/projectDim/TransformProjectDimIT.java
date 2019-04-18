package gov.acwi.wqp.etl.codes.projectDim;

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
import gov.acwi.wqp.etl.codes.projectDim.index.BuildProjectDimIndexesFlowIT;
import gov.acwi.wqp.etl.codes.projectDim.table.SetupProjectDimSwapTableFlowIT;

public class TransformProjectDimIT extends BaseFlowIT {

	@Autowired
	@Qualifier("createProjectDimFlow")
	private Flow createProjectDimFlow;

	@PostConstruct
	public void beforeClass() throws ScriptException, SQLException {
		EncodedResource encodedResource = new EncodedResource(resource, Charset.forName("UTF-8"));
		ScriptUtils.executeSqlScript(dataSource.getConnection(), encodedResource);
	}

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("createProjectDimFlowTest")
				.start(createProjectDimFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/projectDim/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/activitySum/activitySum.xml")
	@ExpectedDatabase(value="classpath:/testResult/wqp/projectDim/projectDim.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void transformProjectDimStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformProjectDimStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/projectDim/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/activitySum/activitySum.xml")
	@ExpectedDatabase(value="classpath:/testResult/wqp/projectDim/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildProjectDimIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/projectDim/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupProjectDimSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/projectDim/projectDim.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void projectDimFlowTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

}
