package gov.acwi.wqp.etl.monitoringLocationObject;

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
public class AfterLoadMonitoringLocationObjectConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildMonitoringLocationObjectIndexesFlow")
	public Flow buildMonitoringLocationObjectIndexesFlow;

	@Autowired
	@Qualifier("addMonitoringLocationObjectPrimaryKey")
	private Tasklet addMonitoringLocationObjectPrimaryKey;

	@Autowired
	@Qualifier("analyzeMonitoringLocationObject")
	private Tasklet analyzeMonitoringLocationObject;

	@Bean
	public Step addMonitoringLocationObjectPrimaryKeyStep() {
		return stepBuilderFactory.get("addMonitoringLocationObjectPrimaryKeyStep")
				.tasklet(addMonitoringLocationObjectPrimaryKey)
				.build();
	}

	@Bean
	public Step analyzeMonitoringLocationObjectStep() {
		return stepBuilderFactory.get("analyzeMonitoringLocationObjectStep")
				.tasklet(analyzeMonitoringLocationObject)
				.build();
	}

	@Bean
	public Flow afterLoadMonitoringLocationObjectFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.AFTER_LOAD_MONITORING_LOCATION_OBJECT_FLOW)
				.start(buildMonitoringLocationObjectIndexesFlow)
				.next(addMonitoringLocationObjectPrimaryKeyStep())
				.next(analyzeMonitoringLocationObjectStep())
				.build();
	}
}
