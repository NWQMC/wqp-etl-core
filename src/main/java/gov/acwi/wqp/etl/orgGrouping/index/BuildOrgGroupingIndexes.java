package gov.acwi.wqp.etl.orgGrouping.index;

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
public class BuildOrgGroupingIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildOrgGroupingOrganizationIndex")
	private Tasklet buildOrgGroupingOrganizationIndex;

	@Bean
	public Step buildOrgGroupingOrganizationIndexStep() {
		return stepBuilderFactory.get("buildOrgGroupingOrganizationIndexStep")
				.tasklet(buildOrgGroupingOrganizationIndex)
				.build();
	}

	@Bean
	public Flow buildOrgGroupingIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildOrgGroupingIndexesFlow")
				.start(buildOrgGroupingOrganizationIndexStep())
				.build();
	}

}
