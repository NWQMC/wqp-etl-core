package gov.acwi.wqp.etl.codes.characteristicName;

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
public class TransformCharacteristicName {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("setupCharacteristicNameSwapTableFlow")
	private Flow setupCharacteristicNameSwapTableFlow;

	@Autowired
	@Qualifier("transformCharacteristicNameTasklet")
	private Tasklet transformCharacteristicNameTasklet;

	@Autowired
	@Qualifier("buildCharacteristicNameIndexesFlow")
	private Flow buildCharacteristicNameIndexesFlow;

	@Autowired
	@Qualifier("analyzeCharacteristicName")
	private Tasklet analyzeCharacteristicName;

	@Bean
	public Step transformCharacteristicNameStep() {
		return stepBuilderFactory.get("transformCharacteristicNameStep")
				.tasklet(transformCharacteristicNameTasklet)
				.build();
	}

	@Bean
	public Step analyzeCharacteristicNameStep() {
		return stepBuilderFactory.get("analyzeCharacteristicNameStep")
				.tasklet(analyzeCharacteristicName)
				.build();
	}

	@Bean
	public Flow createCharacteristicNameFlow() {
		return new FlowBuilder<SimpleFlow>("createCharacteristicNameFlow")
				.start(setupCharacteristicNameSwapTableFlow)
				.next(transformCharacteristicNameStep())
				.next(buildCharacteristicNameIndexesFlow)
				.next(analyzeCharacteristicNameStep())
				.build();
	}

}
