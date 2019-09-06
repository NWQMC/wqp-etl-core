package gov.acwi.wqp.etl.resDetectQntLimit;

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
public class AfterLoadResDetectQntLimitConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildResDetectQntLimitIndexesFlow")
	public Flow buildResDetectQntLimitIndexesFlow;

	@Autowired
	@Qualifier("analyzeResDetectQntLimit")
	private Tasklet analyzeResDetectQntLimit;

	@Bean
	public Step analyzeResDetectQntLimitStep() {
		return stepBuilderFactory.get("analyzeResDetectQntLimitStep")
				.tasklet(analyzeResDetectQntLimit)
				.build();
	}

	@Bean
	public Flow afterLoadResDetectQntLimitFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.AFTER_LOAD_RES_DETECT_QNT_LIMIT_FLOW)
				.start(buildResDetectQntLimitIndexesFlow)
				.next(analyzeResDetectQntLimitStep())
				.build();
	}
}
