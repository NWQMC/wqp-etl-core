package gov.acwi.wqp.etl.codes.monitoringLoc.index;

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
public class BuildMonitoringLocIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildMonitoringLocCodeValueIndex")
	private Tasklet buildMonitoringLocCodeValueIndex;

	@Autowired
	@Qualifier("buildMonitoringLocOrganizationIndex")
	private Tasklet buildMonitoringLocOrganizationIndex;

	@Bean
	public Step buildMonitoringLocCodeValueIndexStep() {
		return stepBuilderFactory.get("buildMonitoringLocCodeValueIndexStep")
				.tasklet(buildMonitoringLocCodeValueIndex)
				.build();
	}

	@Bean
	public Step buildMonitoringLocOrganizationIndexStep() {
		return stepBuilderFactory.get("buildMonitoringLocOrganizationIndexStep")
				.tasklet(buildMonitoringLocOrganizationIndex)
				.build();
	}
	@Bean
	public Flow buildMonitoringLocIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildMonitoringLocIndexesFlow")
				.start(buildMonitoringLocCodeValueIndexStep())
				.next(buildMonitoringLocOrganizationIndexStep())
				.build();
	}

}
