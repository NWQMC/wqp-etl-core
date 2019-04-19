package gov.acwi.wqp.etl.summaries.monitoringLocationSum;

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

@Configuration
public class TransformMonitoringLocationSum {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("setupMonitoringLocationSumSwapTableFlow")
	private Flow setupMonitoringLocationSumSwapTableFlow;

	@Autowired
	@Qualifier("transformMonitoringLocationSumTasklet")
	private Tasklet transformMonitoringLocationSumTasklet;

	@Autowired
	@Qualifier("buildMonitoringLocationSumIndexesFlow")
	private Flow buildMonitoringLocationSumIndexesFlow;

	//TODO correct SQL - WQP-1395
//	@Autowired
//	@Qualifier("createMonitoringLocationSumPkTasklet")
//	private Tasklet createMonitoringLocationSumPkTasklet;

	@Bean
	public Step transformMonitoringLocationSumStep() {
		return stepBuilderFactory.get("transformMonitoringLocationSumStep")
				.tasklet(transformMonitoringLocationSumTasklet)
				.build();
	}

	//TODO correct SQL - WQP-1395
//	@Bean
//	public Step createMonitoringLocationSumPkStep() {
//		return stepBuilderFactory.get("createMonitoringLocationSumPkStep")
//				.tasklet(createMonitoringLocationSumPkTasklet)
//				.build();
//	}

	@Bean
	public Flow monitoringLocationSumFlow() {
		return new FlowBuilder<SimpleFlow>("monitoringLocationSumFlow")
				.start(setupMonitoringLocationSumSwapTableFlow)
				.next(transformMonitoringLocationSumStep())
//				.next(createMonitoringLocationSumPkStep())
				.next(buildMonitoringLocationSumIndexesFlow)
				.build();
	}

}
