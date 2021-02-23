package gov.acwi.wqp.etl.result.index;

import gov.acwi.wqp.etl.result.BaseForTestsNeedingPartitionedResultTables;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

//See BaseForTestsNeedingPartitionedResultTables@TestPropertySource for config params
public abstract class BaseBuildResultIndexesIT extends BaseForTestsNeedingPartitionedResultTables {

	public static final String EXPECTED_DATABASE_QUERY = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'result_swap_testsrc'";

	//This big messy query is required in PG11 to get indexes b/c PG11 doesn't include indexes on a partitioned
	//table - it only includes indexes on child tables.
	//See:  https://www.postgresql.org/message-id/CAKJS1f8Jp11xrPu35rHMr7Q8GTFwYEKT%3DYCkJQo7bP0%3DF8iCUg%40mail.gmail.com
	//and:  https://www.postgresql.org/docs/12/release-12.html (search for pg_indexes)
	public static final String EXPECTED_ALL_INDEXES_QUERY = " SELECT\n" +
			                                                        "    c.relname AS tablename,\n" +
			                                                        "    i.relname AS indexname,\n" +
			                                                        "    pg_get_indexdef(i.oid) AS indexdef\n" +
			                                                        "   FROM pg_index x\n" +
			                                                        "     JOIN pg_class c ON c.oid = x.indrelid\n" +
			                                                        "     JOIN pg_class i ON i.oid = x.indexrelid\n" +
			                                                        "     LEFT JOIN pg_namespace n ON n.oid = c.relnamespace\n" +
			                                                        "     LEFT JOIN pg_tablespace t ON t.oid = i.reltablespace\n" +
			                                                        "  WHERE (c.relkind in ('r','m','p'))\n" +
			                                                        "    AND i.relkind in ('i', 'I')\n" +
			                                                        "    and c.relname != 'result_old'\n" +
			                                                        "    and c.relname like 'result_%'\n" +
			                                                        " order by tablename";
	//This query should work once we have PG11 to test against.
	//public static final String EXPECTED_ALL_INDEXES_QUERY = "select tablename, indexname, indexdef from pg_indexes " +
	//		                                               "where indexname not like '%pk' and tablename like 'result_%'";

	//The configuration class that has the build[Step Name] methods & definition of all step beans
	@Autowired
	BuildResultIndexes buildIndexes;

	@Autowired
	@Qualifier("buildResultIndexesFlow")
	private Flow buildResultIndexesFlow;

	@BeforeEach
	public void setUp() {
		testJob = jobBuilderFactory.get("BuildResultIndexesFlowTest")
				.start(buildResultIndexesFlow)
				.build()
				.build();

		jobLauncherTestUtils.setStepCreator(buildIndexes);
		jobLauncherTestUtils.setJob(testJob);
	}
}
