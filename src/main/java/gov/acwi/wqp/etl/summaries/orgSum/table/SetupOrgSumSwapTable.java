package gov.acwi.wqp.etl.summaries.orgSum.table;

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
public class SetupOrgSumSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropOrgSumSwapTable")
	private Tasklet dropOrgSumSwapTable;

	@Autowired
	@Qualifier("createOrgSumSwapTable")
	private Tasklet createOrgSumSwapTable;

	@Bean
	public Step dropOrgSumSwapTableStep() {
		return stepBuilderFactory.get("dropOrgSumSwapTableStep")
				.tasklet(dropOrgSumSwapTable)
				.build();
	}

	@Bean
	public Step createOrgSumSwapTableStep() {
		return stepBuilderFactory.get("createOrgSumSwapTableStep")
				.tasklet(createOrgSumSwapTable)
				.build();
	}

	@Bean
	public Flow setupOrgSumSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupOrgSumSwapTableFlow")
				.start(dropOrgSumSwapTableStep())
				.next(createOrgSumSwapTableStep())
				.build();
	}
}
