package gov.acwi.wqp.etl.codes.monitoringLoc;

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
public class TransformMonitoringLoc {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("setupMonitoringLocSwapTableFlow")
	private Flow setupMonitoringLocSwapTableFlow;

	@Autowired
	@Qualifier("transformMonitoringLocTasklet")
	private Tasklet transformMonitoringLocTasklet;

	@Autowired
	@Qualifier("buildMonitoringLocIndexesFlow")
	private Flow buildMonitoringLocIndexesFlow;

	@Bean
	public Step transformMonitoringLocStep() {
		return stepBuilderFactory.get("transformMonitoringLocStep")
				.tasklet(transformMonitoringLocTasklet)
				.build();
	}

	@Bean
	public Flow createMonitoringLocFlow() {
		return new FlowBuilder<SimpleFlow>("createMonitoringLocFlow")
				.start(setupMonitoringLocSwapTableFlow)
				.next(transformMonitoringLocStep())
				.next(buildMonitoringLocIndexesFlow)
				.build();
	}

}
