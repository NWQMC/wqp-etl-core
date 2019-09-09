package gov.acwi.wqp.etl.resultObject;

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
public class AfterLoadResultObjectConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildResultObjectIndexesFlow")
	public Flow buildResultObjectIndexesFlow;

	@Autowired
	@Qualifier("addResultObjectPrimaryKey")
	private Tasklet addResultObjectPrimaryKey;

	@Autowired
	@Qualifier("analyzeResultObject")
	private Tasklet analyzeResultObject;

	@Bean
	public Step addResultObjectPrimaryKeyStep() {
		return stepBuilderFactory.get("addResultObjectPrimaryKeyStep")
				.tasklet(addResultObjectPrimaryKey)
				.build();
	}

	@Bean
	public Step analyzeResultObjectStep() {
		return stepBuilderFactory.get("analyzeResultObjectStep")
				.tasklet(analyzeResultObject)
				.build();
	}

	@Bean
	public Flow afterLoadResultObjectFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.AFTER_LOAD_RESULT_OBJECT_FLOW)
				.start(buildResultObjectIndexesFlow)
				.next(addResultObjectPrimaryKeyStep())
				.next(analyzeResultObjectStep())
				.build();
	}
}
