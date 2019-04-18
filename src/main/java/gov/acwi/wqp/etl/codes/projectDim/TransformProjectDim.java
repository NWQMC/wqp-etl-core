package gov.acwi.wqp.etl.codes.projectDim;

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
public class TransformProjectDim {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("setupProjectDimSwapTableFlow")
	private Flow setupProjectDimSwapTableFlow;

	@Autowired
	@Qualifier("transformProjectDimTasklet")
	private Tasklet transformProjectDimTasklet;

	@Autowired
	@Qualifier("buildProjectDimIndexesFlow")
	private Flow buildProjectDimIndexesFlow;

	@Bean
	public Step transformProjectDimStep() {
		return stepBuilderFactory.get("transformProjectDimStep")
				.tasklet(transformProjectDimTasklet)
				.build();
	}

	@Bean
	public Flow createProjectDimFlow() {
		return new FlowBuilder<SimpleFlow>("createProjectDimFlow")
				.start(setupProjectDimSwapTableFlow)
				.next(transformProjectDimStep())
				.next(buildProjectDimIndexesFlow)
				.build();
	}

}
