package gov.acwi.wqp.etl.codes.taxaName;

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
public class TransformTaxaName {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("setupTaxaNameSwapTableFlow")
	private Flow setupTaxaNameSwapTableFlow;

	@Autowired
	@Qualifier("transformTaxaNameTasklet")
	private Tasklet transformTaxaNameTasklet;

	@Autowired
	@Qualifier("buildTaxaNameIndexesFlow")
	private Flow buildTaxaNameIndexesFlow;

	@Autowired
	@Qualifier("analyzeTaxaName")
	private Tasklet analyzeTaxaName;

	@Bean
	public Step transformTaxaNameStep() {
		return stepBuilderFactory.get("transformTaxaNameStep")
				.tasklet(transformTaxaNameTasklet)
				.build();
	}

	@Bean
	public Step analyzeTaxaNameStep() {
		return stepBuilderFactory.get("analyzeTaxaNameStep")
				.tasklet(analyzeTaxaName)
				.build();
	}

	@Bean
	public Flow createTaxaNameFlow() {
		return new FlowBuilder<SimpleFlow>("createTaxaNameFlow")
				.start(setupTaxaNameSwapTableFlow)
				.next(transformTaxaNameStep())
				.next(buildTaxaNameIndexesFlow)
				.next(analyzeTaxaNameStep())
				.build();
	}

}
