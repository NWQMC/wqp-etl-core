package gov.acwi.wqp.etl.activityObject;

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
public class AfterLoadActivityObjectConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildActivityObjectIndexesFlow")
	public Flow buildActivityObjectIndexesFlow;

	@Autowired
	@Qualifier("addActivityObjectPrimaryKey")
	private Tasklet addActivityObjectPrimaryKey;

	@Autowired
	@Qualifier("analyzeActivityObject")
	private Tasklet analyzeActivityObject;

	@Bean
	public Step addActivityObjectPrimaryKeyStep() {
		return stepBuilderFactory.get("addActivityObjectPrimaryKeyStep")
				.tasklet(addActivityObjectPrimaryKey)
				.build();
	}

	@Bean
	public Step analyzeActivityObjectStep() {
		return stepBuilderFactory.get("analyzeActivityObjectStep")
				.tasklet(analyzeActivityObject)
				.build();
	}

	@Bean
	public Flow afterLoadActivityObjectFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.AFTER_LOAD_ACTIVITY_OBJECT_FLOW)
				.start(buildActivityObjectIndexesFlow)
				.next(addActivityObjectPrimaryKeyStep())
				.next(analyzeActivityObjectStep())
				.build();
	}
}
