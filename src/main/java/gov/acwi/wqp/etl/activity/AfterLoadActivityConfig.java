package gov.acwi.wqp.etl.activity;

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
public class AfterLoadActivityConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildActivityIndexesFlow")
	public Flow buildActivityIndexesFlow;

	@Autowired
	@Qualifier("addActivityPrimaryKey")
	private Tasklet addActivityPrimaryKey;

	@Autowired
	@Qualifier("addActivityForeignKeyMonitoringLocation")
	private Tasklet addActivityForeignKeyMonitoringLocation;

	@Autowired
	@Qualifier("analyzeActivity")
	private Tasklet analyzeActivity;

	@Bean
	public Step addActivityPrimaryKeyStep() {
		return stepBuilderFactory.get("addActivityPrimaryKeyStep")
				.tasklet(addActivityPrimaryKey)
				.build();
	}

	@Bean
	public Step addActivityForeignKeyMonitoringLocationStep() {
		return stepBuilderFactory.get("addActivityForeignKeyMonitoringLocationStep")
				.tasklet(addActivityForeignKeyMonitoringLocation)
				.build();
	}

	@Bean
	public Step analyzeActivityStep() {
		return stepBuilderFactory.get("analyzeActivityStep")
				.tasklet(analyzeActivity)
				.build();
	}

	@Bean
	public Flow afterLoadActivityFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.AFTER_LOAD_ACTIVITY_FLOW)
				.start(buildActivityIndexesFlow)
				.next(addActivityPrimaryKeyStep())
				.next(addActivityForeignKeyMonitoringLocationStep())
				.next(analyzeActivityStep())
				.build();
	}
}
