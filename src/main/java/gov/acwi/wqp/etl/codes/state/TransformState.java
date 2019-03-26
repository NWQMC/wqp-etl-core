package gov.acwi.wqp.etl.codes.state;

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
public class TransformState {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("setupStateSwapTableFlow")
	private Flow setupStateSwapTableFlow;

	@Autowired
	@Qualifier("transformStateTasklet")
	private Tasklet transformStateTasklet;

	@Autowired
	@Qualifier("buildStateIndexesFlow")
	private Flow buildStateIndexesFlow;

	@Bean
	public Step transformStateStep() {
		return stepBuilderFactory.get("transformStateStep")
				.tasklet(transformStateTasklet)
				.build();
	}

	@Bean
	public Flow createStateFlow() {
		return new FlowBuilder<SimpleFlow>("createStateFlow")
				.start(setupStateSwapTableFlow)
				.next(transformStateStep())
				.next(buildStateIndexesFlow)
				.build();
	}

}
