package gov.acwi.wqp.etl.codes.taxaName.table;

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
public class SetupTaxaNameSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropTaxaNameSwapTable")
	private Tasklet dropTaxaNameSwapTable;

	@Autowired
	@Qualifier("createTaxaNameSwapTable")
	private Tasklet createTaxaNameSwapTable;

	@Bean
	public Step dropTaxaNameSwapTableStep() {
		return stepBuilderFactory.get("dropTaxaNameSwapTableStep")
				.tasklet(dropTaxaNameSwapTable)
				.build();
	}

	@Bean
	public Step createTaxaNameSwapTableStep() {
		return stepBuilderFactory.get("createTaxaNameSwapTableStep")
				.tasklet(createTaxaNameSwapTable)
				.build();
	}

	@Bean
	public Flow setupTaxaNameSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupTaxaNameSwapTableFlow")
				.start(dropTaxaNameSwapTableStep())
				.next(createTaxaNameSwapTableStep())
				.build();
	}
}
