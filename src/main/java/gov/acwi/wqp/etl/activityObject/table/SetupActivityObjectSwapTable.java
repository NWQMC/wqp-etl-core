package gov.acwi.wqp.etl.activityObject.table;

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
public class SetupActivityObjectSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropActivityObjectSwapTable")
	private Tasklet dropActivityObjectSwapTable;

	@Autowired
	@Qualifier("createActivityObjectSwapTable")
	private Tasklet createActivityObjectSwapTable;

	@Bean
	public Step dropActivityObjectSwapTableStep() {
		return stepBuilderFactory.get("dropActivityObjectSwapTableStep")
				.tasklet(dropActivityObjectSwapTable)
				.build();
	}

	@Bean
	public Step createActivityObjectSwapTableStep() {
		return stepBuilderFactory.get("createActivityObjectSwapTableStep")
				.tasklet(createActivityObjectSwapTable)
				.build();
	}

	@Bean
	public Flow setupActivityObjectSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.SETUP_ACTIVITY_OBJECT_SWAP_TABLE_FLOW)
				.start(dropActivityObjectSwapTableStep())
				.next(createActivityObjectSwapTableStep())
				.build();
	}

}
