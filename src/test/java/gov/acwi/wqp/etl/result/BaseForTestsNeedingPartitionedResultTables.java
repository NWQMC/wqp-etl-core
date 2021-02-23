package gov.acwi.wqp.etl.result;


import gov.acwi.wqp.etl.BaseFlowIT;
import gov.acwi.wqp.etl.EtlConstantUtils;
import org.junit.jupiter.api.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.TestPropertySource;

/**
 * Base for tests that need the result partitions created.
 */
//See ConfigurationService:
//Env. Config params to control how result partitions are created, ensuring the partition structure is known & repeatable.
//ETL_RUN_TIME is used to construct unique partition names and overrides the actual runTime of the ETL job.
@TestPropertySource(properties = {"ETL_RESULT_PARTITION_START_DATE=1990-01-01",
                                  "ETL_RESULT_PARTITION_ONE_YEAR_BREAK=2015-01-01",
                                  "ETL_RESULT_PARTITION_QUARTER_BREAK=2015-01-01",
                                  "ETL_RESULT_PARTITION_END_DATE=2015-01-01",
                                  "ETL_RUN_TIME=2021-01-01T10:15:30",
                                  "DB_OPERATION_CONCURRENCY=2"})
public abstract class BaseForTestsNeedingPartitionedResultTables extends BaseFlowIT {

	@Autowired
	@Qualifier(EtlConstantUtils.SETUP_RESULT_SWAP_TABLE_FLOW)
	private Flow setupResultSwapTableFlow;

	private boolean resultTablesSetup = false;

	@BeforeEach
	public void BaseForTestsNeedingPartitionedResultTablesSetUp() throws Exception {
		//Though we test to see if we already setup tables, it will be rerun unless the test class is annotated like this:
		//@TestInstance(Lifecycle.PER_CLASS)
		//Can't apply that here b/c not all test classes can handle shared state
		if (! resultTablesSetup) {
			setupResultTable();
			resultTablesSetup = true;
		}
	}

	/**
	 * Creates a full suite of result partition tables
	 */
	protected void setupResultTable() throws Exception {
		Job setupResultTbl = jobBuilderFactory.get("setupResultSwapTableFlowTest")
				                     .start(setupResultSwapTableFlow)
				                     .build()
				                     .build();

		//Going to use the testUtil to run this setup job, but will need to restore the original configured job, so save it.
		Job orgJob = jobLauncherTestUtils.getJob();

		jobLauncherTestUtils.setJob(setupResultTbl);
		JobExecution jobExecution = jobLauncherTestUtils.launchJob(createNewTestJobParams());

		assert(! jobExecution.getStatus().isUnsuccessful());

		//rest to original test job for actual testing
		jobLauncherTestUtils.setJob(orgJob);
	}

}
