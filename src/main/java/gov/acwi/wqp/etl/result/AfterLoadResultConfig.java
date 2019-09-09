package gov.acwi.wqp.etl.result;

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
public class AfterLoadResultConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;


	@Autowired
	@Qualifier("buildResultIndexesFlow")
	public Flow buildResultIndexesFlow;

	@Autowired
	@Qualifier("addResultForeignKeyMonitoringLocation")
	private Tasklet addResultForeignKeyMonitoringLocation;

	@Autowired
	@Qualifier("analyzeResult")
	private Tasklet analyzeResult;

	@Bean
	public Step addResultForeignKeyMonitoringLocationStep() {
		return stepBuilderFactory.get("addResultForeignKeyMonitoringLocationStep")
				.tasklet(addResultForeignKeyMonitoringLocation)
				.build();
	}

	@Bean
	public Step analyzeResultStep() {
		return stepBuilderFactory.get("analyzeResultStep")
				.tasklet(analyzeResult)
				.build();
	}

	@Bean
	public Flow afterLoadResultFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.AFTER_LOAD_RESULT_FLOW)
				.start(buildResultIndexesFlow)
				.next(addResultForeignKeyMonitoringLocationStep())
				.next(analyzeResultStep())
				.build();
	}
}
