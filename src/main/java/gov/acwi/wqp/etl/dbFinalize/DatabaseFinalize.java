package gov.acwi.wqp.etl.dbFinalize;

import gov.acwi.wqp.etl.*;
import gov.acwi.wqp.etl.result.Result;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseFinalize {

	@Autowired
	private ConfigurationService config;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

//TODO WQP-1396	@Autowired
//	@Qualifier("validate")
//	private Tasklet validate;

	@Autowired
	@Qualifier("install")
	private Tasklet install;

	@Autowired
	@Qualifier("updateLastETL")
	private Tasklet updateLastETL;

	@Bean
	public Step updateLastETLStep() {
		return stepBuilderFactory.get("updateLastETLStep")
				.tasklet(updateLastETL)
				.build();
	}

	/**
	 * This step is to work around an apparent PG bug discovered here:  https://internal.cida.usgs.gov/jira/browse/WQP-1623
	 * Apparently detaching the station_nwis table from station causes a FK constraint violation for result_nwis and it's
	 * partitions.  It doesn't seem like that should be the case, but it does.
	 * @return
	 */
	@Bean
	public Step dropResultFKConstraint() {

		//Naming is based on naming in addResultForeignKeyMonitoringLocation.sql
		DropConstraint task = new DropConstraint(
				jdbcTemplate,
				config.getWqpSchemaName(),
				Result.BASE_TABLE_NAME + "_" + config.getEtlDataSource(),
				Result.BASE_TABLE_NAME + "_" + config.getEtlDataSource() + "_fk_station");

		return stepBuilderFactory.get("dropResultFKConstraint")
				.tasklet(task)
				.build();
	}

	@Bean
	public Step installStep() {
		return stepBuilderFactory.get("installStep")
				.tasklet(install)
				.build();
	}

	@Bean
	public Flow databaseFinalizeFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.CREATE_DATABASE_FINALIZE_FLOW)
				.start(dropResultFKConstraint())
				.next(installStep())
				.next(updateLastETLStep())
				.build();
	}
}
