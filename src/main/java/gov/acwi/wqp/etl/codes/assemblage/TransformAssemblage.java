package gov.acwi.wqp.etl.codes.assemblage;

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
public class TransformAssemblage {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("setupAssemblageSwapTableFlow")
	private Flow setupAssemblageSwapTableFlow;

	@Autowired
	@Qualifier("transformAssemblageTasklet")
	private Tasklet transformAssemblageTasklet;

	@Autowired
	@Qualifier("buildAssemblageIndexesFlow")
	private Flow buildAssemblageIndexesFlow;

	@Autowired
	@Qualifier("analyzeAssemblage")
	private Tasklet analyzeAssemblage;

	@Bean
	public Step transformAssemblageStep() {
		return stepBuilderFactory.get("transformAssemblageStep")
				.tasklet(transformAssemblageTasklet)
				.build();
	}

	@Bean
	public Step analyzeAssemblageStep() {
		return stepBuilderFactory.get("analyzeAssemblageStep")
				.tasklet(analyzeAssemblage)
				.build();
	}

	@Bean
	public Flow createAssemblageFlow() {
		return new FlowBuilder<SimpleFlow>("createAssemblageFlow")
				.start(setupAssemblageSwapTableFlow)
				.next(transformAssemblageStep())
				.next(buildAssemblageIndexesFlow)
				.next(analyzeAssemblageStep())
				.build();
	}

}
