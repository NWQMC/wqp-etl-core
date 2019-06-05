package gov.acwi.wqp.etl.projectMLWeighting;

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
public class AfterLoadProjectMLWeightingConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildProjectMLWeightingIndexesFlow")
	public Flow buildProjectMLWeightingIndexesFlow;

	@Autowired
	@Qualifier("addProjectMLWeightingForeignKeyProjectData")
	private Tasklet addProjectMLWeightingForeignKeyProjectData;

	@Autowired
	@Qualifier("addProjectMLWeightingForeignKeyMonitoringLocation")
	private Tasklet addProjectMLWeightingForeignKeyMonitoringLocation;

	@Autowired
	@Qualifier("analyzeProjectMLWeighting")
	private Tasklet analyzeProjectMLWeighting;

	@Bean
	public Step analyzeProjectMLWeightingStep() {
		return stepBuilderFactory.get("analyzeProjectMLWeightingStep")
				.tasklet(analyzeProjectMLWeighting)
				.build();
	}

	@Bean
	public Step addProjectMLWeightingForeignKeyProjectDataStep() {
		return stepBuilderFactory.get("addProjectMLWeightingForeignKeyProjectDataStep")
				.tasklet(addProjectMLWeightingForeignKeyProjectData)
				.build();
	}

	@Bean
	public Step addProjectMLWeightingForeignKeyMonitoringLocationStep() {
		return stepBuilderFactory.get("addProjectMLWeightingForeignKeyMonitoringLocationStep")
				.tasklet(addProjectMLWeightingForeignKeyMonitoringLocation)
				.build();
	}

	@Bean
	@Deprecated
	public Flow analyzeProjectMLWeightingFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.ANALYZE_PROJECT_ML_WEIGHTING_FLOW)
				.start(analyzeProjectMLWeightingStep())
				.build();
	}

	@Bean
	public Flow afterLoadProjectMLWeightingFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.AFTER_LOAD_ACTIVITY_FLOW)
				.start(buildProjectMLWeightingIndexesFlow)
				.next(addProjectMLWeightingForeignKeyProjectDataStep())
				.next(addProjectMLWeightingForeignKeyMonitoringLocationStep())
				.next(analyzeProjectMLWeightingStep())
				.build();
	}
}
