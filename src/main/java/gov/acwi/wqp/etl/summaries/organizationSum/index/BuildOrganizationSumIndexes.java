package gov.acwi.wqp.etl.summaries.organizationSum.index;

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
public class BuildOrganizationSumIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildOrganizationSumOrganizationIndex")
	private Tasklet buildOrganizationSumOrganizationIndex;

	@Bean
	public Step buildOrganizationSumOrganizationIndexStep() {
		return stepBuilderFactory.get("buildOrganizationSumOrganizationIndexStep")
				.tasklet(buildOrganizationSumOrganizationIndex)
				.build();
	}

	@Bean
	public Flow buildOrganizationSumIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildOrganizationSumIndexesFlow")
				.start(buildOrganizationSumOrganizationIndexStep())
				.build();
	}

}
