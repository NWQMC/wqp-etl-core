package gov.acwi.wqp.etl.codes.country.index;

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
public class BuildCountryIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildCountryCodeValueIndex")
	private Tasklet buildCountryCodeValueIndex;

	@Bean
	public Step buildCountryCodeValueIndexStep() {
		return stepBuilderFactory.get("buildCountryCodeValueIndexStep")
				.tasklet(buildCountryCodeValueIndex)
				.build();
	}

	@Bean
	public Flow buildCountryIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildCountryIndexesFlow")
				.start(buildCountryCodeValueIndexStep())
				.build();
	}

}
