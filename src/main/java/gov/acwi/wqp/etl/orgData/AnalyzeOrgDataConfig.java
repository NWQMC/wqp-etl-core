package gov.acwi.wqp.etl.orgData;

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
public class AnalyzeOrgDataConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("analyzeOrgData")
	private Tasklet analyzeOrgData;

	@Bean
	public Step analyzeOrgDataStep() {
		return stepBuilderFactory.get("analyzeOrgDataStep")
				.tasklet(analyzeOrgData)
				.build();
	}

	@Bean
	public Flow analyzeOrgDataFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.ANALYZE_ORG_DATA_FLOW)
				.start(analyzeOrgDataStep())
				.build();
	}
}
