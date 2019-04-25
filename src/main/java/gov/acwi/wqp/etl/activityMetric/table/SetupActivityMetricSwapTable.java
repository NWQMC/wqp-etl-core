package gov.acwi.wqp.etl.activityMetric.table;

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
public class SetupActivityMetricSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropActivityMetricSwapTable")
	private Tasklet dropActivityMetricSwapTable;

	@Autowired
	@Qualifier("createActivityMetricSwapTable")
	private Tasklet createActivityMetricSwapTable;

	@Bean
	public Step dropActivityMetricSwapTableStep() {
		return stepBuilderFactory.get("dropActivityMetricSwapTableStep")
				.tasklet(dropActivityMetricSwapTable)
				.build();
	}

	@Bean
	public Step createActivityMetricSwapTableStep() {
		return stepBuilderFactory.get("createActivityMetricSwapTableStep")
				.tasklet(createActivityMetricSwapTable)
				.build();
	}

	@Bean
	public Flow setupActivityMetricSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.SETUP_ACTIVITY_METRIC_SWAP_TABLE_FLOW)
				.start(dropActivityMetricSwapTableStep())
				.next(createActivityMetricSwapTableStep())
				.build();
	}
}
