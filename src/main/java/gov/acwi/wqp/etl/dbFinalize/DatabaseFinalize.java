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
//TODO WQP-1399
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

//	@Autowired
//	@Qualifier("analyze")
//	private Tasklet analyze;
//
//	@Autowired
//	@Qualifier("validate")
//	private Tasklet validate;
//
//	@Autowired
//	@Qualifier("install")
//	private Tasklet install;

	@Autowired
	@Qualifier("updateLastETL")
	private Tasklet updateLastETL;

	@Bean
	public Step updateLastETLStep() {
		return stepBuilderFactory.get("updateLastETLStep")
				.tasklet(updateLastETL)
				.build();
	}

	@Bean
	public Flow databaseFinalizeFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.CREATE_DATABASE_FINALIZE_FLOW)
//				.start(addRiStep())
//				.next(analyzeStep())
//				.next(validateStep())
//				.next(installStep())
//				.next(finalizeStep())
				.start(updateLastETLStep())
				.build();
	}

//	@Bean
//	public Step addRiStep() {
//		return stepBuilderFactory.get("addRiStep")
//				.tasklet(addRi)
//				.build();
//	}
//
//	@Bean
//	public Step analyzeStep() {
//		return stepBuilderFactory.get("analyzeStep")
//				.tasklet(analyze)
//				.build();
//	}
//
//	@Bean
//	public Step validateStep() {
//		return stepBuilderFactory.get("validateStep")
//				.tasklet(validate)
//				.build();
//	}
//
//	@Bean
//	public Step installStep() {
//		return stepBuilderFactory.get("installStep")
//				.tasklet(install)
//				.build();
//	}
}
