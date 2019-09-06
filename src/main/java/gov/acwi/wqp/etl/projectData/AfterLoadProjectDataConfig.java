package gov.acwi.wqp.etl.projectData;

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
public class AfterLoadProjectDataConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildProjectDataIndexesFlow")
	public Flow buildProjectDataIndexesFlow;

	@Autowired
	@Qualifier("addProjectDataPrimaryKey")
	private Tasklet addProjectDataPrimaryKey;

	@Autowired
	@Qualifier("analyzeProjectData")
	private Tasklet analyzeProjectData;

	@Bean
	public Step addProjectDataPrimaryKeyStep() {
		return stepBuilderFactory.get("addProjectDataPrimaryKeyStep")
				.tasklet(addProjectDataPrimaryKey)
				.build();
	}

	@Bean
	public Step analyzeProjectDataStep() {
		return stepBuilderFactory.get("analyzeProjectDataStep")
				.tasklet(analyzeProjectData)
				.build();
	}

	@Bean
	public Flow afterLoadProjectDataFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.AFTER_LOAD_PROJECT_DATA_FLOW)
				.start(buildProjectDataIndexesFlow)
				.next(addProjectDataPrimaryKeyStep())
				.next(analyzeProjectDataStep())
				.build();
	}
}
