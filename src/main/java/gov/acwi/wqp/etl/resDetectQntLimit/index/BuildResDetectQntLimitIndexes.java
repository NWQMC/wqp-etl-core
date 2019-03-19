package gov.acwi.wqp.etl.resDetectQntLimit.index;

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
public class BuildResDetectQntLimitIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildResDetectQntLimitOrganizationIndex")
	private Tasklet buildResDetectQntLimitOrganizationIndex;

//	@Autowired
//	@Qualifier("buildResDetectQntLimitIdentifierIndex")
//	private Tasklet buildResDetectQntLimitIdentifierIndex;

	@Bean
	public Step buildResDetectQntLimitOrganizationIndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitOrganizationIndexStep")
				.tasklet(buildResDetectQntLimitOrganizationIndex)
				.build();
	}

//	@Bean
//	public Step buildResDetectQntLimitIdentifierIndexStep() {
//		return stepBuilderFactory.get("buildResDetectQntLimitIdentifierIndexStep")
//				.tasklet(buildResDetectQntLimitIdentifierIndex)
//				.build();
//	}

	@Bean
	public Flow buildResDetectQntLimitIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildResDetectQntLimitIndexesFlow")
				.start(buildResDetectQntLimitOrganizationIndexStep())
//				.next(buildResDetectQntLimitIdentifierIndexStep())
				.build();
	}

}
