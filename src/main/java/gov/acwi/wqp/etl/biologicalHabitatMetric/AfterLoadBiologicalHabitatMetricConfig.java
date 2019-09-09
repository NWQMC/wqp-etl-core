package gov.acwi.wqp.etl.biologicalHabitatMetric;

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
public class AfterLoadBiologicalHabitatMetricConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildBiologicalHabitatMetricIndexesFlow")
	public Flow buildBiologicalHabitatMetricIndexesFlow;

	@Autowired
	@Qualifier("analyzeBiologicalHabitatMetric")
	private Tasklet analyzeBiologicalHabitatMetric;

	@Bean
	public Step analyzeBiologicalHabitatMetricStep() {
		return stepBuilderFactory.get("analyzeBiologicalHabitatMetricStep")
				.tasklet(analyzeBiologicalHabitatMetric)
				.build();
	}

	@Bean
	public Flow afterLoadBiologicalHabitatMetricFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.AFTER_LOAD_BIOLOGICAL_HABITAT_METRIC_FLOW)
				.start(buildBiologicalHabitatMetricIndexesFlow)
				.next(analyzeBiologicalHabitatMetricStep())
				.build();
	}
}
