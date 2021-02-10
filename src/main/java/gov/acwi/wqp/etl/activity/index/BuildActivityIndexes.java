package gov.acwi.wqp.etl.activity.index;

import gov.acwi.wqp.etl.ConcurrentDbStepsUtil;
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
public class BuildActivityIndexes {

	@Autowired
	private ConcurrentDbStepsUtil concurrent;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildActivityActivityIndex")
	private Tasklet buildActivityActivityIndex;

	@Autowired
	@Qualifier("buildActivityActivityIdIndex")
	private Tasklet buildActivityActivityIdIndex;

	@Autowired
	@Qualifier("buildActivityCountryIndex")
	private Tasklet buildActivityCountryIndex;

	@Autowired
	@Qualifier("buildActivityCountyIndex")
	private Tasklet buildActivityCountyIndex;

	@Autowired
	@Qualifier("buildActivityEventDateIndex")
	private Tasklet buildActivityEventDateIndex;

	@Autowired
	@Qualifier("buildActivityGeomIndex")
	private Tasklet buildActivityGeomIndex;

	@Autowired
	@Qualifier("buildActivityGeom2163Index")
	private Tasklet buildActivityGeom2163Index;

	@Autowired
	@Qualifier("buildActivityHuc10Index")
	private Tasklet buildActivityHuc10Index;

	@Autowired
	@Qualifier("buildActivityHuc12Index")
	private Tasklet buildActivityHuc12Index;

	@Autowired
	@Qualifier("buildActivityHuc2Index")
	private Tasklet buildActivityHuc2Index;

	@Autowired
	@Qualifier("buildActivityHuc4Index")
	private Tasklet buildActivityHuc4Index;

	@Autowired
	@Qualifier("buildActivityHuc6Index")
	private Tasklet buildActivityHuc6Index;

	@Autowired
	@Qualifier("buildActivityHuc8Index")
	private Tasklet buildActivityHuc8Index;

	@Autowired
	@Qualifier("buildActivityOrganizationIndex")
	private Tasklet buildActivityOrganizationIndex;

	@Autowired
	@Qualifier("buildActivityProjectIdIndex")
	private Tasklet buildActivityProjectIdIndex;

	@Autowired
	@Qualifier("buildActivitySampleMediaIndex")
	private Tasklet buildActivitySampleMediaIndex;

	@Autowired
	@Qualifier("buildActivitySiteIdIndex")
	private Tasklet buildActivitySiteIdIndex;

	@Autowired
	@Qualifier("buildActivitySiteTypeIndex")
	private Tasklet buildActivitySiteTypeIndex;

	@Autowired
	@Qualifier("buildActivityStateIndex")
	private Tasklet buildActivityStateIndex;

	@Autowired
	@Qualifier("buildActivityStationIdIndex")
	private Tasklet buildActivityStationIdIndex;

	@Bean
	public Step buildActivityActivityIndexStep() {
		return stepBuilderFactory.get("buildActivityActivityIndexStep")
				.tasklet(buildActivityActivityIndex)
				.build();
	}

	@Bean
	public Step buildActivityActivityIdIndexStep() {
		return stepBuilderFactory.get("buildActivityActivityIdIndexStep")
				.tasklet(buildActivityActivityIdIndex)
				.build();
	}

	@Bean
	public Step buildActivityCountryIndexStep() {
		return stepBuilderFactory.get("buildActivityCountryIndexStep")
				.tasklet(buildActivityCountryIndex)
				.build();
	}

	@Bean
	public Step buildActivityCountyIndexStep() {
		return stepBuilderFactory.get("buildActivityCountyIndexStep")
				.tasklet(buildActivityCountyIndex)
				.build();
	}

	@Bean
	public Step buildActivityEventDateIndexStep() {
		return stepBuilderFactory.get("buildActivityEventDateIndexStep")
				.tasklet(buildActivityEventDateIndex)
				.build();
	}

	@Bean
	public Step buildActivityGeomIndexStep() {
		return stepBuilderFactory.get("buildActivityGeomIndexStep")
				.tasklet(buildActivityGeomIndex)
				.build();
	}

	@Bean
	public Step buildActivityGeom2163IndexStep() {
		return stepBuilderFactory.get("buildActivityGeom2163IndexStep")
				.tasklet(buildActivityGeom2163Index)
				.build();
	}

