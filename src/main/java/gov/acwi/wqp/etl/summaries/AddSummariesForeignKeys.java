package gov.acwi.wqp.etl.summaries;

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
public class AddSummariesForeignKeys {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("addResultSumForeignKeyMonitoringLocationSum")
	private Tasklet addResultSumForeignKeyMonitoringLocationSum;

	@Autowired
	@Qualifier("addMlGroupingForeignKeyMonitoringLocationSum")
	private Tasklet addMlGroupingForeignKeyMonitoringLocationSum;

	@Bean
	public Step addResultSumForeignKeyMonitoringLocationSumStep() {
		return stepBuilderFactory.get("addResultSumForeignKeyMonitoringLocationSumStep")
				.tasklet(addResultSumForeignKeyMonitoringLocationSum)
				.build();
	}

	@Bean
	public Step addMlGroupingForeignKeyMonitoringLocationSumStep() {
		return stepBuilderFactory.get("addMlGroupingForeignKeyMonitoringLocationSumStep")
				.tasklet(addMlGroupingForeignKeyMonitoringLocationSum)
				.build();
	}

	@Bean
	public Flow addSummariesForeignKeysFlow() {
		return new FlowBuilder<SimpleFlow>("addSummariesForeignKeysFlow")
				.start(addResultSumForeignKeyMonitoringLocationSumStep())
				.next(addMlGroupingForeignKeyMonitoringLocationSumStep())
				.build();
	}

}
