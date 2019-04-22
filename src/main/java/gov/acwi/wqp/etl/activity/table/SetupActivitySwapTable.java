package gov.acwi.wqp.etl.activity.table;

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
public class SetupActivitySwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropActivitySwapTable")
	private Tasklet dropActivitySwapTable;

	@Autowired
	@Qualifier("createActivitySwapTable")
	private Tasklet createActivitySwapTable;

	@Bean
	public Step dropActivitySwapTableStep() {
		return stepBuilderFactory.get("dropActivitySwapTableStep")
				.tasklet(dropActivitySwapTable)
				.build();
	}

	@Bean
	public Step createActivitySwapTableStep() {
		return stepBuilderFactory.get("createActivitySwapTableStep")
				.tasklet(createActivitySwapTable)
				.build();
	}

	@Bean
	public Flow setupActivitySwapTableFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.SETUP_ACTIVITY_SWAP_TABLE_FLOW)
				.start(dropActivitySwapTableStep())
				.next(createActivitySwapTableStep())
				.build();
	}
}
