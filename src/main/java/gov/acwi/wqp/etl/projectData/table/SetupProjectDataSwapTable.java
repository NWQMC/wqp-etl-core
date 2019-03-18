package gov.acwi.wqp.etl.projectData.table;

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
public class SetupProjectDataSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropProjectDataSwapTable")
	private Tasklet dropProjectDataSwapTable;

	@Autowired
	@Qualifier("createProjectDataSwapTable")
	private Tasklet createProjectDataSwapTable;

	@Bean
	public Step dropProjectDataSwapTableStep() {
		return stepBuilderFactory.get("dropProjectDataSwapTableStep")
				.tasklet(dropProjectDataSwapTable)
				.build();
	}

	@Bean
	public Step createProjectDataSwapTableStep() {
		return stepBuilderFactory.get("createProjectDataSwapTableStep")
				.tasklet(createProjectDataSwapTable)
				.build();
	}

	@Bean
	public Flow setupProjectDataSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupProjectDataSwapTableFlow")
				.start(dropProjectDataSwapTableStep())
				.next(createProjectDataSwapTableStep())
				.build();
	}

}
