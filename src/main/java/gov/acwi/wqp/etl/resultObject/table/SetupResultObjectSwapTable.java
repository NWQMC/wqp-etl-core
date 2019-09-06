package gov.acwi.wqp.etl.resultObject.table;

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
public class SetupResultObjectSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropResultObjectSwapTable")
	private Tasklet dropResultObjectSwapTable;

	@Autowired
	@Qualifier("createResultObjectSwapTable")
	private Tasklet createResultObjectSwapTable;

	@Bean
	public Step dropResultObjectSwapTableStep() {
		return stepBuilderFactory.get("dropResultObjectSwapTableStep")
				.tasklet(dropResultObjectSwapTable)
				.build();
	}

	@Bean
	public Step createResultObjectSwapTableStep() {
		return stepBuilderFactory.get("createResultObjectSwapTableStep")
				.tasklet(createResultObjectSwapTable)
				.build();
	}

	@Bean
	public Flow setupResultObjectSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.SETUP_RESULT_OBJECT_SWAP_TABLE_FLOW)
				.start(dropResultObjectSwapTableStep())
				.next(createResultObjectSwapTableStep())
				.build();
	}

}
