package gov.acwi.wqp.etl.summaries.activitySum.index;

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
public class BuildActivitySumIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildActivitySumActivityIdIndex")
	private Tasklet buildActivitySumActivityIdIndex;

	@Autowired
	@Qualifier("buildActivitySumCountryIndex")
	private Tasklet buildActivitySumCountryIndex;

	@Autowired
	@Qualifier("buildActivitySumCountyIndex")
	private Tasklet buildActivitySumCountyIndex;

	@Autowired
	@Qualifier("buildActivitySumEventDateIndex")
	private Tasklet buildActivitySumEventDateIndex;

	@Autowired
	@Qualifier("buildActivitySumGeomIndex")
	private Tasklet buildActivitySumGeomIndex;

	@Autowired
	@Qualifier("buildActivitySumHuc10Index")
	private Tasklet buildActivitySumHuc10Index;

	@Autowired
	@Qualifier("buildActivitySumHuc12Index")
	private Tasklet buildActivitySumHuc12Index;

	@Autowired
	@Qualifier("buildActivitySumHuc2Index")
	private Tasklet buildActivitySumHuc2Index;

	@Autowired
	@Qualifier("buildActivitySumHuc4Index")
	private Tasklet buildActivitySumHuc4Index;

	@Autowired
	@Qualifier("buildActivitySumHuc6Index")
	private Tasklet buildActivitySumHuc6Index;

	@Autowired
	@Qualifier("buildActivitySumHuc8Index")
	private Tasklet buildActivitySumHuc8Index;

	@Autowired
	@Qualifier("buildActivitySumOrganizationIndex")
	private Tasklet buildActivitySumOrganizationIndex;

	@Autowired
	@Qualifier("buildActivitySumProjectIdIndex")
	private Tasklet buildActivitySumProjectIdIndex;

	@Autowired
	@Qualifier("buildActivitySumSampleMediaIndex")
	private Tasklet buildActivitySumSampleMediaIndex;

	@Autowired
	@Qualifier("buildActivitySumSiteIdIndex")
	private Tasklet buildActivitySumSiteIdIndex;

	@Autowired
	@Qualifier("buildActivitySumSiteTypeIndex")
	private Tasklet buildActivitySumSiteTypeIndex;

	@Autowired
	@Qualifier("buildActivitySumStateIndex")
	private Tasklet buildActivitySumStateIndex;

	@Autowired
	@Qualifier("buildActivitySumStationIdIndex")
	private Tasklet buildActivitySumStationIdIndex;

	@Bean
	public Step buildActivitySumActivityIdIndexStep() {
		return stepBuilderFactory.get("buildActivitySumActivityIdIndexStep")
				.tasklet(buildActivitySumActivityIdIndex)
				.build();
	}

	@Bean
	public Step buildActivitySumCountryIndexStep() {
		return stepBuilderFactory.get("buildActivitySumCountryIndexStep")
				.tasklet(buildActivitySumCountryIndex)
				.build();
	}

	@Bean
	public Step buildActivitySumCountyIndexStep() {
		return stepBuilderFactory.get("buildActivitySumCountyIndexStep")
				.tasklet(buildActivitySumCountyIndex)
				.build();
	}

	@Bean
	public Step buildActivitySumEventDateIndexStep() {
		return stepBuilderFactory.get("buildActivitySumEventDateIndexStep")
				.tasklet(buildActivitySumEventDateIndex)
				.build();
	}

	@Bean
	public Step buildActivitySumGeomIndexStep() {
		return stepBuilderFactory.get("buildActivitySumGeomIndexStep")
				.tasklet(buildActivitySumGeomIndex)
				.build();
	}

	@Bean
	public Step buildActivitySumHuc10IndexStep() {
		return stepBuilderFactory.get("buildActivitySumHuc10IndexStep")
				.tasklet(buildActivitySumHuc10Index)
				.build();
	}

	@Bean
	public Step buildActivitySumHuc12IndexStep() {
		return stepBuilderFactory.get("buildActivitySumHuc12IndexStep")
				.tasklet(buildActivitySumHuc12Index)
				.build();
	}

	@Bean
	public Step buildActivitySumHuc2IndexStep() {
		return stepBuilderFactory.get("buildActivitySumHuc2IndexStep")
				.tasklet(buildActivitySumHuc2Index)
				.build();
	}

	@Bean
	public Step buildActivitySumHuc4IndexStep() {
		return stepBuilderFactory.get("buildActivitySumHuc4IndexStep")
				.tasklet(buildActivitySumHuc4Index)
				.build();
	}

	@Bean
	public Step buildActivitySumHuc6IndexStep() {
		return stepBuilderFactory.get("buildActivitySumHuc6IndexStep")
				.tasklet(buildActivitySumHuc6Index)
				.build();
	}

	@Bean
	public Step buildActivitySumHuc8IndexStep() {
		return stepBuilderFactory.get("buildActivitySumHuc8IndexStep")
				.tasklet(buildActivitySumHuc8Index)
				.build();
	}

	@Bean
	public Step buildActivitySumOrganizationIndexStep() {
		return stepBuilderFactory.get("buildActivitySumOrganizationIndexStep")
				.tasklet(buildActivitySumOrganizationIndex)
				.build();
	}

	@Bean
	public Step buildActivitySumProjectIdIndexStep() {
		return stepBuilderFactory.get("buildActivitySumProjectIdIndexStep")
				.tasklet(buildActivitySumProjectIdIndex)
				.build();
	}

	@Bean
	public Step buildActivitySumSampleMediaIndexStep() {
		return stepBuilderFactory.get("buildActivitySumSampleMediaIndexStep")
				.tasklet(buildActivitySumSampleMediaIndex)
				.build();
	}

	@Bean
	public Step buildActivitySumSiteIdIndexStep() {
		return stepBuilderFactory.get("buildActivitySumSiteIdIndexStep")
				.tasklet(buildActivitySumSiteIdIndex)
				.build();
	}

	@Bean
	public Step buildActivitySumSiteTypeIndexStep() {
		return stepBuilderFactory.get("buildActivitySumSiteTypeIndexStep")
				.tasklet(buildActivitySumSiteTypeIndex)
				.build();
	}

	@Bean
	public Step buildActivitySumStateIndexStep() {
		return stepBuilderFactory.get("buildActivitySumStateIndexStep")
				.tasklet(buildActivitySumStateIndex)
				.build();
	}

	@Bean
	public Step buildActivitySumStationIdIndexStep() {
		return stepBuilderFactory.get("buildActivitySumStationIdIndexStep")
				.tasklet(buildActivitySumStationIdIndex)
				.build();
	}

	@Bean
	public Flow buildActivitySumIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildActivitySumIndexesFlow")
				.start(buildActivitySumActivityIdIndexStep())
				.next(buildActivitySumCountryIndexStep())
				.next(buildActivitySumCountyIndexStep())
				.next(buildActivitySumEventDateIndexStep())
				.next(buildActivitySumGeomIndexStep())
				.next(buildActivitySumHuc10IndexStep())
				.next(buildActivitySumHuc12IndexStep())
				.next(buildActivitySumHuc2IndexStep())
				.next(buildActivitySumHuc4IndexStep())
				.next(buildActivitySumHuc6IndexStep())
				.next(buildActivitySumHuc8IndexStep())
				.next(buildActivitySumOrganizationIndexStep())
				.next(buildActivitySumProjectIdIndexStep())
				.next(buildActivitySumSampleMediaIndexStep())
				.next(buildActivitySumSiteIdIndexStep())
				.next(buildActivitySumSiteTypeIndexStep())
				.next(buildActivitySumStateIndexStep())
				.next(buildActivitySumStationIdIndexStep())
				.build();
	}

}
