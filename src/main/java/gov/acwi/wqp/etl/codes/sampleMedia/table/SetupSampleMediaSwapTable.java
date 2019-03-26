package gov.acwi.wqp.etl.codes.sampleMedia.table;

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
public class SetupSampleMediaSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropSampleMediaSwapTable")
	private Tasklet dropSampleMediaSwapTable;

	@Autowired
	@Qualifier("createSampleMediaSwapTable")
	private Tasklet createSampleMediaSwapTable;

	@Bean
	public Step dropSampleMediaSwapTableStep() {
		return stepBuilderFactory.get("dropSampleMediaSwapTableStep")
				.tasklet(dropSampleMediaSwapTable)
				.build();
	}

	@Bean
	public Step createSampleMediaSwapTableStep() {
		return stepBuilderFactory.get("createSampleMediaSwapTableStep")
				.tasklet(createSampleMediaSwapTable)
				.build();
	}

	@Bean
	public Flow setupSampleMediaSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>("setupSampleMediaSwapTableFlow")
				.start(dropSampleMediaSwapTableStep())
				.next(createSampleMediaSwapTableStep())
				.build();
	}
}
