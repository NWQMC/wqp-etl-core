package gov.acwi.wqp.etl.dbFinalize;

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

import gov.acwi.wqp.etl.EtlConstantUtils;

@Configuration
public class DatabaseFinalize {

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

//	@Bean
//	public Step validateStep() {
//		return stepBuilderFactory.get("validateStep")
//				.tasklet(validate)
//				.build();
//	}

	@Bean
	public Step installStep() {
		return stepBuilderFactory.get("installStep")
				.tasklet(install)
				.build();
	}

	@Bean
	public Flow databaseFinalizeFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.CREATE_DATABASE_FINALIZE_FLOW)
//				.next(validateStep())
				.start(installStep())
				.next(updateLastETLStep())
				.build();
	}
}
