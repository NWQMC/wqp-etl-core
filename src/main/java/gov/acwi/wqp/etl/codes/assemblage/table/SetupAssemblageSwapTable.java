package gov.acwi.wqp.etl.codes.assemblage.table;

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
public class SetupAssemblageSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropAssemblageSwapTable")
	private Tasklet dropAssemblageSwapTable;

	@Autowired
	@Qualifier("createAssemblageSwapTable")
	private Tasklet createAssemblageSwapTable;

	@Bean
	public Step dropAssemblageSwapTableStep() {
		return stepBuilderFactory.get("dropAssemblageSwapTableStep")
				.tasklet(dropAssemblageSwapTable)
				.build();
	}

	@Bean
	public Step createAssemblageSwapTableStep() {
		return stepBuilderFactory.get("createAssemblageSwapTableStep")
				.tasklet(createAssemblageSwapTable)
				.build();
	}

	@Bean
	public Flow setupAssemblageSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupAssemblageSwapTableFlow")
				.start(dropAssemblageSwapTableStep())
				.next(createAssemblageSwapTableStep())
				.build();
	}
}
