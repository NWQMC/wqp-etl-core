package gov.acwi.wqp.etl.mlGrouping.table;

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
public class SetupMlGroupingSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropMlGroupingSwapTable")
	private Tasklet dropMlGroupingSwapTable;

	@Autowired
	@Qualifier("createMlGroupingSwapTable")
	private Tasklet createMlGroupingSwapTable;

	@Bean
	public Step dropMlGroupingSwapTableStep() {
		return stepBuilderFactory.get("dropMlGroupingSwapTableStep")
				.tasklet(dropMlGroupingSwapTable)
				.build();
	}

	@Bean
	public Step createMlGroupingSwapTableStep() {
		return stepBuilderFactory.get("createMlGroupingSwapTableStep")
				.tasklet(createMlGroupingSwapTable)
				.build();
	}

	@Bean
	public Flow setupMlGroupingSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupMlGroupingSwapTableFlow")
				.start(dropMlGroupingSwapTableStep())
				.next(createMlGroupingSwapTableStep())
				.build();
	}
}
