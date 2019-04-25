package gov.acwi.wqp.etl.biologicalHabitatMetric.index;

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
public class BuildBiologicalHabitatMetricIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildBiologicalHabitatMetricOrganizationIndex")
	private Tasklet buildBiologicalHabitatMetricOrganizationIndex;

	@Bean
	public Step buildBiologicalHabitatMetricOrganizationIndexStep() {
		return stepBuilderFactory.get("buildBiologicalHabitatMetricOrganizationIndexStep")
				.tasklet(buildBiologicalHabitatMetricOrganizationIndex)
				.build();
	}

	@Bean
	public Flow buildBiologicalHabitatMetricIndexesFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.BUILD_BIOLOGICAL_HABITAT_METRIC_INDEXES_FLOW)
				.start(buildBiologicalHabitatMetricOrganizationIndexStep())
				.build();
	}

}
