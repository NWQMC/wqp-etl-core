package gov.acwi.wqp.etl.codes.characteristicType;

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
public class TransformCharacteristicType {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("setupCharacteristicTypeSwapTableFlow")
	private Flow setupCharacteristicTypeSwapTableFlow;

	@Autowired
	@Qualifier("transformCharacteristicTypeTasklet")
	private Tasklet transformCharacteristicTypeTasklet;

	@Autowired
	@Qualifier("buildCharacteristicTypeIndexesFlow")
	private Flow buildCharacteristicTypeIndexesFlow;

	@Bean
	public Step transformCharacteristicTypeStep() {
		return stepBuilderFactory.get("transformCharacteristicTypeStep")
				.tasklet(transformCharacteristicTypeTasklet)
				.build();
	}

	@Bean
	public Flow createCharacteristicTypeFlow() {
		return new FlowBuilder<SimpleFlow>("createCharacteristicTypeFlow")
				.start(setupCharacteristicTypeSwapTableFlow)
				.next(transformCharacteristicTypeStep())
				.next(buildCharacteristicTypeIndexesFlow)
				.build();
	}

}
