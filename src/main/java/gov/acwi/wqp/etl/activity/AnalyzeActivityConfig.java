package gov.acwi.wqp.etl.activity;

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
public class AnalyzeActivityConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("analyzeActivity")
	private Tasklet analyzeActivity;

	@Bean
	public Step analyzeActivityStep() {
		return stepBuilderFactory.get("analyzeActivityStep")
				.tasklet(analyzeActivity)
				.build();
	}

	@Bean
	public Flow analyzeActivityFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.ANALYZE_ACTIVITY_FLOW)
				.start(analyzeActivityStep())
				.build();
	}
}
