package gov.acwi.wqp.etl;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

/**
 * Simplifies creating parallel Steps.
 */
@Component
public class ConcurrentDbStepsUtil {

	protected ConfigurationService config;

	@Autowired
	public ConcurrentDbStepsUtil(ConfigurationService config) {
		this.config = config;
	}

	/**
	 * Get a TaskExecutor, which is nominally a SimpleAsyncTaskExecutor.
	 * The SimpleAsyncTaskExecutor uses ConfigurationService.getDbOperationConcurrency() to set its concurrency.
	 * @return
	 */
	public TaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor exe = new SimpleAsyncTaskExecutor("spring_batch_async");
		exe.setConcurrencyLimit(config.getDbOperationConcurrency());
		return exe;
	}

	/**
	 * Wrap a step into a flow.
	 * Only flows can be executed in parallel, not individual steps, thus require a wrap.
	 * @param step
	 * @return
	 */
	public Flow makeFlow(Step step) {
		return new FlowBuilder<SimpleFlow>("FlowForStep_" + step.getName())
				       .start(step).build();
	}
}
