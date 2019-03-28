package gov.acwi.wqp.etl.summaries.activitySum;

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
public class TransformActivitySum {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("setupActivitySumSwapTableFlow")
	private Flow setupActivitySumSwapTableFlow;

	@Autowired
	@Qualifier("transformActivitySumTasklet")
	private Tasklet transformActivitySumTasklet;

	@Autowired
	@Qualifier("buildActivitySumIndexesFlow")
	private Flow buildActivitySumIndexesFlow;

	@Bean
	public Step transformActivitySumStep() {
		return stepBuilderFactory.get("transformActivitySumStep")
				.tasklet(transformActivitySumTasklet)
				.build();
	}

	@Bean
	public Flow activitySumFlow() {
		return new FlowBuilder<SimpleFlow>("activitySumFlow")
				.start(setupActivitySumSwapTableFlow)
				.next(transformActivitySumStep())
				.next(buildActivitySumIndexesFlow)
				.build();
	}

}
