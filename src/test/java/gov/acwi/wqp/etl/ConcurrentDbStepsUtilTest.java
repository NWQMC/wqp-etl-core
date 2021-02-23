package gov.acwi.wqp.etl;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConcurrentDbStepsUtilIT extends BaseFlowIT {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Test
	public void ParallelStepBuilderOrderTest() {
		ConcurrentDbStepsUtil.ParallelStepBuilder parallelBuild = new ConcurrentDbStepsUtil.ParallelStepBuilder();

		parallelBuild.add(createStep("table1_task1"));
		parallelBuild.add(createStep("table1_task2"));
		parallelBuild.add(createStep("table1_task3"));
		parallelBuild.add(createStep("table2_task1"));
		parallelBuild.add(createStep("table2_task2"));
		parallelBuild.add(createStep("table3_task1"));
		parallelBuild.add(createStep("table4_aaa"));

		Flow[] flows = parallelBuild.getFlows();

		//The order should be alpha based on the reverse characters of the step name.
		//The flows contain the name of the step, but have their own prefix, so ignore that part.

		assertTrue(flows[0].getName().contains("table1_task1"));
		assertTrue(flows[1].getName().contains("table2_task1"));
		assertTrue(flows[2].getName().contains("table3_task1"));
		assertTrue(flows[3].getName().contains("table1_task2"));
		assertTrue(flows[4].getName().contains("table2_task2"));
		assertTrue(flows[5].getName().contains("table1_task3"));
		assertTrue(flows[6].getName().contains("table4_aaa"));

	}


	public Step createStep(String name) {
		return stepBuilderFactory.get(name)
				       .tasklet((contribution, chunkContext) -> {
					       return RepeatStatus.FINISHED;
				       }).build();
	}
}
