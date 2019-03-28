package gov.acwi.wqp.etl.summaries.monitoringLocationSum.table;

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

@Configuration
public class SetupMonitoringLocationSumSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropMonitoringLocationSumSwapTable")
	private Tasklet dropMonitoringLocationSumSwapTable;

	@Autowired
	@Qualifier("createMonitoringLocationSumSwapTable")
	private Tasklet createMonitoringLocationSumSwapTable;

	@Bean
	public Step dropMonitoringLocationSumSwapTableStep() {
		return stepBuilderFactory.get("dropMonitoringLocationSumSwapTableStep")
				.tasklet(dropMonitoringLocationSumSwapTable)
				.build();
	}

	@Bean
	public Step createMonitoringLocationSumSwapTableStep() {
		return stepBuilderFactory.get("createMonitoringLocationSumSwapTableStep")
				.tasklet(createMonitoringLocationSumSwapTable)
				.build();
	}

	@Bean
	public Flow setupMonitoringLocationSumSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupMonitoringLocationSumSwapTableFlow")
				.start(dropMonitoringLocationSumSwapTableStep())
				.next(createMonitoringLocationSumSwapTableStep())
				.build();
	}
}
