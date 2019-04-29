package gov.acwi.wqp.etl.activityMetric.index;

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
public class BuildActivityMetricIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildActivityMetricOrganizationIndex")
	private Tasklet buildActivityMetricOrganizationIndex;

	@Bean
	public Step buildActivityMetricOrganizationIndexStep() {
		return stepBuilderFactory.get("buildActivityMetricOrganizationIndexStep")
				.tasklet(buildActivityMetricOrganizationIndex)
				.build();
	}

	@Bean
	public Flow buildActivityMetricIndexesFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.BUILD_ACTIVITY_METRIC_INDEXES_FLOW)
				.start(buildActivityMetricOrganizationIndexStep())
				.build();
	}

}
