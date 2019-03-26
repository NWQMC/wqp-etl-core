package gov.acwi.wqp.etl.codes.siteType.table;

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
public class SetupSiteTypeSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropSiteTypeSwapTable")
	private Tasklet dropSiteTypeSwapTable;

	@Autowired
	@Qualifier("createSiteTypeSwapTable")
	private Tasklet createSiteTypeSwapTable;

	@Bean
	public Step dropSiteTypeSwapTableStep() {
		return stepBuilderFactory.get("dropSiteTypeSwapTableStep")
				.tasklet(dropSiteTypeSwapTable)
				.build();
	}

	@Bean
	public Step createSiteTypeSwapTableStep() {
		return stepBuilderFactory.get("createSiteTypeSwapTableStep")
				.tasklet(createSiteTypeSwapTable)
				.build();
	}

	@Bean
	public Flow setupSiteTypeSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupSiteTypeSwapTableFlow")
				.start(dropSiteTypeSwapTableStep())
				.next(createSiteTypeSwapTableStep())
				.build();
	}
}
