package gov.acwi.wqp.etl.orgData.table;

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
public class SetupOrgDataSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropOrgDataSwapTable")
	private Tasklet dropOrgDataSwapTable;

	@Autowired
	@Qualifier("createOrgDataSwapTable")
	private Tasklet createOrgDataSwapTable;

	@Bean
	public Step dropOrgDataSwapTableStep() {
		return stepBuilderFactory.get("dropOrgDataSwapTableStep")
				.tasklet(dropOrgDataSwapTable)
				.build();
	}

	@Bean
	public Step createOrgDataSwapTableStep() {
		return stepBuilderFactory.get("createOrgDataSwapTableStep")
				.tasklet(createOrgDataSwapTable)
				.build();
	}

	@Bean
	public Flow setupOrgDataSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.SETUP_ORG_DATA_SWAP_TABLE_FLOW)
				.start(dropOrgDataSwapTableStep())
				.next(createOrgDataSwapTableStep())
				.build();
	}

}
