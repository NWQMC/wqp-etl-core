package gov.acwi.wqp.etl.summaries;

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
import gov.acwi.wqp.etl.summaries.activitySum.index.BuildActivitySumIndexesFlowIT;
import gov.acwi.wqp.etl.summaries.activitySum.table.SetupActivitySumSwapTableFlowIT;
import gov.acwi.wqp.etl.summaries.mlGrouping.index.BuildMlGroupingIndexesFlowIT;
import gov.acwi.wqp.etl.summaries.mlGrouping.table.SetupMlGroupingSwapTableFlowIT;
import gov.acwi.wqp.etl.summaries.monitoringLocationSum.TransformMonitoringLocationSumIT;
import gov.acwi.wqp.etl.summaries.monitoringLocationSum.index.BuildMonitoringLocationSumIndexesFlowIT;
import gov.acwi.wqp.etl.summaries.monitoringLocationSum.table.SetupMonitoringLocationSumSwapTableFlowIT;
import gov.acwi.wqp.etl.summaries.orgGrouping.index.BuildOrgGroupingIndexesFlowIT;
import gov.acwi.wqp.etl.summaries.orgGrouping.table.SetupOrgGroupingSwapTableFlowIT;
import gov.acwi.wqp.etl.summaries.resultSum.index.BuildResultSumIndexesFlowIT;
import gov.acwi.wqp.etl.summaries.resultSum.table.SetupResultSumSwapTableFlowIT;

public class CreateSummariesIT extends BaseFlowIT {

	@Autowired
	@Qualifier("createSummariesFlow")
	private Flow createSummariesFlow;

	@PostConstruct
	public void beforeClass() throws ScriptException, SQLException {
		EncodedResource encodedResource = new EncodedResource(resource, Charset.forName("UTF-8"));
		ScriptUtils.executeSqlScript(dataSource.getConnection(), encodedResource);
	}

	@Before
	public void setUp() {
		testJob = jobBuilderFactory.get("createSummariesFlowTest")
				.start(createSummariesFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@DatabaseSetup(value="classpath:/testResult/wqp/activitySum/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/resultSum/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/orgGrouping/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/mlGrouping/empty.xml")
//TODO	@DatabaseSetup(value="classpath:/testResult/wqp/orgSum/empty.xml")
	@DatabaseSetup(value="classpath:/testResult/wqp/monitoringLocationSum/empty.xml")

	@DatabaseSetup(value="classpath:/testData/wqp/station/station.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/activity/activity.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/result/result.xml")

	@DatabaseSetup(value="classpath:/testData/nwisWsStar/country/country.xml")
	@DatabaseSetup(value="classpath:/testData/nwisWsStar/state/state.xml")
	@DatabaseSetup(value="classpath:/testData/nwisWsStar/county/county.xml")
	@DatabaseSetup(value="classpath:/testData/wqx/country/country.xml")
	@DatabaseSetup(value="classpath:/testData/wqx/state/state.xml")
	@DatabaseSetup(value="classpath:/testData/wqx/county/county.xml")

	@ExpectedDatabase(value="classpath:/testResult/wqp/activitySum/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=BuildActivitySumIndexesFlowIT.EXPECTED_DATABASE_TABLE,
			query=BuildActivitySumIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/activitySum/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=SetupActivitySumSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
			query=SetupActivitySumSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/resultSum/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=BuildResultSumIndexesFlowIT.EXPECTED_DATABASE_TABLE,
			query=BuildResultSumIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/resultSum/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=SetupResultSumSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
			query=SetupResultSumSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/orgGrouping/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=BuildOrgGroupingIndexesFlowIT.EXPECTED_DATABASE_TABLE,
			query=BuildOrgGroupingIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/orgGrouping/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=SetupOrgGroupingSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
			query=SetupOrgGroupingSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/mlGrouping/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=BuildMlGroupingIndexesFlowIT.EXPECTED_DATABASE_TABLE,
			query=BuildMlGroupingIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/mlGrouping/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=SetupMlGroupingSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
			query=SetupMlGroupingSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
//	@ExpectedDatabase(value="classpath:/testResult/wqp/orgSum/indexes/all.xml",
//			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
//			table=BuildOrgSumIndexesFlowIT.EXPECTED_DATABASE_TABLE,
//			query=BuildOrgSumIndexesFlowIT.EXPECTED_DATABASE_QUERY)
//	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/orgSum/create.xml",
//			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
//			table=SetupOrgSumSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
//			query=SetupOrgSumSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/monitoringLocationSum/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=BuildMonitoringLocationSumIndexesFlowIT.EXPECTED_DATABASE_TABLE,
			query=BuildMonitoringLocationSumIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/monitoringLocationSum/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=SetupMonitoringLocationSumSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
			query=SetupMonitoringLocationSumSwapTableFlowIT.EXPECTED_DATABASE_QUERY)

	@ExpectedDatabase(value="classpath:/testResult/wqp/activitySum/activitySum.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/resultSum/resultSum.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/orgGrouping/orgGrouping.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/mlGrouping/mlGrouping.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
//TODO	@ExpectedDatabase(value="classpath:/testResult/wqp/orgSum/orgSum.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/monitoringLocationSum/monitoringLocationSum.xml",
			table=TransformMonitoringLocationSumIT.EXPECTED_DATABASE_TABLE,
			query=TransformMonitoringLocationSumIT.EXPECTED_DATABASE_QUERY)
	public void createSummariesFlowTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

}
