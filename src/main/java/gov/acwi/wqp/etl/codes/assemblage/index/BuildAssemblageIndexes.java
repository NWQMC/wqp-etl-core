package gov.acwi.wqp.etl.codes.assemblage.index;

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
public class BuildAssemblageIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildAssemblageCodeValueIndex")
	private Tasklet buildAssemblageCodeValueIndex;

	@Bean
	public Step buildAssemblageCodeValueIndexStep() {
		return stepBuilderFactory.get("buildAssemblageCodeValueIndexStep")
				.tasklet(buildAssemblageCodeValueIndex)
				.build();
	}

	@Bean
	public Flow buildAssemblageIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildAssemblageIndexesFlow")
				.start(buildAssemblageCodeValueIndexStep())
				.build();
	}

}
