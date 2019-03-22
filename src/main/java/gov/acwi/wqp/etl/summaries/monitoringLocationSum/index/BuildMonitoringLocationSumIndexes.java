package gov.acwi.wqp.etl.summaries.monitoringLocationSum.index;

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
public class BuildMonitoringLocationSumIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildMonitoringLocationSumStationIdIndex")
	private Tasklet buildMonitoringLocationSumStationIdIndex;

	@Bean
	public Step buildMonitoringLocationSumStationIdIndexStep() {
		return stepBuilderFactory.get("buildMonitoringLocationSumStationIdIndexStep")
				.tasklet(buildMonitoringLocationSumStationIdIndex)
				.build();
	}

	@Bean
	public Flow buildMonitoringLocationSumIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildMonitoringLocationSumIndexesFlow")
				.start(buildMonitoringLocationSumStationIdIndexStep())
				.build();
	}

}
