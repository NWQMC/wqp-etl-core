package gov.acwi.wqp.etl.codes.project.table;

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
public class SetupProjectSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropProjectSwapTable")
	private Tasklet dropProjectSwapTable;

	@Autowired
	@Qualifier("createProjectSwapTable")
	private Tasklet createProjectSwapTable;

	@Bean
	public Step dropProjectSwapTableStep() {
		return stepBuilderFactory.get("dropProjectSwapTableStep")
				.tasklet(dropProjectSwapTable)
				.build();
	}

	@Bean
	public Step createProjectSwapTableStep() {
		return stepBuilderFactory.get("createProjectSwapTableStep")
				.tasklet(createProjectSwapTable)
				.build();
	}

	@Bean
	public Flow setupProjectSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupProjectSwapTableFlow")
				.start(dropProjectSwapTableStep())
				.next(createProjectSwapTableStep())
				.build();
	}
}
