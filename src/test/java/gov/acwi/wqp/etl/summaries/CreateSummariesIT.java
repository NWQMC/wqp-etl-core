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
import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.summaries.activitySum.index.BuildActivitySumIndexesFlowIT;
import gov.acwi.wqp.etl.summaries.activitySum.table.SetupActivitySumSwapTableFlowIT;
import gov.acwi.wqp.etl.summaries.mlGrouping.index.BuildMlGroupingIndexesFlowIT;
import gov.acwi.wqp.etl.summaries.mlGrouping.table.SetupMlGroupingSwapTableFlowIT;
import gov.acwi.wqp.etl.summaries.monitoringLocationSum.TransformMonitoringLocationSumIT;
import gov.acwi.wqp.etl.summaries.monitoringLocationSum.index.BuildMonitoringLocationSumIndexesFlowIT;
import gov.acwi.wqp.etl.summaries.monitoringLocationSum.table.SetupMonitoringLocationSumSwapTableFlowIT;
import gov.acwi.wqp.etl.summaries.orgGrouping.index.BuildOrgGroupingIndexesFlowIT;
import gov.acwi.wqp.etl.summaries.orgGrouping.table.SetupOrgGroupingSwapTableFlowIT;
import gov.acwi.wqp.etl.summaries.organizationSum.index.BuildOrganizationSumIndexesFlowIT;
import gov.acwi.wqp.etl.summaries.organizationSum.table.SetupOrganizationSumSwapTableFlowIT;
import gov.acwi.wqp.etl.summaries.qwportalSummary.table.SetupQwportalSummarySwapTableFlowIT;
import gov.acwi.wqp.etl.summaries.resultSum.index.BuildResultSumIndexesFlowIT;
import gov.acwi.wqp.etl.summaries.resultSum.table.SetupResultSumSwapTableFlowIT;

public class CreateSummariesIT extends BaseFlowIT {

	@Autowired
	@Qualifier(EtlConstantUtils.CREATE_SUMMARIES_FLOW)
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
	@DatabaseSetup(value="classpath:/testData/wqp/monitoringLocation/monitoringLocation.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/activity/csv/")
	@DatabaseSetup(value="classpath:/testData/wqp/activityMetric/activityMetric.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/result/csv/")

	@DatabaseSetup(connection=CONNECTION_NWIS, value="classpath:/testData/nwis/country/country.xml")
	@DatabaseSetup(connection=CONNECTION_NWIS, value="classpath:/testData/nwis/state/state.xml")
	@DatabaseSetup(connection=CONNECTION_NWIS, value="classpath:/testData/nwis/county/county.xml")
//TODO - WQP-1430	@DatabaseSetup(value="classpath:/testData/wqx/country/country.xml")
//TODO - WQP-1430	@DatabaseSetup(value="classpath:/testData/wqx/state/state.xml")
//TODO - WQP-1430	@DatabaseSetup(value="classpath:/testData/wqx/county/county.xml")

	@ExpectedDatabase(value="classpath:/testResult/wqp/activitySum/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildActivitySumIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/activitySum/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupActivitySumSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/resultSum/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildResultSumIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/resultSum/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupResultSumSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/orgGrouping/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildOrgGroupingIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/orgGrouping/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupOrgGroupingSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/mlGrouping/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildMlGroupingIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/mlGrouping/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupMlGroupingSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/organizationSum/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildOrganizationSumIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/organizationSum/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupOrganizationSumSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/monitoringLocationSum/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BuildMonitoringLocationSumIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/monitoringLocationSum/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupMonitoringLocationSumSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/qwportalSummary/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=SetupQwportalSummarySwapTableFlowIT.EXPECTED_DATABASE_QUERY)

	@ExpectedDatabase(value="classpath:/testResult/wqp/activitySum/activitySum.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/resultSum/csv/", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/orgGrouping/csv/", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/mlGrouping/csv/", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/organizationSum/organizationSum.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/monitoringLocationSum/monitoringLocationSum.xml",
			table=TransformMonitoringLocationSumIT.EXPECTED_DATABASE_TABLE_STATION_SUM,
			query=TransformMonitoringLocationSumIT.EXPECTED_DATABASE_QUERY_STATION_SUM)
	@ExpectedDatabase(value="classpath:/testResult/wqp/qwportalSummary/qwportalSummary.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
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
