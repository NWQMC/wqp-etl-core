package gov.acwi.wqp.etl.summaries.resultSum;

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
import gov.acwi.wqp.etl.summaries.resultSum.index.BuildResultSumIndexesFlowIT;
import gov.acwi.wqp.etl.summaries.resultSum.table.SetupResultSumSwapTableFlowIT;

public class TransformResultSumIT extends BaseFlowIT {

	@Autowired
	@Qualifier("resultSumFlow")
	private Flow resultSumFlow;

	@PostConstruct
	public void beforeClass() throws ScriptException, SQLException {
		EncodedResource encodedResource = new EncodedResource(resource, Charset.forName("UTF-8"));
		ScriptUtils.executeSqlScript(dataSource.getConnection(), encodedResource);
	}

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("resultSumFlowTest")
				.start(resultSumFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/resultSum/empty.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/result/result.xml")
	@ExpectedDatabase(value="classpath:/testResult/wqp/resultSum/resultSum.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void transformResultSumStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformResultSumStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/resultSum/empty.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/result/result.xml")
	@ExpectedDatabase(value="classpath:/testResult/wqp/resultSum/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildResultSumIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/resultSum/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupResultSumSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/resultSum/resultSum.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void resultSumFlowTest() {
		Job resultSumFlowTest = jobBuilderFactory.get("resultSumFlowTest")
					.start(resultSumFlow)
					.build()
					.build();
		jobLauncherTestUtils.setJob(resultSumFlowTest);
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

}
