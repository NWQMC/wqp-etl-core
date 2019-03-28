package gov.acwi.wqp.etl.summaries.resultSum.table;

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
public class SetupResultSumSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropResultSumSwapTable")
	private Tasklet dropResultSumSwapTable;

	@Autowired
	@Qualifier("createResultSumSwapTable")
	private Tasklet createResultSumSwapTable;

	@Bean
	public Step dropResultSumSwapTableStep() {
		return stepBuilderFactory.get("dropResultSumSwapTableStep")
				.tasklet(dropResultSumSwapTable)
				.build();
	}

	@Bean
	public Step createResultSumSwapTableStep() {
		return stepBuilderFactory.get("createResultSumSwapTableStep")
				.tasklet(createResultSumSwapTable)
				.build();
	}

	@Bean
	public Flow setupResultSumSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupResultSumSwapTableFlow")
				.start(dropResultSumSwapTableStep())
				.next(createResultSumSwapTableStep())
				.build();
	}
}
