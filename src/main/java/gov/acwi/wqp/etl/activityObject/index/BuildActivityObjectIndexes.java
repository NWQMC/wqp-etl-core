package gov.acwi.wqp.etl.activityObject.index;

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
public class BuildActivityObjectIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildActivityObjectOrganizationIndex")
	private Tasklet buildActivityObjectOrganizationIndex;

	@Autowired
	@Qualifier("buildActivityObjectActivityIndex")
	private Tasklet buildActivityObjectActivityIndex;

	@Bean
	public Step buildActivityObjectOrganizationIndexStep() {
		return stepBuilderFactory.get("buildActivityObjectOrganizationIndexStep")
				.tasklet(buildActivityObjectOrganizationIndex)
				.build();
	}

	@Bean
	public Step buildActivityObjectActivityIndexStep() {
		return stepBuilderFactory.get("buildActivityObjectActivityIndexStep")
				.tasklet(buildActivityObjectActivityIndex)
				.build();
	}

	@Bean
	public Flow buildActivityObjectIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildActivityObjectIndexesFlow")
				.start(buildActivityObjectOrganizationIndexStep())
				.next(buildActivityObjectActivityIndexStep())
				.build();
	}

}