	@Bean
	public Step buildActivityHuc10IndexStep() {
		return stepBuilderFactory.get("buildActivityHuc10IndexStep")
				.tasklet(buildActivityHuc10Index)
				.build();
	}

	@Bean
	public Step buildActivityHuc12IndexStep() {
		return stepBuilderFactory.get("buildActivityHuc12IndexStep")
				.tasklet(buildActivityHuc12Index)
				.build();
	}

	@Bean
	public Step buildActivityHuc2IndexStep() {
		return stepBuilderFactory.get("buildActivityHuc2IndexStep")
				.tasklet(buildActivityHuc2Index)
				.build();
	}

	@Bean
	public Step buildActivityHuc4IndexStep() {
		return stepBuilderFactory.get("buildActivityHuc4IndexStep")
				.tasklet(buildActivityHuc4Index)
				.build();
	}

	@Bean
	public Step buildActivityHuc6IndexStep() {
		return stepBuilderFactory.get("buildActivityHuc6IndexStep")
				.tasklet(buildActivityHuc6Index)
				.build();
	}

	@Bean
	public Step buildActivityHuc8IndexStep() {
		return stepBuilderFactory.get("buildActivityHuc8IndexStep")
				.tasklet(buildActivityHuc8Index)
				.build();
	}

	@Bean
	public Step buildActivityOrganizationIndexStep() {
		return stepBuilderFactory.get("buildActivityOrganizationIndexStep")
				.tasklet(buildActivityOrganizationIndex)
				.build();
	}

	@Bean
	public Step buildActivityProjectIdIndexStep() {
		return stepBuilderFactory.get("buildActivityProjectIdIndexStep")
				.tasklet(buildActivityProjectIdIndex)
				.build();
	}

	@Bean
	public Step buildActivitySampleMediaIndexStep() {
		return stepBuilderFactory.get("buildActivitySampleMediaIndexStep")
				.tasklet(buildActivitySampleMediaIndex)
				.build();
	}

	@Bean
	public Step buildActivitySiteIdIndexStep() {
		return stepBuilderFactory.get("buildActivitySiteIdIndexStep")
				.tasklet(buildActivitySiteIdIndex)
				.build();
	}

	@Bean
	public Step buildActivitySiteTypeIndexStep() {
		return stepBuilderFactory.get("buildActivitySiteTypeIndexStep")
				.tasklet(buildActivitySiteTypeIndex)
				.build();
	}

	@Bean
	public Step buildActivityStateIndexStep() {
		return stepBuilderFactory.get("buildActivityStateIndexStep")
				.tasklet(buildActivityStateIndex)
				.build();
	}

	@Bean
	public Step buildActivityStationIdIndexStep() {
		return stepBuilderFactory.get("buildActivityStationIdIndexStep")
				.tasklet(buildActivityStationIdIndex)
				.build();
	}

	@Bean
	public Flow buildActivityIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildActivityIndexesFlow")
				.split(concurrent.taskExecutor()).add(
					concurrent.makeFlow((buildActivityActivityIndexStep())),
					concurrent.makeFlow(buildActivityActivityIdIndexStep()),
					concurrent.makeFlow(buildActivityCountryIndexStep()),
					concurrent.makeFlow(buildActivityCountyIndexStep()),
					concurrent.makeFlow(buildActivityEventDateIndexStep()),
					concurrent.makeFlow(buildActivityGeomIndexStep()),
					concurrent.makeFlow(buildActivityGeom2163IndexStep()),
					concurrent.makeFlow(buildActivityHuc10IndexStep()),
					concurrent.makeFlow(buildActivityHuc12IndexStep()),
					concurrent.makeFlow(buildActivityHuc2IndexStep()),
					concurrent.makeFlow(buildActivityHuc4IndexStep()),
					concurrent.makeFlow(buildActivityHuc6IndexStep()),
					concurrent.makeFlow(buildActivityHuc8IndexStep()),
					concurrent.makeFlow(buildActivityOrganizationIndexStep()),
					concurrent.makeFlow(buildActivityProjectIdIndexStep()),
					concurrent.makeFlow(buildActivitySampleMediaIndexStep()),
					concurrent.makeFlow(buildActivitySiteIdIndexStep()),
					concurrent.makeFlow(buildActivitySiteTypeIndexStep()),
					concurrent.makeFlow(buildActivityStateIndexStep()),
					concurrent.makeFlow(buildActivityStationIdIndexStep())
				).build();
	}

}
