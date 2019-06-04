package gov.acwi.wqp.etl.monitoringLocation;

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
public class AfterLoadMonitoringLocationConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildMonitoringLocationIndexesFlow")
	public Flow buildMonitoringLocationIndexesFlow;

	@Autowired
	@Qualifier("addMonitoringLocationPrimaryKey")
	private Tasklet addMonitoringLocationPrimaryKey;

	@Autowired
	@Qualifier("analyzeMonitoringLocation")
	private Tasklet analyzeMonitoringLocation;

	@Bean
	public Step addMonitoringLocationPrimaryKeyStep() {
		return stepBuilderFactory.get("addMonitoringLocationPrimaryKeyStep")
				.tasklet(addMonitoringLocationPrimaryKey)
				.build();
	}

	@Bean
	public Step analyzeMonitoringLocationStep() {
		return stepBuilderFactory.get("analyzeMonitoringLocationStep")
				.tasklet(analyzeMonitoringLocation)
				.build();
	}

	@Bean
	@Deprecated
	public Flow analyzeMonitoringLocationFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.ANALYZE_MONITORING_LOCATION_FLOW)
				.start(analyzeMonitoringLocationStep())
				.build();
	}

	@Bean
	public Flow afterLoadMonitoringLocationFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.AFTER_LOAD_ORG_DATA_FLOW)
				.start(buildMonitoringLocationIndexesFlow)
				.next(addMonitoringLocationPrimaryKeyStep())
				.next(analyzeMonitoringLocationStep())
				.build();
	}
}
