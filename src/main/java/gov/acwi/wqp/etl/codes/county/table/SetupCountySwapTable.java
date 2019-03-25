package gov.acwi.wqp.etl.codes.county.table;

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
public class SetupCountySwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropCountySwapTable")
	private Tasklet dropCountySwapTable;

	@Autowired
	@Qualifier("createCountySwapTable")
	private Tasklet createCountySwapTable;

	@Bean
	public Step dropCountySwapTableStep() {
		return stepBuilderFactory.get("dropCountySwapTableStep")
				.tasklet(dropCountySwapTable)
				.build();
	}

	@Bean
	public Step createCountySwapTableStep() {
		return stepBuilderFactory.get("createCountySwapTableStep")
				.tasklet(createCountySwapTable)
				.build();
	}

	@Bean
	public Flow setupCountySwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupCountySwapTableFlow")
				.start(dropCountySwapTableStep())
				.next(createCountySwapTableStep())
				.build();
	}
}
