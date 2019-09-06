package gov.acwi.wqp.etl.monitoringLocationObject.table;

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
public class SetupMonitoringLocationObjectSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropMonitoringLocationObjectSwapTable")
	private Tasklet dropMonitoringLocationObjectSwapTable;

	@Autowired
	@Qualifier("createMonitoringLocationObjectSwapTable")
	private Tasklet createMonitoringLocationObjectSwapTable;

	@Bean
	public Step dropMonitoringLocationObjectSwapTableStep() {
		return stepBuilderFactory.get("dropMonitoringLocationObjectSwapTableStep")
				.tasklet(dropMonitoringLocationObjectSwapTable)
				.build();
	}

	@Bean
	public Step createMonitoringLocationObjectSwapTableStep() {
		return stepBuilderFactory.get("createMonitoringLocationObjectSwapTableStep")
				.tasklet(createMonitoringLocationObjectSwapTable)
				.build();
	}

	@Bean
	public Flow setupMonitoringLocationObjectSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.SETUP_MONITORING_LOCATION_OBJECT_SWAP_TABLE_FLOW)
				.start(dropMonitoringLocationObjectSwapTableStep())
				.next(createMonitoringLocationObjectSwapTableStep())
				.build();
	}

}
