package gov.acwi.wqp.etl.summaries.organizationSum;

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
public class TransformOrganizationSum {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("setupOrganizationSumSwapTableFlow")
	private Flow setupOrganizationSumSwapTableFlow;

	@Autowired
	@Qualifier("transformOrganizationSumTasklet")
	private Tasklet transformOrganizationSumTasklet;

	@Autowired
	@Qualifier("buildOrganizationSumIndexesFlow")
	private Flow buildOrganizationSumIndexesFlow;

	@Bean
	public Step transformOrganizationSumStep() {
		return stepBuilderFactory.get("transformOrganizationSumStep")
				.tasklet(transformOrganizationSumTasklet)
				.build();
	}

	@Bean
	public Flow organizationSumFlow() {
		return new FlowBuilder<SimpleFlow>("organizationSumFlow")
				.start(setupOrganizationSumSwapTableFlow)
				.next(transformOrganizationSumStep())
				.next(buildOrganizationSumIndexesFlow)
				.build();
	}

}
