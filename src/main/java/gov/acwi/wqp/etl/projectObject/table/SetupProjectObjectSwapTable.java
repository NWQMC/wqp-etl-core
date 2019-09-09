package gov.acwi.wqp.etl.projectObject.table;

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

import gov.acwi.wqp.etl.EtlConstantUtils;

@Configuration
public class SetupProjectObjectSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropProjectObjectSwapTable")
	private Tasklet dropProjectObjectSwapTable;

	@Autowired
	@Qualifier("createProjectObjectSwapTable")
	private Tasklet createProjectObjectSwapTable;

	@Bean
	public Step dropProjectObjectSwapTableStep() {
		return stepBuilderFactory.get("dropProjectObjectSwapTableStep")
				.tasklet(dropProjectObjectSwapTable)
				.build();
	}

	@Bean
	public Step createProjectObjectSwapTableStep() {
		return stepBuilderFactory.get("createProjectObjectSwapTableStep")
				.tasklet(createProjectObjectSwapTable)
				.build();
	}

	@Bean
	public Flow setupProjectObjectSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.SETUP_PROJECT_OBJECT_SWAP_TABLE_FLOW)
				.start(dropProjectObjectSwapTableStep())
				.next(createProjectObjectSwapTableStep())
				.build();
	}

}
