package gov.acwi.wqp.etl.summaries.orgGrouping;

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
public class TransformOrgGrouping {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("setupOrgGroupingSwapTableFlow")
	private Flow setupOrgGroupingSwapTableFlow;

	@Autowired
	@Qualifier("transformOrgGroupingTasklet")
	private Tasklet transformOrgGroupingTasklet;

	@Autowired
	@Qualifier("buildOrgGroupingIndexesFlow")
	private Flow buildOrgGroupingIndexesFlow;

	@Bean
	public Step transformOrgGroupingStep() {
		return stepBuilderFactory.get("transformOrgGroupingStep")
				.tasklet(transformOrgGroupingTasklet)
				.build();
	}

	@Bean
	public Flow orgGroupingFlow() {
		return new FlowBuilder<SimpleFlow>("orgGroupingFlow")
				.start(setupOrgGroupingSwapTableFlow)
				.next(transformOrgGroupingStep())
				.next(buildOrgGroupingIndexesFlow)
				.build();
	}

}
