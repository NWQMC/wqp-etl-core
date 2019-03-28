package gov.acwi.wqp.etl.codes.organization.table;

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
public class SetupOrganizationSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropOrganizationSwapTable")
	private Tasklet dropOrganizationSwapTable;

	@Autowired
	@Qualifier("createOrganizationSwapTable")
	private Tasklet createOrganizationSwapTable;

	@Bean
	public Step dropOrganizationSwapTableStep() {
		return stepBuilderFactory.get("dropOrganizationSwapTableStep")
				.tasklet(dropOrganizationSwapTable)
				.build();
	}

	@Bean
	public Step createOrganizationSwapTableStep() {
		return stepBuilderFactory.get("createOrganizationSwapTableStep")
				.tasklet(createOrganizationSwapTable)
				.build();
	}

	@Bean
	public Flow setupOrganizationSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupOrganizationSwapTableFlow")
				.start(dropOrganizationSwapTableStep())
				.next(createOrganizationSwapTableStep())
				.build();
	}
}
