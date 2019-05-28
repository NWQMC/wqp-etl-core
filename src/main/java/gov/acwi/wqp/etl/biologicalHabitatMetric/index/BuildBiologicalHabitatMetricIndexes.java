package gov.acwi.wqp.etl.biologicalHabitatMetric.index;

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
public class BuildBiologicalHabitatMetricIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildBiologicalHabitatMetricCountryIndex")
	private Tasklet buildBiologicalHabitatMetricCountryIndex;

	@Autowired
	@Qualifier("buildBiologicalHabitatMetricCountyIndex")
	private Tasklet buildBiologicalHabitatMetricCountyIndex;

	@Autowired
	@Qualifier("buildBiologicalHabitatMetricGeomIndex")
	private Tasklet buildBiologicalHabitatMetricGeomIndex;

	@Autowired
	@Qualifier("buildBiologicalHabitatMetricHuc10Index")
	private Tasklet buildBiologicalHabitatMetricHuc10Index;

	@Autowired
	@Qualifier("buildBiologicalHabitatMetricHuc12Index")
	private Tasklet buildBiologicalHabitatMetricHuc12Index;

	@Autowired
	@Qualifier("buildBiologicalHabitatMetricHuc2Index")
	private Tasklet buildBiologicalHabitatMetricHuc2Index;

	@Autowired
	@Qualifier("buildBiologicalHabitatMetricHuc4Index")
	private Tasklet buildBiologicalHabitatMetricHuc4Index;

	@Autowired
	@Qualifier("buildBiologicalHabitatMetricHuc6Index")
	private Tasklet buildBiologicalHabitatMetricHuc6Index;

	@Autowired
	@Qualifier("buildBiologicalHabitatMetricHuc8Index")
	private Tasklet buildBiologicalHabitatMetricHuc8Index;

	@Autowired
	@Qualifier("buildBiologicalHabitatMetricOrganizationIndex")
	private Tasklet buildBiologicalHabitatMetricOrganizationIndex;

	@Autowired
	@Qualifier("buildBiologicalHabitatMetricSiteIdIndex")
	private Tasklet buildBiologicalHabitatMetricSiteIdIndex;

	@Autowired
	@Qualifier("buildBiologicalHabitatMetricSiteTypeIndex")
	private Tasklet buildBiologicalHabitatMetricSiteTypeIndex;

	@Autowired
	@Qualifier("buildBiologicalHabitatMetricStateIndex")
	private Tasklet buildBiologicalHabitatMetricStateIndex;

	@Autowired
	@Qualifier("buildBiologicalHabitatMetricStationIdIndex")
	private Tasklet buildBiologicalHabitatMetricStationIdIndex;

	@Bean
	public Step buildBiologicalHabitatMetricCountryIndexStep() {
		return stepBuilderFactory.get("buildBiologicalHabitatMetricCountryIndexStep")
				.tasklet(buildBiologicalHabitatMetricCountryIndex)
				.build();
	}

	@Bean
	public Step buildBiologicalHabitatMetricCountyIndexStep() {
		return stepBuilderFactory.get("buildBiologicalHabitatMetricCountyIndexStep")
				.tasklet(buildBiologicalHabitatMetricCountyIndex)
				.build();
	}
	@Bean
	public Step buildBiologicalHabitatMetricGeomIndexStep() {
		return stepBuilderFactory.get("buildBiologicalHabitatMetricGeomIndexStep")
				.tasklet(buildBiologicalHabitatMetricGeomIndex)
				.build();
	}

	@Bean
	public Step buildBiologicalHabitatMetricHuc10IndexStep() {
		return stepBuilderFactory.get("buildBiologicalHabitatMetricHuc10IndexStep")
				.tasklet(buildBiologicalHabitatMetricHuc10Index)
				.build();
	}

	@Bean
	public Step buildBiologicalHabitatMetricHuc12IndexStep() {
		return stepBuilderFactory.get("buildBiologicalHabitatMetricHuc12IndexStep")
				.tasklet(buildBiologicalHabitatMetricHuc12Index)
				.build();
	}

	@Bean
	public Step buildBiologicalHabitatMetricHuc2IndexStep() {
		return stepBuilderFactory.get("buildBiologicalHabitatMetricHuc2IndexStep")
				.tasklet(buildBiologicalHabitatMetricHuc2Index)
				.build();
	}

	@Bean
	public Step buildBiologicalHabitatMetricHuc4IndexStep() {
		return stepBuilderFactory.get("buildBiologicalHabitatMetricHuc4IndexStep")
				.tasklet(buildBiologicalHabitatMetricHuc4Index)
				.build();
	}

	@Bean
	public Step buildBiologicalHabitatMetricHuc6IndexStep() {
		return stepBuilderFactory.get("buildBiologicalHabitatMetricHuc6IndexStep")
				.tasklet(buildBiologicalHabitatMetricHuc6Index)
				.build();
	}

	@Bean
	public Step buildBiologicalHabitatMetricHuc8IndexStep() {
		return stepBuilderFactory.get("buildBiologicalHabitatMetricHuc8IndexStep")
				.tasklet(buildBiologicalHabitatMetricHuc8Index)
				.build();
	}

	@Bean
	public Step buildBiologicalHabitatMetricOrganizationIndexStep() {
		return stepBuilderFactory.get("buildBiologicalHabitatMetricOrganizationIndexStep")
				.tasklet(buildBiologicalHabitatMetricOrganizationIndex)
				.build();
	}

	@Bean
	public Step buildBiologicalHabitatMetricSiteIdIndexStep() {
		return stepBuilderFactory.get("buildBiologicalHabitatMetricSiteIdIndexStep")
				.tasklet(buildBiologicalHabitatMetricSiteIdIndex)
				.build();
	}

	@Bean
	public Step buildBiologicalHabitatMetricSiteTypeIndexStep() {
		return stepBuilderFactory.get("buildBiologicalHabitatMetricSiteTypeIndexStep")
				.tasklet(buildBiologicalHabitatMetricSiteTypeIndex)
				.build();
	}

	@Bean
	public Step buildBiologicalHabitatMetricStateIndexStep() {
		return stepBuilderFactory.get("buildBiologicalHabitatMetricStateIndexStep")
				.tasklet(buildBiologicalHabitatMetricStateIndex)
				.build();
	}

	@Bean
	public Step buildBiologicalHabitatMetricStationIdIndexStep() {
		return stepBuilderFactory.get("buildBiologicalHabitatMetricStationIdIndexStep")
				.tasklet(buildBiologicalHabitatMetricStationIdIndex)
				.build();
	}

	@Bean
	public Flow buildBiologicalHabitatMetricIndexesFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.BUILD_BIOLOGICAL_HABITAT_METRIC_INDEXES_FLOW)
				.start(buildBiologicalHabitatMetricCountryIndexStep())
				.next(buildBiologicalHabitatMetricCountyIndexStep())
				.next(buildBiologicalHabitatMetricGeomIndexStep())
				.next(buildBiologicalHabitatMetricHuc10IndexStep())
				.next(buildBiologicalHabitatMetricHuc12IndexStep())
				.next(buildBiologicalHabitatMetricHuc2IndexStep())
				.next(buildBiologicalHabitatMetricHuc4IndexStep())
				.next(buildBiologicalHabitatMetricHuc6IndexStep())
				.next(buildBiologicalHabitatMetricHuc8IndexStep())
				.next(buildBiologicalHabitatMetricOrganizationIndexStep())
				.next(buildBiologicalHabitatMetricSiteIdIndexStep())
				.next(buildBiologicalHabitatMetricSiteTypeIndexStep())
				.next(buildBiologicalHabitatMetricStateIndexStep())
				.next(buildBiologicalHabitatMetricStationIdIndexStep())
				.build();
	}

}
