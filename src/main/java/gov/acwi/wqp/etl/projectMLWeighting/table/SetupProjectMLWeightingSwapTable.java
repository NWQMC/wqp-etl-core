package gov.acwi.wqp.etl.projectMLWeighting.table;

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

import gov.acwi.wqp.etl.EtlConstantUtils;

@Configuration
public class SetupProjectMLWeightingSwapTable {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropProjectMLWeightingSwapTable")
	private Tasklet dropProjectMLWeightingSwapTable;

	@Autowired
	@Qualifier("createProjectMLWeightingSwapTable")
	private Tasklet createProjectMLWeightingSwapTable;

	@Bean
	public Step dropProjectMLWeightingSwapTableStep() {
		return stepBuilderFactory.get("dropProjectMLWeightingSwapTableStep")
				.tasklet(dropProjectMLWeightingSwapTable)
				.build();
	}

	@Bean
	public Step createProjectMLWeightingSwapTableStep() {
		return stepBuilderFactory.get("createProjectMLWeightingSwapTableStep")
				.tasklet(createProjectMLWeightingSwapTable)
				.build();
	}

	@Bean
	public Flow setupProjectMLWeightingSwapTableFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.SETUP_PROJECT_ML_WEIGHTING_SWAP_TABLE_FLOW)
				.start(dropProjectMLWeightingSwapTableStep())
				.next(createProjectMLWeightingSwapTableStep())
				.build();
	}

}
