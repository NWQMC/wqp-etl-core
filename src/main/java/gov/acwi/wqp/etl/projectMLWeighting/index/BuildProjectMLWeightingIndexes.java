package gov.acwi.wqp.etl.projectMLWeighting.index;

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

import gov.acwi.wqp.etl.EtlConstantUtils;

@Configuration
public class BuildProjectMLWeightingIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildProjectMLWeightingOrganizationIndex")
	private Tasklet buildProjectMLWeightingOrganizationIndex;

	@Bean
	public Step buildProjectMLWeightingOrganizationIndexStep() {
		return stepBuilderFactory.get("buildProjectMLWeightingOrganizationIndexStep")
				.tasklet(buildProjectMLWeightingOrganizationIndex)
				.build();
	}

	@Bean
	public Flow buildProjectMLWeightingIndexesFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.BUILD_PROJECT_ML_WEIGHTING_INDEXES_FLOW)
				.start(buildProjectMLWeightingOrganizationIndexStep())
				.build();
	}

}
