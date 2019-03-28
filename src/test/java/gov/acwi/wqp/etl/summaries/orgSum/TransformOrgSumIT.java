package gov.acwi.wqp.etl.summaries.orgSum;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.nio.charset.Charset;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
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
import gov.acwi.wqp.etl.summaries.orgSum.index.BuildOrgSumIndexesFlowIT;
import gov.acwi.wqp.etl.summaries.orgSum.table.SetupOrgSumSwapTableFlowIT;

public class TransformOrgSumIT extends BaseFlowIT {

	@Autowired
	@Qualifier("orgSumFlow")
	private Flow orgSumFlow;

	@PostConstruct
	public void beforeClass() throws ScriptException, SQLException {
		EncodedResource encodedResource = new EncodedResource(resource, Charset.forName("UTF-8"));
		ScriptUtils.executeSqlScript(dataSource.getConnection(), encodedResource);
	}

	@Before
	public void setup() {
		testJob = jobBuilderFactory.get("orgSumFlowTest")
				.start(orgSumFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/orgSum/empty.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/result/result.xml")
	@ExpectedDatabase(value="classpath:/testResult/wqp/orgSum/orgSum.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void transformOrgSumStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformOrgSumStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/orgSum/empty.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/result/result.xml")
	@ExpectedDatabase(value="classpath:/testResult/wqp/orgSum/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=BuildOrgSumIndexesFlowIT.EXPECTED_DATABASE_TABLE,
			query=BuildOrgSumIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/orgSum/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=SetupOrgSumSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
			query=SetupOrgSumSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/orgSum/orgSum.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void orgSumFlowTest() {
		Job orgSumFlowTest = jobBuilderFactory.get("orgSumFlowTest")
					.start(orgSumFlow)
					.build()
					.build();
		jobLauncherTestUtils.setJob(orgSumFlowTest);
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

}
