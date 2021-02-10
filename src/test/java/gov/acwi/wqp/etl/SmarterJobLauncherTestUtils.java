package gov.acwi.wqp.etl;

import gov.acwi.wqp.etl.result.index.BuildResultIndexes;
import org.springframework.batch.core.*;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * The basic JobLauncherTestUtils cannot find a Step if it is nested within a Flow in a Flow, so this
 * class provides a configurable backdoor to get it via reflection.
 * @return
 */
@Component
public class SmarterJobLauncherTestUtils extends JobLauncherTestUtils {

	Object stepCreator;

	/**
	 * The Object, typically annotated as @Configuration, that builds the Flows and Steps for a Job.
	 * Usually the tests will request to run a specific Step from a 'build[Step Name]' of that class.
	 * @return
	 */
	public Object getStepCreator() {
		return stepCreator;
	}

	/**
	 * Assign the object, usually in a @BeforeClass or @BeforeTest, that builds the Flows and Steps for a Job.
	 * Usually the tests will request to run a specific Step from a 'build[Step Name]' of that class.
	 * @return
	 */
	public void setStepCreator(final Object stepCreator) {
		this.stepCreator = stepCreator;
	}

	/**
	 * Intercept to scan the class used to build the Flow for the step, even if the JobLauncherTestUtils couldn't find it.
	 * @param stepName
	 * @param jobParameters
	 * @param jobExecutionContext
	 * @return
	 */
	@Override
	public JobExecution launchStep(final String stepName, final JobParameters jobParameters, final ExecutionContext jobExecutionContext) {
		try {
			return super.launchStep(stepName, jobParameters, jobExecutionContext);
		} catch (IllegalStateException ise) {
			//Its possible the Step just could not be found because it is nested in a Flow in a Flow - try another way.

			if (stepCreator != null) {

				try {
					Method method = stepCreator.getClass().getDeclaredMethod(stepName);
					method.setAccessible(true);
					Step step = (Step)(method.invoke(stepCreator));
					return this.getStepRunner().launchStep(step, jobParameters, jobExecutionContext);
				} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					throw new IllegalStateException("Unable to find step '" + stepName +
							                                "' even after attempting to call '" + stepName + "' on " +
							                                stepCreator.getClass().getSimpleName(), e);
				}


			} else {
				throw ise;
			}
		}
	}
}
