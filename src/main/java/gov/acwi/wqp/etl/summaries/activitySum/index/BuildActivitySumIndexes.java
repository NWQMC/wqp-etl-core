package gov.acwi.wqp.etl.summaries.activitySum.index;

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
public class BuildActivitySumIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildActivitySumOrganizationIndex")
	private Tasklet buildActivitySumOrganizationIndex;

	@Bean
	public Step buildActivitySumOrganizationIndexStep() {
		return stepBuilderFactory.get("buildActivitySumOrganizationIndexStep")
				.tasklet(buildActivitySumOrganizationIndex)
				.build();
	}

	@Bean
	public Flow buildActivitySumIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildActivitySumIndexesFlow")
				.start(buildActivitySumOrganizationIndexStep())
				.build();
	}

}
