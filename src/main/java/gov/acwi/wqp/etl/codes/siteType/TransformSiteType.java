package gov.acwi.wqp.etl.codes.siteType;

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
public class TransformSiteType {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("setupSiteTypeSwapTableFlow")
	private Flow setupSiteTypeSwapTableFlow;

	@Autowired
	@Qualifier("transformSiteTypeTasklet")
	private Tasklet transformSiteTypeTasklet;

	@Autowired
	@Qualifier("buildSiteTypeIndexesFlow")
	private Flow buildSiteTypeIndexesFlow;

	@Bean
	public Step transformSiteTypeStep() {
		return stepBuilderFactory.get("transformSiteTypeStep")
				.tasklet(transformSiteTypeTasklet)
				.build();
	}

	@Bean
	public Flow createSiteTypeFlow() {
		return new FlowBuilder<SimpleFlow>("createSiteTypeFlow")
				.start(setupSiteTypeSwapTableFlow)
				.next(transformSiteTypeStep())
				.next(buildSiteTypeIndexesFlow)
				.build();
	}

}
