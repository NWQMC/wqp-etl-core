package gov.acwi.wqp.etl.codes.country.table;

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
public class SetupCountrySwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropCountrySwapTable")
	private Tasklet dropCountrySwapTable;

	@Autowired
	@Qualifier("createCountrySwapTable")
	private Tasklet createCountrySwapTable;

	@Bean
	public Step dropCountrySwapTableStep() {
		return stepBuilderFactory.get("dropCountrySwapTableStep")
				.tasklet(dropCountrySwapTable)
				.build();
	}

	@Bean
	public Step createCountrySwapTableStep() {
		return stepBuilderFactory.get("createCountrySwapTableStep")
				.tasklet(createCountrySwapTable)
				.build();
	}

	@Bean
	public Flow setupCountrySwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupCountrySwapTableFlow")
				.start(dropCountrySwapTableStep())
				.next(createCountrySwapTableStep())
				.build();
	}
}
