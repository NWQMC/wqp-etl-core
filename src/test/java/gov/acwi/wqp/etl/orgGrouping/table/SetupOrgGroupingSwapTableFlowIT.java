package gov.acwi.wqp.etl.orgGrouping.table;

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

public class SetupOrgGroupingSwapTableFlowIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = "select table_catalog, table_schema, table_name, table_type from information_schema.tables where table_name='org_grouping_swap_stewards'";
	public static final String EXPECTED_DATABASE_TABLE = "tables";

	@Autowired
	@Qualifier("setupOrgGroupingSwapTableFlow")
	private Flow setupOrgGroupingSwapTableFlow;

	@Before
	public void setup() {
		testJob = jobBuilderFactory.get("setupOrgGroupingSwapTableFlowTest")
				.start(setupOrgGroupingSwapTableFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/orgGrouping/drop.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE, query=EXPECTED_DATABASE_QUERY)
	public void dropOrgGroupingSwapTableStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("dropOrgGroupingSwapTableStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/orgGrouping/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE, query=EXPECTED_DATABASE_QUERY)
	public void createOrgGroupingSwapTableStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("createOrgGroupingSwapTableStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/orgGrouping/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE, query=EXPECTED_DATABASE_QUERY)
	public void setupOrgGroupingSwapTableFlowTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
