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
public class AfterLoadActivityMetricConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildActivityMetricIndexesFlow")
	public Flow buildActivityMetricIndexesFlow;

	@Autowired
	@Qualifier("addActivityMetricForeignKeyActivity")
	private Tasklet addActivityMetricForeignKeyActivity;

	@Autowired
	@Qualifier("analyzeActivityMetric")
	private Tasklet analyzeActivityMetric;

	@Bean
	public Step addActivityMetricForeignKeyActivityStep() {
		return stepBuilderFactory.get("addActivityMetricForeignKeyActivityStep")
				.tasklet(addActivityMetricForeignKeyActivity)
				.build();
	}

	@Bean
	public Step analyzeActivityMetricStep() {
		return stepBuilderFactory.get("analyzeActivityMetricStep")
				.tasklet(analyzeActivityMetric)
				.build();
	}

	@Bean
	public Flow afterLoadActivityMetricFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.AFTER_LOAD_ACTIVITY_METRIC_FLOW)
				.start(buildActivityMetricIndexesFlow)
				.next(addActivityMetricForeignKeyActivityStep())
				.next(analyzeActivityMetricStep())
				.build();
	}
}
