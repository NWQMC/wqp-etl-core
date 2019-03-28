package gov.acwi.wqp.etl.codes.monitoringLoc.table;

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
public class SetupMonitoringLocSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropMonitoringLocSwapTable")
	private Tasklet dropMonitoringLocSwapTable;

	@Autowired
	@Qualifier("createMonitoringLocSwapTable")
	private Tasklet createMonitoringLocSwapTable;

	@Bean
	public Step dropMonitoringLocSwapTableStep() {
		return stepBuilderFactory.get("dropMonitoringLocSwapTableStep")
				.tasklet(dropMonitoringLocSwapTable)
				.build();
	}

	@Bean
	public Step createMonitoringLocSwapTableStep() {
		return stepBuilderFactory.get("createMonitoringLocSwapTableStep")
				.tasklet(createMonitoringLocSwapTable)
				.build();
	}

	@Bean
	public Flow setupMonitoringLocSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupMonitoringLocSwapTableFlow")
				.start(dropMonitoringLocSwapTableStep())
				.next(createMonitoringLocSwapTableStep())
				.build();
	}
}
