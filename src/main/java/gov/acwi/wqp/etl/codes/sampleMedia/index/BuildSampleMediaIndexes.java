package gov.acwi.wqp.etl.codes.sampleMedia.index;

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
public class BuildSampleMediaIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildSampleMediaCodeValueIndex")
	private Tasklet buildSampleMediaCodeValueIndex;

	@Bean
	public Step buildSampleMediaCodeValueIndexStep() {
		return stepBuilderFactory.get("buildSampleMediaCodeValueIndexStep")
				.tasklet(buildSampleMediaCodeValueIndex)
				.build();
	}

	@Bean
	public Flow buildSampleMediaIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildSampleMediaIndexesFlow")
				.start(buildSampleMediaCodeValueIndexStep())
				.build();
	}

}
