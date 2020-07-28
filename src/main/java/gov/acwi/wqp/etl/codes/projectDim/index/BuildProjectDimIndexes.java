package gov.acwi.wqp.etl.codes.projectDim.index;

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
public class BuildProjectDimIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildProjectDimCodeValueIndex")
	private Tasklet buildProjectDimCodeValueIndex;

	@Autowired
	@Qualifier("buildProjectDimProjectDimValueIndex")
	private Tasklet buildProjectDimProjectDimValueIndex;

	@Bean
	public Step buildProjectDimCodeValueIndexStep() {
		return stepBuilderFactory.get("buildProjectDimCodeValueIndexStep")
				.tasklet(buildProjectDimCodeValueIndex)
				.build();
	}

	@Bean
	public Step buildProjectDimProjectDimValueIndexStep() {
		return stepBuilderFactory.get("buildProjectDimProjectDimValueIndexStep")
				.tasklet(buildProjectDimProjectDimValueIndex)
				.build();
	}

	@Bean
	public Flow buildProjectDimIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildProjectDimIndexesFlow")
				.start(buildProjectDimCodeValueIndexStep())
				.next(buildProjectDimProjectDimValueIndexStep())
				.build();
	}

}
