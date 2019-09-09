package gov.acwi.wqp.etl.resultObject.index;

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
public class BuildResultObjectIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildResultObjectOrganizationIndex")
	private Tasklet buildResultObjectOrganizationIndex;

	@Autowired
	@Qualifier("buildResultObjectActivityIndex")
	private Tasklet buildResultObjectActivityIndex;

	@Autowired
	@Qualifier("buildResultObjectResultIdIndex")
	private Tasklet buildResultObjectResultIdIndex;

	@Bean
	public Step buildResultObjectOrganizationIndexStep() {
		return stepBuilderFactory.get("buildResultObjectOrganizationIndexStep")
				.tasklet(buildResultObjectOrganizationIndex)
				.build();
	}

	@Bean
	public Step buildResultObjectActivityIndexStep() {
		return stepBuilderFactory.get("buildResultObjectActivityIndexStep")
				.tasklet(buildResultObjectActivityIndex)
				.build();
	}

	@Bean
	public Step buildResultObjectResultIdIndexStep() {
		return stepBuilderFactory.get("buildResultObjectResultIdIndexStep")
				.tasklet(buildResultObjectResultIdIndex)
				.build();
	}

	@Bean
	public Flow buildResultObjectIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildResultObjectIndexesFlow")
				.start(buildResultObjectOrganizationIndexStep())
				.next(buildResultObjectActivityIndexStep())
				.next(buildResultObjectResultIdIndexStep())
				.build();
	}

}
