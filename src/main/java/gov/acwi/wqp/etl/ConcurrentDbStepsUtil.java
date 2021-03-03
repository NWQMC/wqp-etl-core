package gov.acwi.wqp.etl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.HashMap;

/**
 * Simplifies creating parallel Steps.
 */
@Component
public class ConcurrentDbStepsUtil {
	private static final Logger LOG = LoggerFactory.getLogger(ConcurrentDbStepsUtil.class);

	protected ConfigurationService config;

	/**
	 * Don't create your own instances!  Simply @Autowired the shared instance where you need one.
	 * The destroy() method must be called on each instance so that the contained threadpool is shut down, otherwise
	 * the process will hang after job completion.  Autowiring a reference to this class allows that to happen automatically.
	 * @param config
	 */
	@Autowired
	public ConcurrentDbStepsUtil(ConfigurationService config) {
		this.config = config;

		LOG.info("ConcurrentDbStepsUtil instance created (Please remove this log statement at some point)");
	}

	//Lazy create the Executor
	private transient ThreadPoolTaskExecutor executor = null;

	//Sync access to the executor
	private final Object executorLock = new Object();

	/**
	 * Get a TaskExecutor, which is nominally a ThreadPoolTaskExecutor.
	 * The ThreadPoolTaskExecutor uses ConfigurationService.getDbOperationConcurrency() to set its concurrency.
	 * @return
	 */
	public TaskExecutor taskExecutor() {

		synchronized (executorLock) {

			if (executor == null) {
				ThreadPoolTaskExecutor exe = new ThreadPoolTaskExecutor();
				exe.setMaxPoolSize(config.getDbOperationConcurrency());
				exe.setCorePoolSize(config.getDbOperationConcurrency());    //Set both to actually have that many threads
				exe.setThreadNamePrefix("parallel_exe");
				exe.initialize();
				executor = exe;
			}

			return executor;
		}
	}

	/**
	 * Wrap a step into a flow.
	 * Only flows can be executed in parallel, not individual steps, thus require a wrap.
	 * @param step
	 * @return
	 */
	public static Flow makeFlow(Step step) {
		return new FlowBuilder<SimpleFlow>("FlowForStep_" + step.getName())
				       .start(step).build();
	}

	/**
	 * Automatically wrap tasks as Flows and does a sort of tasks before adding them.
	 * The sort is based on the REVERSED step name.  For most table related tasks, this will ensure
	 * that operations on the same table will not be next to each other in the list, so that operations
	 * on the database can be less entangled.
	 */
	public static class ParallelStepBuilder {
		HashMap<String, Flow> flowmap = new HashMap<>();

		public void add(Step step) {
			flowmap.put(step.getName(), makeFlow(step));
		}

		/**
		 * Return an array of Flows that were added.
		 * The returned order is:
		 * * Alfa-sorted based on the reversed letters of name of the step.
		 *
		 * This weird sorting behavior ensures that the typical step name structure, i.e. 'tablename_do_a_thing',
		 * results in the tables being spread out in the list so there is less db contention for the same table.
		 * @return
		 */
		public Flow[] getFlows() {
			return flowmap.keySet().stream().map( k -> new StringBuilder(k).reverse().toString() ).sorted()
					.map( k -> new StringBuilder(k).reverse().toString() )
					.map( k -> flowmap.get(k)).toArray(Flow[]::new);

		}
	}

	@PreDestroy
	public void destroy() {
		LOG.info("ConcurrentDbStepsUtil destroy called (Please remove this log statement)");
		synchronized (executorLock) {
			if (executor != null) {
				executor.shutdown();
				LOG.info("ConcurrentDbStepsUtil destroy: executor shutdown (Please remove this log statement)");
			} else {
				LOG.info("ConcurrentDbStepsUtil destroy: executor was null (Please remove this log statement)");
			}
		}
	}


}
