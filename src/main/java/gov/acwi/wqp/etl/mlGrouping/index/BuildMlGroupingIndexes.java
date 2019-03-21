package gov.acwi.wqp.etl.mlGrouping.index;

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
public class BuildMlGroupingIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildMlGroupingStationIdIndex")
	private Tasklet buildMlGroupingStationIdIndex;

	@Bean
	public Step buildMlGroupingStationIdIndexStep() {
		return stepBuilderFactory.get("buildMlGroupingStationIdIndexStep")
				.tasklet(buildMlGroupingStationIdIndex)
				.build();
	}

	@Bean
	public Flow buildMlGroupingIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildMlGroupingIndexesFlow")
				.start(buildMlGroupingStationIdIndexStep())
				.build();
	}

}
