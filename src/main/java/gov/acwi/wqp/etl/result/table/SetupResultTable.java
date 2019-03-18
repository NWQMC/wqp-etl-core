package gov.acwi.wqp.etl.result.table;

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
public class SetupResultTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropResultSwapTable")
	private Tasklet dropResultSwapTable;

	@Autowired
	@Qualifier("createResultSwapTable")
	private Tasklet createResultSwapTable;

	@Bean
	public Step dropResultSwapTableStep() {
		return stepBuilderFactory.get("dropResultSwapTableStep")
				.tasklet(dropResultSwapTable)
				.build();
	}

	@Bean
	public Step createResultSwapTableStep() {
		return stepBuilderFactory.get("createResultSwapTableStep")
				.tasklet(createResultSwapTable)
				.build();
	}

	@Bean
	public Flow setupResultSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupResultSwapTableFlow")
				.start(dropResultSwapTableStep())
				.next(createResultSwapTableStep())
				.build();
	}
}
