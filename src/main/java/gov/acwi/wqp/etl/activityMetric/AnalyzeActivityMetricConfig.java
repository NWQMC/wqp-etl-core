package gov.acwi.wqp.etl.activityMetric;

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
public class AnalyzeActivityMetricConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("analyzeActivityMetric")
	private Tasklet analyzeActivityMetric;

	@Bean
	public Step analyzeActivityMetricStep() {
		return stepBuilderFactory.get("analyzeActivityMetricStep")
				.tasklet(analyzeActivityMetric)
				.build();
	}

	@Bean
	public Flow analyzeActivityMetricFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.ANALYZE_ACTIVITY_METRIC_FLOW)
				.start(analyzeActivityMetricStep())
				.build();
	}
}
