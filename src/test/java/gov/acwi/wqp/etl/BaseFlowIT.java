package gov.acwi.wqp.etl;

import java.nio.charset.Charset;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.runner.RunWith;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@SpringBatchTest
@SpringBootTest
@RunWith(SpringRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	StepScopeTestExecutionListener.class,
	DbUnitTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class
	})
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Import({DBTestConfig.class})
@DbUnitConfiguration(databaseConnection={"wqp","pg","nwis"})
@DirtiesContext
public abstract class BaseFlowIT {

	public static final String EXPECTED_DATABASE_TABLE_CHECK_TABLE = "tables";
	public static final String EXPECTED_DATABASE_TABLE_CHECK_INDEX = "pg_indexes";

	public static final String BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE = "select table_catalog, table_schema, table_name, table_type from information_schema.tables where table_name=";
	public static final String BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX = "select tablename, indexname, indexdef from pg_indexes where tablename=";

	public static final String BASE_EXPECTED_DATABASE_QUERY_STATION_SUM = "select data_source_id,data_source,station_id,site_id,"
			+ "station_name,organization,organization_name,site_type,station_type_name,huc,governmental_unit_code,"
			+ "geom,country_name,state_name,county_name,activity_count,activity_count_past_12_months,"
			+ "activity_count_past_60_months,result_count,result_count_past_12_months,result_count_past_60_months,"
			+ "summary_all_months from ";

	@Autowired
	protected JdbcTemplate jdbcTemplate;
	@Autowired
	protected JobBuilderFactory jobBuilderFactory;
	@Autowired
	protected JobLauncherTestUtils jobLauncherTestUtils;
	@Autowired
	protected DataSource dataSource;
	@Value("classpath:db/test_db.sql")
	protected Resource resource;

	protected JobParameters testJobParameters;

	protected Job testJob;

	@PostConstruct
	public void beforeClass() throws ScriptException, SQLException {
		EncodedResource encodedResource = new EncodedResource(resource, Charset.forName("UTF-8"));
		ScriptUtils.executeSqlScript(dataSource.getConnection(), encodedResource);
	}

	@Before
	public void baseSetup() {
		testJobParameters= new JobParametersBuilder()
				.addJobParameters(jobLauncherTestUtils.getUniqueJobParameters())
				.addString("wqpDataSourceId", "1", true)
				.addString("wqpDataSource", "stewards", true)
				.addString("schemaName", "wqp", false)
				.addString("geoSchemaName", "nwis", false)
				.toJobParameters();
	}

}
