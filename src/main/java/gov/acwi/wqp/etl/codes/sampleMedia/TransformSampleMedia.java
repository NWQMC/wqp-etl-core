package gov.acwi.wqp.etl.codes.sampleMedia;

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
public class TransformSampleMedia {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("setupSampleMediaSwapTableFlow")
	private Flow setupSampleMediaSwapTableFlow;

	@Autowired
	@Qualifier("transformSampleMediaTasklet")
	private Tasklet transformSampleMediaTasklet;

	@Autowired
	@Qualifier("buildSampleMediaIndexesFlow")
	private Flow buildSampleMediaIndexesFlow;

	@Bean
	public Step transformSampleMediaStep() {
		return stepBuilderFactory.get("transformSampleMediaStep")
				.tasklet(transformSampleMediaTasklet)
				.build();
	}

	@Bean
	public Flow createSampleMediaFlow() {
		return new FlowBuilder<SimpleFlow>("createSampleMediaFlow")
				.start(setupSampleMediaSwapTableFlow)
				.next(transformSampleMediaStep())
				.next(buildSampleMediaIndexesFlow)
				.build();
	}

}
