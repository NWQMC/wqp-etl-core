package gov.acwi.wqp.etl.codes.state.index;

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
public class BuildStateIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildStateCodeValueIndex")
	private Tasklet buildStateCodeValueIndex;

	@Bean
	public Step buildStateCodeValueIndexStep() {
		return stepBuilderFactory.get("buildStateCodeValueIndexStep")
				.tasklet(buildStateCodeValueIndex)
				.build();
	}

	@Bean
	public Flow buildStateIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildStateIndexesFlow")
				.start(buildStateCodeValueIndexStep())
				.build();
	}

}
