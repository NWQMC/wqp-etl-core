package gov.acwi.wqp.etl.monitoringLocation.table;

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
public class SetupMonitoringLocationSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropMonitoringLocationSwapTable")
	private Tasklet dropMonitoringLocationSwapTable;

	@Autowired
	@Qualifier("createMonitoringLocationSwapTable")
	private Tasklet createMonitoringLocationSwapTable;

	@Bean
	public Step dropMonitoringLocationSwapTableStep() {
		return stepBuilderFactory.get("dropMonitoringLocationSwapTableStep")
				.tasklet(dropMonitoringLocationSwapTable)
				.build();
	}

	@Bean
	public Step createMonitoringLocationSwapTableStep() {
		return stepBuilderFactory.get("createMonitoringLocationSwapTableStep")
				.tasklet(createMonitoringLocationSwapTable)
				.build();
	}

	@Bean
	public Flow setupMonitoringLocationSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.SETUP_MONITORING_LOCATION_SWAP_TABLE_FLOW)
				.start(dropMonitoringLocationSwapTableStep())
				.next(createMonitoringLocationSwapTableStep())
				.build();
	}
}
