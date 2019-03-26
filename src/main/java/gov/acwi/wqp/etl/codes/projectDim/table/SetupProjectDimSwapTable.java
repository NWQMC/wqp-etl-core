package gov.acwi.wqp.etl.codes.projectDim.table;

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
public class SetupProjectDimSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropProjectDimSwapTable")
	private Tasklet dropProjectDimSwapTable;

	@Autowired
	@Qualifier("createProjectDimSwapTable")
	private Tasklet createProjectDimSwapTable;

	@Bean
	public Step dropProjectDimSwapTableStep() {
		return stepBuilderFactory.get("dropProjectDimSwapTableStep")
				.tasklet(dropProjectDimSwapTable)
				.build();
	}

	@Bean
	public Step createProjectDimSwapTableStep() {
		return stepBuilderFactory.get("createProjectDimSwapTableStep")
				.tasklet(createProjectDimSwapTable)
				.build();
	}

	@Bean
	public Flow setupProjectDimSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupProjectDimSwapTableFlow")
				.start(dropProjectDimSwapTableStep())
				.next(createProjectDimSwapTableStep())
				.build();
	}
}
