package gov.acwi.wqp.etl.codes.project.index;

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
public class BuildProjectIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildProjectCodeValueIndex")
	private Tasklet buildProjectCodeValueIndex;

	@Bean
	public Step buildProjectCodeValueIndexStep() {
		return stepBuilderFactory.get("buildProjectCodeValueIndexStep")
				.tasklet(buildProjectCodeValueIndex)
				.build();
	}

	@Bean
	public Flow buildProjectIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildProjectIndexesFlow")
				.start(buildProjectCodeValueIndexStep())
				.build();
	}

}
