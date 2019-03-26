package gov.acwi.wqp.etl.codes.taxaName.index;

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
public class BuildTaxaNameIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildTaxaNameCodeValueIndex")
	private Tasklet buildTaxaNameCodeValueIndex;

	@Bean
	public Step buildTaxaNameCodeValueIndexStep() {
		return stepBuilderFactory.get("buildTaxaNameCodeValueIndexStep")
				.tasklet(buildTaxaNameCodeValueIndex)
				.build();
	}

	@Bean
	public Flow buildTaxaNameIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildTaxaNameIndexesFlow")
				.start(buildTaxaNameCodeValueIndexStep())
				.build();
	}

}
