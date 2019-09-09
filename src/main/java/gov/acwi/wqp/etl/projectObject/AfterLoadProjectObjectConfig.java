package gov.acwi.wqp.etl.projectObject;

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
public class AfterLoadProjectObjectConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildProjectObjectIndexesFlow")
	public Flow buildProjectObjectIndexesFlow;

	@Autowired
	@Qualifier("addProjectObjectPrimaryKey")
	private Tasklet addProjectObjectPrimaryKey;

	@Autowired
	@Qualifier("analyzeProjectObject")
	private Tasklet analyzeProjectObject;

	@Bean
	public Step addProjectObjectPrimaryKeyStep() {
		return stepBuilderFactory.get("addProjectObjectPrimaryKeyStep")
				.tasklet(addProjectObjectPrimaryKey)
				.build();
	}

	@Bean
	public Step analyzeProjectObjectStep() {
		return stepBuilderFactory.get("analyzeProjectObjectStep")
				.tasklet(analyzeProjectObject)
				.build();
	}

	@Bean
	public Flow afterLoadProjectObjectFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.AFTER_LOAD_PROJECT_OBJECT_FLOW)
				.start(buildProjectObjectIndexesFlow)
				.next(addProjectObjectPrimaryKeyStep())
				.next(analyzeProjectObjectStep())
				.build();
	}
}
