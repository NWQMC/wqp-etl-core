package gov.acwi.wqp.etl.summaries.qwportalSummary.table;

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
public class SetupQwportalSummarySwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropQwportalSummarySwapTable")
	private Tasklet dropQwportalSummarySwapTable;

	@Autowired
	@Qualifier("createQwportalSummarySwapTable")
	private Tasklet createQwportalSummarySwapTable;

	@Bean
	public Step dropQwportalSummarySwapTableStep() {
		return stepBuilderFactory.get("dropQwportalSummarySwapTableStep")
				.tasklet(dropQwportalSummarySwapTable)
				.build();
	}

	@Bean
	public Step createQwportalSummarySwapTableStep() {
		return stepBuilderFactory.get("createQwportalSummarySwapTableStep")
				.tasklet(createQwportalSummarySwapTable)
				.build();
	}

	@Bean
	public Flow setupQwportalSummarySwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupQwportalSummarySwapTableFlow")
				.start(dropQwportalSummarySwapTableStep())
				.next(createQwportalSummarySwapTableStep())
				.build();
	}
}
