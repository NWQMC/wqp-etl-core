package gov.acwi.wqp.etl.resDetectQntLimit.table;

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
public class SetupResDetectQntLimitTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropResDetectQntLimitSwapTable")
	private Tasklet dropResDetectQntLimitSwapTable;

	@Autowired
	@Qualifier("createResDetectQntLimitSwapTable")
	private Tasklet createResDetectQntLimitSwapTable;

	@Bean
	public Step dropResDetectQntLimitSwapTableStep() {
		return stepBuilderFactory.get("dropResDetectQntLimitSwapTableStep")
				.tasklet(dropResDetectQntLimitSwapTable)
				.build();
	}

	@Bean
	public Step createResDetectQntLimitSwapTableStep() {
		return stepBuilderFactory.get("createResDetectQntLimitSwapTableStep")
				.tasklet(createResDetectQntLimitSwapTable)
				.build();
	}

	@Bean
	public Flow setupResDetectQntLimitSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupResDetectQntLimitSwapTableFlow")
				.start(dropResDetectQntLimitSwapTableStep())
				.next(createResDetectQntLimitSwapTableStep())
				.build();
	}
}
