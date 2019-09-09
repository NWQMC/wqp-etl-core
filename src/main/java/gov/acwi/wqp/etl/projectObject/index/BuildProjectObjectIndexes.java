package gov.acwi.wqp.etl.projectObject.index;

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
public class BuildProjectObjectIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildProjectObjectOrganizationIndex")
	private Tasklet buildProjectObjectOrganizationIndex;

	@Autowired
	@Qualifier("buildProjectObjectProjectIdentifierIndex")
	private Tasklet buildProjectObjectProjectIdentifierIndex;

	@Bean
	public Step buildProjectObjectOrganizationIndexStep() {
		return stepBuilderFactory.get("buildProjectObjectOrganizationIndexStep")
				.tasklet(buildProjectObjectOrganizationIndex)
				.build();
	}

	@Bean
	public Step buildProjectObjectProjectIdentifierIndexStep() {
		return stepBuilderFactory.get("buildProjectObjectProjectIdentifierIndexStep")
				.tasklet(buildProjectObjectProjectIdentifierIndex)
				.build();
	}

	@Bean
	public Flow buildProjectObjectIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildProjectObjectIndexesFlow")
				.start(buildProjectObjectOrganizationIndexStep())
				.next(buildProjectObjectProjectIdentifierIndexStep())
				.build();
	}

}
