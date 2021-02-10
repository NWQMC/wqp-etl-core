package gov.acwi.wqp.etl.result.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.github.springtestdbunit.annotation.*;
import gov.acwi.wqp.etl.*;

import org.junit.jupiter.api.*;
import org.springframework.batch.core.*;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import org.springframework.test.context.TestPropertySource;

//See ConfigurationService:
//Env. Config params to control how result partitions are created, ensuring the partition structure is known & repeatable.
//ETL_RUN_TIME is used to construct unique partition names and overrides the actual runTime of the ETL job.
@TestPropertySource(properties = {"ETL_RESULT_PARTITION_START_DATE=1990-01-01",
                                  "ETL_RESULT_PARTITION_ONE_YEAR_BREAK=2015-01-01",
                                  "ETL_RESULT_PARTITION_QUARTER_BREAK=2015-01-01",
                                  "ETL_RESULT_PARTITION_END_DATE=2015-01-01",
                                  "ETL_RUN_TIME=2021-01-01T10:15:30"})
public class SetupResultSwapTableFlowIT extends BaseFlowIT {

	public static final String EXPECTED_DATABASE_QUERY = BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE + "'result_swap_testsrc'";
	public static final String PARTITION_LIST_QUERY = "SELECT\n" +
				       "    lower(parent.relname)      AS parent,\n" +
				       "    lower(child.relname)       AS child\n" +
				       "FROM pg_inherits\n" +
				       "    JOIN pg_class parent            ON pg_inherits.inhparent = parent.oid\n" +
				       "    JOIN pg_class child             ON pg_inherits.inhrelid   = child.oid\n" +
				       "    JOIN pg_namespace nmsp_child    ON nmsp_child.oid   = child.relnamespace\n" +
				       "WHERE lower(parent.relname)='result_swap_testsrc' and lower(nmsp_child.nspname)='wqp'";

	@Autowired
	@Qualifier(EtlConstantUtils.SETUP_RESULT_SWAP_TABLE_FLOW)
	private Flow setupResultSwapTableFlow;

	@BeforeEach
	public void setUp() {

		resetTestJob();

		//Drop the result table so we start clean
		//Ideal to do this after each test, however, dbUnit seems to run @AfterEach before @ExpectedDatabase :-/
		JobExecution jobExecution = jobLauncherTestUtils
				                            .launchStep("dropResultSwapTableStep", testJobParameters);

		//Can't reuse that job, so create a new unique one for the actual test.
		resetTestJob();
	}


	/**
	 * The test job can only be run once, but need to use it to run the 'drop' task during setup, so encapsilate
	 * so we can call it twice during setup.
	 */
	protected void resetTestJob() {

		baseSetup();    //Just gives us a new unique set of job params

		testJob = jobBuilderFactory.get("setupResultSwapTableFlowTest")
				          .start(setupResultSwapTableFlow)
				          .build()
				          .build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/result/drop.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE, query=EXPECTED_DATABASE_QUERY)
	public void dropResultSwapTableStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("dropResultSwapTableStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	/**
	 * Note:  This step creates the result swap table, but does not create the partitions.
	 */
	@Test
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/result/createSwap.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE, query=EXPECTED_DATABASE_QUERY)
	public void createResultSwapTableStepTest() {

		try {
			JobExecution jobExecution = jobLauncherTestUtils
					.launchStep("createResultSwapTableStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/result/createSwap.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE, query=EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection=CONNECTION_INFORMATION_SCHEMA, value="classpath:/testResult/wqp/result/createdPartitions.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE, query=PARTITION_LIST_QUERY)
	public void setupResultSwapTableFlowTest() {

		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
