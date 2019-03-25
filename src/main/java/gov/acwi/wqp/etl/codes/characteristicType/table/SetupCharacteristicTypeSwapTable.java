package gov.acwi.wqp.etl.codes.characteristicType.table;

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
public class SetupCharacteristicTypeSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropCharacteristicTypeSwapTable")
	private Tasklet dropCharacteristicTypeSwapTable;

	@Autowired
	@Qualifier("createCharacteristicTypeSwapTable")
	private Tasklet createCharacteristicTypeSwapTable;

	@Bean
	public Step dropCharacteristicTypeSwapTableStep() {
		return stepBuilderFactory.get("dropCharacteristicTypeSwapTableStep")
				.tasklet(dropCharacteristicTypeSwapTable)
				.build();
	}

	@Bean
	public Step createCharacteristicTypeSwapTableStep() {
		return stepBuilderFactory.get("createCharacteristicTypeSwapTableStep")
				.tasklet(createCharacteristicTypeSwapTable)
				.build();
	}

	@Bean
	public Flow setupCharacteristicTypeSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupCharacteristicTypeSwapTableFlow")
				.start(dropCharacteristicTypeSwapTableStep())
				.next(createCharacteristicTypeSwapTableStep())
				.build();
	}
}
