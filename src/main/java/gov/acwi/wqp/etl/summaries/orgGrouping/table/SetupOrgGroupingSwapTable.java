package gov.acwi.wqp.etl.summaries.orgGrouping.table;

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
public class SetupOrgGroupingSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropOrgGroupingSwapTable")
	private Tasklet dropOrgGroupingSwapTable;

	@Autowired
	@Qualifier("createOrgGroupingSwapTable")
	private Tasklet createOrgGroupingSwapTable;

	@Bean
	public Step dropOrgGroupingSwapTableStep() {
		return stepBuilderFactory.get("dropOrgGroupingSwapTableStep")
				.tasklet(dropOrgGroupingSwapTable)
				.build();
	}

	@Bean
	public Step createOrgGroupingSwapTableStep() {
		return stepBuilderFactory.get("createOrgGroupingSwapTableStep")
				.tasklet(createOrgGroupingSwapTable)
				.build();
	}

	@Bean
	public Flow setupOrgGroupingSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupOrgGroupingSwapTableFlow")
				.start(dropOrgGroupingSwapTableStep())
				.next(createOrgGroupingSwapTableStep())
				.build();
	}
}
