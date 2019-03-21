package gov.acwi.wqp.etl.resultSum;

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
public class TransformResultSum {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("setupResultSumSwapTableFlow")
	private Flow setupResultSumSwapTableFlow;

	@Autowired
	@Qualifier("transformResultSumTasklet")
	private Tasklet transformResultSumTasklet;

	@Autowired
	@Qualifier("buildResultSumIndexesFlow")
	private Flow buildResultSumIndexesFlow;

	@Bean
	public Step transformResultSumStep() {
		return stepBuilderFactory.get("transformResultSumStep")
				.tasklet(transformResultSumTasklet)
				.build();
	}

	@Bean
	public Flow resultSumFlow() {
		return new FlowBuilder<SimpleFlow>("resultSumFlow")
				.start(setupResultSumSwapTableFlow)
				.next(transformResultSumStep())
				.next(buildResultSumIndexesFlow)
				.build();
	}

}
