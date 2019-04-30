package gov.acwi.wqp.etl.biologicalHabitatMetric.table;

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
public class SetupBiologicalHabitatMetricSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropBiologicalHabitatMetricSwapTable")
	private Tasklet dropBiologicalHabitatMetricSwapTable;

	@Autowired
	@Qualifier("createBiologicalHabitatMetricSwapTable")
	private Tasklet createBiologicalHabitatMetricSwapTable;

	@Bean
	public Step dropBiologicalHabitatMetricSwapTableStep() {
		return stepBuilderFactory.get("dropBiologicalHabitatMetricSwapTableStep")
				.tasklet(dropBiologicalHabitatMetricSwapTable)
				.build();
	}

	@Bean
	public Step createBiologicalHabitatMetricSwapTableStep() {
		return stepBuilderFactory.get("createBiologicalHabitatMetricSwapTableStep")
				.tasklet(createBiologicalHabitatMetricSwapTable)
				.build();
	}

	@Bean
	public Flow setupBiologicalHabitatMetricSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.SETUP_BIOLOGICAL_HABITAT_METRIC_SWAP_TABLE_FLOW)
				.start(dropBiologicalHabitatMetricSwapTableStep())
				.next(createBiologicalHabitatMetricSwapTableStep())
				.build();
	}
}
