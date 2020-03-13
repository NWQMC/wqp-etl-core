package gov.acwi.wqp.etl.activityMetric.index;

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
public class BuildActivityMetricIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildActivityMetricActivityIndex")
	private Tasklet buildActivityMetricActivityIndex;

	@Autowired
	@Qualifier("buildActivityMetricActivityIdIndex")
	private Tasklet buildActivityMetricActivityIdIndex;

	@Autowired
	@Qualifier("buildActivityMetricCountryIndex")
	private Tasklet buildActivityMetricCountryIndex;

	@Autowired
	@Qualifier("buildActivityMetricCountyIndex")
	private Tasklet buildActivityMetricCountyIndex;

	@Autowired
	@Qualifier("buildActivityMetricEventDateIndex")
	private Tasklet buildActivityMetricEventDateIndex;

	@Autowired
	@Qualifier("buildActivityMetricGeomIndex")
	private Tasklet buildActivityMetricGeomIndex;

	@Autowired
	@Qualifier("buildActivityMetricGeom2163Index")
	private Tasklet buildActivityMetricGeom2163Index;

	@Autowired
	@Qualifier("buildActivityMetricHuc10Index")
	private Tasklet buildActivityMetricHuc10Index;

	@Autowired
	@Qualifier("buildActivityMetricHuc12Index")
	private Tasklet buildActivityMetricHuc12Index;

	@Autowired
	@Qualifier("buildActivityMetricHuc2Index")
	private Tasklet buildActivityMetricHuc2Index;

	@Autowired
	@Qualifier("buildActivityMetricHuc4Index")
	private Tasklet buildActivityMetricHuc4Index;

	@Autowired
	@Qualifier("buildActivityMetricHuc6Index")
	private Tasklet buildActivityMetricHuc6Index;

	@Autowired
	@Qualifier("buildActivityMetricHuc8Index")
	private Tasklet buildActivityMetricHuc8Index;

	@Autowired
	@Qualifier("buildActivityMetricOrganizationIndex")
	private Tasklet buildActivityMetricOrganizationIndex;

	@Autowired
	@Qualifier("buildActivityMetricSampleMediaIndex")
	private Tasklet buildActivityMetricSampleMediaIndex;

	@Autowired
	@Qualifier("buildActivityMetricSiteIdIndex")
	private Tasklet buildActivityMetricSiteIdIndex;

	@Autowired
	@Qualifier("buildActivityMetricSiteTypeIndex")
	private Tasklet buildActivityMetricSiteTypeIndex;

	@Autowired
	@Qualifier("buildActivityMetricStateIndex")
	private Tasklet buildActivityMetricStateIndex;

	@Autowired
	@Qualifier("buildActivityMetricStationIdIndex")
	private Tasklet buildActivityMetricStationIdIndex;

	@Bean
	public Step buildActivityMetricActivityIndexStep() {
		return stepBuilderFactory.get("buildActivityMetricActivityIndexStep")
				.tasklet(buildActivityMetricActivityIndex)
				.build();
	}

	@Bean
	public Step buildActivityMetricActivityIdIndexStep() {
		return stepBuilderFactory.get("buildActivityMetricActivityIdIndexStep")
				.tasklet(buildActivityMetricActivityIdIndex)
				.build();
	}

	@Bean
	public Step buildActivityMetricCountryIndexStep() {
		return stepBuilderFactory.get("buildActivityMetricCountryIndexStep")
				.tasklet(buildActivityMetricCountryIndex)
				.build();
	}

	@Bean
	public Step buildActivityMetricCountyIndexStep() {
		return stepBuilderFactory.get("buildActivityMetricCountyIndexStep")
				.tasklet(buildActivityMetricCountyIndex)
				.build();
	}

	@Bean
	public Step buildActivityMetricEventDateIndexStep() {
		return stepBuilderFactory.get("buildActivityMetricEventDateIndexStep")
				.tasklet(buildActivityMetricEventDateIndex)
				.build();
	}

	@Bean
	public Step buildActivityMetricGeomIndexStep() {
		return stepBuilderFactory.get("buildActivityMetricGeomIndexStep")
				.tasklet(buildActivityMetricGeomIndex)
				.build();
	}

	@Bean
	public Step buildActivityMetricGeom2163IndexStep() {
		return stepBuilderFactory.get("buildActivityMetricGeom2163IndexStep")
				.tasklet(buildActivityMetricGeom2163Index)
				.build();
	}

	@Bean
	public Step buildActivityMetricHuc10IndexStep() {
		return stepBuilderFactory.get("buildActivityMetricHuc10IndexStep")
				.tasklet(buildActivityMetricHuc10Index)
				.build();
	}

	@Bean
	public Step buildActivityMetricHuc12IndexStep() {
		return stepBuilderFactory.get("buildActivityMetricHuc12IndexStep")
				.tasklet(buildActivityMetricHuc12Index)
				.build();
	}

	@Bean
	public Step buildActivityMetricHuc2IndexStep() {
		return stepBuilderFactory.get("buildActivityMetricHuc2IndexStep")
				.tasklet(buildActivityMetricHuc2Index)
				.build();
	}

	@Bean
	public Step buildActivityMetricHuc4IndexStep() {
		return stepBuilderFactory.get("buildActivityMetricHuc4IndexStep")
				.tasklet(buildActivityMetricHuc4Index)
				.build();
	}

	@Bean
	public Step buildActivityMetricHuc6IndexStep() {
		return stepBuilderFactory.get("buildActivityMetricHuc6IndexStep")
				.tasklet(buildActivityMetricHuc6Index)
				.build();
	}

	@Bean
	public Step buildActivityMetricHuc8IndexStep() {
		return stepBuilderFactory.get("buildActivityMetricHuc8IndexStep")
				.tasklet(buildActivityMetricHuc8Index)
				.build();
	}

	@Bean
	public Step buildActivityMetricOrganizationIndexStep() {
		return stepBuilderFactory.get("buildActivityMetricOrganizationIndexStep")
				.tasklet(buildActivityMetricOrganizationIndex)
				.build();
	}

	@Bean
	public Step buildActivityMetricSampleMediaIndexStep() {
		return stepBuilderFactory.get("buildActivityMetricSampleMediaIndexStep")
				.tasklet(buildActivityMetricSampleMediaIndex)
				.build();
	}

	@Bean
	public Step buildActivityMetricSiteIdIndexStep() {
		return stepBuilderFactory.get("buildActivityMetricSiteIdIndexStep")
				.tasklet(buildActivityMetricSiteIdIndex)
				.build();
	}

	@Bean
	public Step buildActivityMetricSiteTypeIndexStep() {
		return stepBuilderFactory.get("buildActivityMetricSiteTypeIndexStep")
				.tasklet(buildActivityMetricSiteTypeIndex)
				.build();
	}

	@Bean
	public Step buildActivityMetricStateIndexStep() {
		return stepBuilderFactory.get("buildActivityMetricStateIndexStep")
				.tasklet(buildActivityMetricStateIndex)
				.build();
	}

	@Bean
	public Step buildActivityMetricStationIdIndexStep() {
		return stepBuilderFactory.get("buildActivityMetricStationIdIndexStep")
				.tasklet(buildActivityMetricStationIdIndex)
				.build();
	}

	@Bean
	public Flow buildActivityMetricIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildActivityMetricIndexesFlow")
				.start(buildActivityMetricActivityIndexStep())
				.next(buildActivityMetricActivityIdIndexStep())
				.next(buildActivityMetricCountryIndexStep())
				.next(buildActivityMetricCountyIndexStep())
				.next(buildActivityMetricEventDateIndexStep())
				.next(buildActivityMetricGeomIndexStep())
				.next(buildActivityMetricGeom2163IndexStep())
				.next(buildActivityMetricHuc10IndexStep())
				.next(buildActivityMetricHuc12IndexStep())
				.next(buildActivityMetricHuc2IndexStep())
				.next(buildActivityMetricHuc4IndexStep())
				.next(buildActivityMetricHuc6IndexStep())
				.next(buildActivityMetricHuc8IndexStep())
				.next(buildActivityMetricOrganizationIndexStep())
				.next(buildActivityMetricSampleMediaIndexStep())
				.next(buildActivityMetricSiteIdIndexStep())
				.next(buildActivityMetricSiteTypeIndexStep())
				.next(buildActivityMetricStateIndexStep())
				.next(buildActivityMetricStationIdIndexStep())
				.build();
	}

}
