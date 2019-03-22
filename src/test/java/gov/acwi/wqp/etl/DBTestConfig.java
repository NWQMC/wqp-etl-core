package gov.acwi.wqp.etl;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;

@TestConfiguration
public class DBTestConfig {

	@Autowired
	DataSource dataSource;

//	@Bean
//	@ConfigurationProperties("app.datasource.wqx")
//	public DataSourceProperties wqxDataSourceProperties() {
//		return new DataSourceProperties();
//	}
//
//	@Bean
//	@ConfigurationProperties("app.datasource.wqx")
//	public DataSource wqxDataSource() {
//		return wqxDataSourceProperties().initializeDataSourceBuilder().build();
//	}
//
//	@Bean
//	@ConfigurationProperties(prefix = "app.datasource.wqx.liquibase")
//	public LiquibaseProperties wqxLiquibaseProperties() {
//		return new LiquibaseProperties();
//	}

//	@Bean(name = "liquibase")
//	public SpringLiquibase wqxLiquibase(@Qualifier("wqxLiquibaseProperties") LiquibaseProperties liquibaseProperties) {
//		return springLiquibase(wqxDataSource(), wqxLiquibaseProperties());
//	}

//	private static SpringLiquibase springLiquibase(DataSource dataSource, LiquibaseProperties properties) {
//		SpringLiquibase liquibase = new SpringLiquibase();
//		liquibase.setDataSource(dataSource);
//		liquibase.setChangeLog(properties.getChangeLog());
//		liquibase.setContexts(properties.getContexts());
//		liquibase.setDefaultSchema(properties.getDefaultSchema());
//		liquibase.setDropFirst(properties.isDropFirst());
//		liquibase.setShouldRun(properties.isEnabled());
//		liquibase.setLabels(properties.getLabels());
//		liquibase.setChangeLogParameters(properties.getParameters());
//		liquibase.setRollbackFile(properties.getRollbackFile());
//		return liquibase;
//	}

	@Bean
	public DatabaseConfigBean dbUnitDatabaseConfig() {
		DatabaseConfigBean dbUnitDbConfig = new DatabaseConfigBean();
		dbUnitDbConfig.setDatatypeFactory(new PostgresqlDataTypeFactoryWithJson());
		return dbUnitDbConfig;
	}

	@Bean
	public DatabaseDataSourceConnectionFactoryBean wqp() throws SQLException {
		DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection = new DatabaseDataSourceConnectionFactoryBean();
		dbUnitDatabaseConnection.setDatabaseConfig(dbUnitDatabaseConfig());
		dbUnitDatabaseConnection.setDataSource(dataSource);
		dbUnitDatabaseConnection.setSchema("wqp");
		return dbUnitDatabaseConnection;
	}

	@Bean
	public DatabaseDataSourceConnectionFactoryBean pg() throws SQLException {
		DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection = new DatabaseDataSourceConnectionFactoryBean();
		dbUnitDatabaseConnection.setDatabaseConfig(dbUnitDatabaseConfig());
		dbUnitDatabaseConnection.setDataSource(dataSource);
		dbUnitDatabaseConnection.setSchema("information_schema");
		return dbUnitDatabaseConnection;
	}

}
