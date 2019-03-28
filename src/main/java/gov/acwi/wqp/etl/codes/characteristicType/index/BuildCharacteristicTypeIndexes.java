package gov.acwi.wqp.etl.codes.characteristicType.index;

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
public class BuildCharacteristicTypeIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildCharacteristicTypeCodeValueIndex")
	private Tasklet buildCharacteristicTypeCodeValueIndex;

	@Bean
	public Step buildCharacteristicTypeCodeValueIndexStep() {
		return stepBuilderFactory.get("buildCharacteristicTypeCodeValueIndexStep")
				.tasklet(buildCharacteristicTypeCodeValueIndex)
				.build();
	}

	@Bean
	public Flow buildCharacteristicTypeIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildCharacteristicTypeIndexesFlow")
				.start(buildCharacteristicTypeCodeValueIndexStep())
				.build();
	}

}
