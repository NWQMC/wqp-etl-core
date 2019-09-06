package gov.acwi.wqp.etl.monitoringLocationObject.index;

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
public class BuildMonitoringLocationObjectIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildMonitoringLocationObjectOrganizationIndex")
	private Tasklet buildMonitoringLocationObjectOrganizationIndex;

	@Autowired
	@Qualifier("buildMonitoringLocationObjectSiteIdIndex")
	private Tasklet buildMonitoringLocationObjectSiteIdIndex;

	@Bean
	public Step buildMonitoringLocationObjectOrganizationIndexStep() {
		return stepBuilderFactory.get("buildMonitoringLocationObjectOrganizationIndexStep")
				.tasklet(buildMonitoringLocationObjectOrganizationIndex)
				.build();
	}

	@Bean
	public Step buildMonitoringLocationObjectSiteIdIndexStep() {
		return stepBuilderFactory.get("buildMonitoringLocationObjectSiteIdIndexStep")
				.tasklet(buildMonitoringLocationObjectSiteIdIndex)
				.build();
	}

	@Bean
	public Flow buildMonitoringLocationObjectIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildMonitoringLocationObjectIndexesFlow")
				.start(buildMonitoringLocationObjectOrganizationIndexStep())
				.next(buildMonitoringLocationObjectSiteIdIndexStep())
				.build();
	}

}
