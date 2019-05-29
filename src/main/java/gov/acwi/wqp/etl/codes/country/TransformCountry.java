package gov.acwi.wqp.etl.codes.country;

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
public class TransformCountry {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("setupCountrySwapTableFlow")
	private Flow setupCountrySwapTableFlow;

	@Autowired
	@Qualifier("transformCountryTasklet")
	private Tasklet transformCountryTasklet;

	@Autowired
	@Qualifier("buildCountryIndexesFlow")
	private Flow buildCountryIndexesFlow;

	@Autowired
	@Qualifier("analyzeCountry")
	private Tasklet analyzeCountry;

	@Bean
	public Step transformCountryStep() {
		return stepBuilderFactory.get("transformCountryStep")
				.tasklet(transformCountryTasklet)
				.build();
	}

	@Bean
	public Step analyzeCountryStep() {
		return stepBuilderFactory.get("analyzeCountryStep")
				.tasklet(analyzeCountry)
				.build();
	}

	@Bean
	public Flow createCountryFlow() {
		return new FlowBuilder<SimpleFlow>("createCountryFlow")
				.start(setupCountrySwapTableFlow)
				.next(transformCountryStep())
				.next(buildCountryIndexesFlow)
				.next(analyzeCountryStep())
				.build();
	}

}
