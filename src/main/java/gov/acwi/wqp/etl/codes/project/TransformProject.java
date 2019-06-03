package gov.acwi.wqp.etl.codes.project;

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
public class TransformProject {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("setupProjectSwapTableFlow")
	private Flow setupProjectSwapTableFlow;

	@Autowired
	@Qualifier("transformProjectTasklet")
	private Tasklet transformProjectTasklet;

	@Autowired
	@Qualifier("buildProjectIndexesFlow")
	private Flow buildProjectIndexesFlow;

	@Autowired
	@Qualifier("analyzeProject")
	private Tasklet analyzeProject;

	@Bean
	public Step transformProjectStep() {
		return stepBuilderFactory.get("transformProjectStep")
				.tasklet(transformProjectTasklet)
				.build();
	}

	@Bean
	public Step analyzeProjectStep() {
		return stepBuilderFactory.get("analyzeProjectStep")
				.tasklet(analyzeProject)
				.build();
	}

	@Bean
	public Flow createProjectFlow() {
		return new FlowBuilder<SimpleFlow>("createProjectFlow")
				.start(setupProjectSwapTableFlow)
				.next(transformProjectStep())
				.next(buildProjectIndexesFlow)
				.next(analyzeProjectStep())
				.build();
	}

}
