package gov.acwi.wqp.etl.codes.organization;

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
public class TransformOrganization {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("setupOrganizationSwapTableFlow")
	private Flow setupOrganizationSwapTableFlow;

	@Autowired
	@Qualifier("transformOrganizationTasklet")
	private Tasklet transformOrganizationTasklet;

	@Autowired
	@Qualifier("buildOrganizationIndexesFlow")
	private Flow buildOrganizationIndexesFlow;

	@Bean
	public Step transformOrganizationStep() {
		return stepBuilderFactory.get("transformOrganizationStep")
				.tasklet(transformOrganizationTasklet)
				.build();
	}

	@Bean
	public Flow createOrganizationFlow() {
		return new FlowBuilder<SimpleFlow>("createOrganizationFlow")
				.start(setupOrganizationSwapTableFlow)
				.next(transformOrganizationStep())
				.next(buildOrganizationIndexesFlow)
				.build();
	}

}
