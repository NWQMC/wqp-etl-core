package gov.acwi.wqp.etl;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.StepScopeTestExecutionListener;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@SpringBatchTest
@SpringBootTest
@TestExecutionListeners({
		DependencyInjectionTestExecutionListener.class,
		StepScopeTestExecutionListener.class,
		DbUnitTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class
		})
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Import({DBTestConfig.class})
@DbUnitConfiguration(
		databaseConnection={BaseFlowIT.CONNECTION_WQP,BaseFlowIT.CONNECTION_INFORMATION_SCHEMA,BaseFlowIT.CONNECTION_NWIS},
		dataSetLoader=FileSensingDataSetLoader.class
		)
@DirtiesContext
public abstract class BaseFlowIT {

	public static final String CONNECTION_WQX = "wqx";
	public static final String CONNECTION_NWIS = "nwis";
	public static final String CONNECTION_INFORMATION_SCHEMA = "pg";
	public static final String CONNECTION_WQP = "wqp";

	public static final String EXPECTED_DATABASE_TABLE_CHECK_TABLE = "tables";
	public static final String EXPECTED_DATABASE_TABLE_CHECK_INDEX = "pg_indexes";
	public static final String EXPECTED_DATABASE_TABLE_CHECK_ANALYZE = "pg_stat_all_tables";
	public static final String EXPECTED_DATABASE_TABLE_CHECK_RI = "pg_constraint";
	public static final String EXPECTED_DATABASE_TABLE_CHECK_PRIMARY_KEY = EXPECTED_DATABASE_TABLE_CHECK_RI;
	public static final String EXPECTED_DATABASE_TABLE_CHECK_FOREIGN_KEY = EXPECTED_DATABASE_TABLE_CHECK_RI;

	public static final String EQUALS_QUERY = " = ";
	public static final String LIKE_QUERY = " like ";
	public static final String BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE_BARE = "select table_catalog, table_schema, table_name, table_type from information_schema.tables where table_schema='wqp' and table_name";
	public static final String BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE = BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE_BARE + EQUALS_QUERY;
	public static final String BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE_LIKE = BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE_BARE + LIKE_QUERY;

	public static final String BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX_BARE = "select tablename, indexname, indexdef from pg_indexes";
	public static final String INDEXES_FOR_PK_ONLY = " where indexname like '%pk' and tablename";
	public static final String INDEXES_WITHOUT_PK = " where indexname not like '%pk' and tablename";
	public static final String BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX_BARE + INDEXES_WITHOUT_PK + EQUALS_QUERY;
	public static final String BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX_LIKE = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX_BARE + INDEXES_WITHOUT_PK + LIKE_QUERY;
	public static final String BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX_PK = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX_BARE + INDEXES_FOR_PK_ONLY + EQUALS_QUERY;

	//This query is needed as the DBUnit code doesn't correctly read the JSON columns from the catalog. By specifying the columns as text we actually get them in the compare.
	public static final String BASE_EXPECTED_DATABASE_QUERY_STATION_SUM = "select data_source_id,data_source,station_id,site_id,"
			+ "station_name,organization,organization_name,site_type,station_type_name,huc,governmental_unit_code,"
			+ "geom,country_name,state_name,county_name,activity_count,activity_count_past_12_months,"
			+ "activity_count_past_60_months,result_count,result_count_past_12_months,result_count_past_60_months,"
			+ "summary_past_12_months::text, summary_past_60_months::text, summary_all_months::text from ";
	public static final String EXPECTED_DATABASE_QUERY_STATION_SUM_ORDER_BY = " order by data_source_id, station_id";

	public static final String BASE_EXPECTED_DATABASE_QUERY_ANALYZE_BARE = "select schemaname, relname, (now() - last_analyze) < make_interval(mins => 1) within_one_minute from pg_stat_all_tables ";
	public static final String BASE_EXPECTED_DATABASE_QUERY_ANALYZE = BASE_EXPECTED_DATABASE_QUERY_ANALYZE_BARE + "where relname = ";

	public static final String BASE_EXPECTED_DATABASE_QUERY_RI = "select conrelid::regclass, conname, pg_get_constraintdef(oid) from pg_constraint where contype = ";
	public static final String BASE_EXPECTED_DATABASE_QUERY_PRIMARY_KEY = BASE_EXPECTED_DATABASE_QUERY_RI + "'p' and conrelid::regclass::text ";
	public static final String BASE_EXPECTED_DATABASE_QUERY_FOREIGN_KEY = BASE_EXPECTED_DATABASE_QUERY_RI + "'f' and conrelid::regclass::text ";

	public static final LocalDate TEST_DATE = LocalDate.now();

	public static final LocalDate TEST_DATE_MINUS_12_MONTHS = TEST_DATE.minusMonths(12);
	public static final LocalDate TEST_DATE_MINUS_12_MONTHS_1_DAY = TEST_DATE_MINUS_12_MONTHS.minusDays(1);
	public static final LocalDate TEST_DATE_MINUS_60_MONTHS = TEST_DATE.minusMonths(60);
	public static final LocalDate TEST_DATE_MINUS_60_MONTHS_1_DAY = TEST_DATE_MINUS_60_MONTHS.minusDays(1);
	public static final LocalDate TEST_DATE_MINUS_CURRENT_YEAR = LocalDate.of(TEST_DATE.getYear(), 1, 1);
	public static final LocalDate TEST_DATE_MINUS_CURRENT_YEAR_1_DAY = TEST_DATE_MINUS_CURRENT_YEAR.minusDays(1);
	public static final LocalDate TEST_DATE_MINUS_FIVE_YEAR = TEST_DATE_MINUS_CURRENT_YEAR.minusYears(4);
	public static final LocalDate TEST_DATE_MINUS_FIVE_YEAR_1_DAY = TEST_DATE_MINUS_FIVE_YEAR.minusDays(1);

	@Autowired
	protected JdbcTemplate jdbcTemplate;
	@Autowired
	protected JobBuilderFactory jobBuilderFactory;
	@Autowired
	protected SmarterJobLauncherTestUtils jobLauncherTestUtils;
	@Autowired
	protected DataSource dataSource;
	@Autowired
	protected ConfigurationService configurationService;

	@Value("classpath:db/test_db.sql")
	protected Resource resource;

	protected JobParameters testJobParameters;

	protected Job testJob;

	@PostConstruct
	public void beforeClass() throws ScriptException, SQLException {
		EncodedResource encodedResource = new EncodedResource(resource, Charset.forName("UTF-8"));
		ScriptUtils.executeSqlScript(dataSource.getConnection(), encodedResource);
	}

	@BeforeEach
	public void baseSetup() {
		testJobParameters = createNewTestJobParams();
	}

	public JobParameters createNewTestJobParams() {
		return new JobParametersBuilder()
				                   .addJobParameters(jobLauncherTestUtils.getUniqueJobParameters())
				                   .addString(EtlConstantUtils.JOB_PARM_DATA_SOURCE_ID, configurationService.getEtlDataSourceId().toString(), true)
				                   .addString(EtlConstantUtils.JOB_PARM_DATA_SOURCE, configurationService.getEtlDataSource().toLowerCase(), true)
				                   .addString(EtlConstantUtils.JOB_PARM_WQP_SCHEMA, configurationService.getWqpSchemaName(), false)
				                   .addString(EtlConstantUtils.JOB_PARM_GEO_SCHEMA, configurationService.getGeoSchemaName(), false)
				                   .addString(EtlConstantUtils.JOB_PARM_NWIS_OR_EPA, configurationService.getNwisOrEpa(), false)
				                   .toJobParameters();
	}

}
