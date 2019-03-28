package gov.acwi.wqp.etl.summaries.mlGrouping;

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
public class TransformMlGrouping {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("setupMlGroupingSwapTableFlow")
	private Flow setupMlGroupingSwapTableFlow;

	@Autowired
	@Qualifier("transformMlGroupingTasklet")
	private Tasklet transformMlGroupingTasklet;

	@Autowired
	@Qualifier("buildMlGroupingIndexesFlow")
	private Flow buildMlGroupingIndexesFlow;

	@Bean
	public Step transformMlGroupingStep() {
		return stepBuilderFactory.get("transformMlGroupingStep")
				.tasklet(transformMlGroupingTasklet)
				.build();
	}

	@Bean
	public Flow mlGroupingFlow() {
		return new FlowBuilder<SimpleFlow>("mlGroupingFlow")
				.start(setupMlGroupingSwapTableFlow)
				.next(transformMlGroupingStep())
				.next(buildMlGroupingIndexesFlow)
				.build();
	}

}
