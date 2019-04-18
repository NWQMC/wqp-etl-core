package gov.acwi.wqp.etl.codes.county;

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
public class TransformCounty {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("setupCountySwapTableFlow")
	private Flow setupCountySwapTableFlow;

	@Autowired
	@Qualifier("transformCountyTasklet")
	private Tasklet transformCountyTasklet;

	@Autowired
	@Qualifier("buildCountyIndexesFlow")
	private Flow buildCountyIndexesFlow;

	@Bean
	public Step transformCountyStep() {
		return stepBuilderFactory.get("transformCountyStep")
				.tasklet(transformCountyTasklet)
				.build();
	}

	@Bean
	public Flow createCountyFlow() {
		return new FlowBuilder<SimpleFlow>("createCountyFlow")
				.start(setupCountySwapTableFlow)
				.next(transformCountyStep())
				.next(buildCountyIndexesFlow)
				.build();
	}

}
