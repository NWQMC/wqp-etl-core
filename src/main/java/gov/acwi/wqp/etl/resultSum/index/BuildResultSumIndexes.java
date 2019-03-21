package gov.acwi.wqp.etl.resultSum.index;

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
public class BuildResultSumIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildResultSumOrganizationIndex")
	private Tasklet buildResultSumOrganizationIndex;

	@Bean
	public Step buildResultSumOrganizationIndexStep() {
		return stepBuilderFactory.get("buildResultSumOrganizationIndexStep")
				.tasklet(buildResultSumOrganizationIndex)
				.build();
	}

	@Bean
	public Flow buildResultSumIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildResultSumIndexesFlow")
				.start(buildResultSumOrganizationIndexStep())
				.build();
	}

}
