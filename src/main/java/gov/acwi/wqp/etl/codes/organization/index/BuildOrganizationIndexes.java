package gov.acwi.wqp.etl.codes.organization.index;

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
public class BuildOrganizationIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildOrganizationCodeValueIndex")
	private Tasklet buildOrganizationCodeValueIndex;

	@Bean
	public Step buildOrganizationCodeValueIndexStep() {
		return stepBuilderFactory.get("buildOrganizationCodeValueIndexStep")
				.tasklet(buildOrganizationCodeValueIndex)
				.build();
	}

	@Bean
	public Flow buildOrganizationIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildOrganizationIndexesFlow")
				.start(buildOrganizationCodeValueIndexStep())
				.build();
	}

}
