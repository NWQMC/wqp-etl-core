package gov.acwi.wqp.etl.summaries.activitySum.table;

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
public class SetupActivitySumSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropActivitySumSwapTable")
	private Tasklet dropActivitySumSwapTable;

	@Autowired
	@Qualifier("createActivitySumSwapTable")
	private Tasklet createActivitySumSwapTable;

	@Bean
	public Step dropActivitySumSwapTableStep() {
		return stepBuilderFactory.get("dropActivitySumSwapTableStep")
				.tasklet(dropActivitySumSwapTable)
				.build();
	}

	@Bean
	public Step createActivitySumSwapTableStep() {
		return stepBuilderFactory.get("createActivitySumSwapTableStep")
				.tasklet(createActivitySumSwapTable)
				.build();
	}

	@Bean
	public Flow setupActivitySumSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupActivitySumSwapTableFlow")
				.start(dropActivitySumSwapTableStep())
				.next(createActivitySumSwapTableStep())
				.build();
	}
}
