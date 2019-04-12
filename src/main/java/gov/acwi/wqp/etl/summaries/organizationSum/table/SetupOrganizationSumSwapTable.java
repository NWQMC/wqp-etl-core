package gov.acwi.wqp.etl.summaries.organizationSum.table;

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
public class SetupOrganizationSumSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropOrganizationSumSwapTable")
	private Tasklet dropOrganizationSumSwapTable;

	@Autowired
	@Qualifier("createOrganizationSumSwapTable")
	private Tasklet createOrganizationSumSwapTable;

	@Bean
	public Step dropOrganizationSumSwapTableStep() {
		return stepBuilderFactory.get("dropOrganizationSumSwapTableStep")
				.tasklet(dropOrganizationSumSwapTable)
				.build();
	}

	@Bean
	public Step createOrganizationSumSwapTableStep() {
		return stepBuilderFactory.get("createOrganizationSumSwapTableStep")
				.tasklet(createOrganizationSumSwapTable)
				.build();
	}

	@Bean
	public Flow setupOrganizationSumSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupOrganizationSumSwapTableFlow")
				.start(dropOrganizationSumSwapTableStep())
				.next(createOrganizationSumSwapTableStep())
				.build();
	}
}
