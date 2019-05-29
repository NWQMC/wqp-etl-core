package gov.acwi.wqp.etl.summaries.qwportalSummary;

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
public class TransformQwportalSummary {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("setupQwportalSummarySwapTableFlow")
	private Flow setupQwportalSummarySwapTableFlow;

	@Autowired
	@Qualifier("transformQwportalSummaryTasklet")
	private Tasklet transformQwportalSummaryTasklet;

	@Autowired
	@Qualifier("analyzeQwportalSummary")
	private Tasklet analyzeQwportalSummary;

	@Bean
	public Step transformQwportalSummaryStep() {
		return stepBuilderFactory.get("transformQwportalSummaryStep")
				.tasklet(transformQwportalSummaryTasklet)
				.build();
	}

	@Bean
	public Step analyzeQwportalSummaryStep() {
		return stepBuilderFactory.get("analyzeQwportalSummaryStep")
				.tasklet(analyzeQwportalSummary)
				.build();
	}

	@Bean
	public Flow qwportalSummaryFlow() {
		//Note: this table does not currently have any indexes.
		return new FlowBuilder<SimpleFlow>("qwportalSummaryFlow")
				.start(setupQwportalSummarySwapTableFlow)
				.next(transformQwportalSummaryStep())
				.next(analyzeQwportalSummaryStep())
				.build();
	}

}
