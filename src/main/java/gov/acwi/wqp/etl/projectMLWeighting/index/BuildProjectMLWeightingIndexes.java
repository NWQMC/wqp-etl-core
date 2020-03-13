package gov.acwi.wqp.etl.projectMLWeighting.index;

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
public class BuildProjectMLWeightingIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildProjectMLWeightingCountryIndex")
	private Tasklet buildProjectMLWeightingCountryIndex;

	@Autowired
	@Qualifier("buildProjectMLWeightingCountyIndex")
	private Tasklet buildProjectMLWeightingCountyIndex;

	@Autowired
	@Qualifier("buildProjectMLWeightingGeomIndex")
	private Tasklet buildProjectMLWeightingGeomIndex;

	@Autowired
	@Qualifier("buildProjectMLWeightingGeom2163Index")
	private Tasklet buildProjectMLWeightingGeom2163Index;

	@Autowired
	@Qualifier("buildProjectMLWeightingHuc10Index")
	private Tasklet buildProjectMLWeightingHuc10Index;

	@Autowired
	@Qualifier("buildProjectMLWeightingHuc12Index")
	private Tasklet buildProjectMLWeightingHuc12Index;

	@Autowired
	@Qualifier("buildProjectMLWeightingHuc2Index")
	private Tasklet buildProjectMLWeightingHuc2Index;

	@Autowired
	@Qualifier("buildProjectMLWeightingHuc4Index")
	private Tasklet buildProjectMLWeightingHuc4Index;

	@Autowired
	@Qualifier("buildProjectMLWeightingHuc6Index")
	private Tasklet buildProjectMLWeightingHuc6Index;

	@Autowired
	@Qualifier("buildProjectMLWeightingHuc8Index")
	private Tasklet buildProjectMLWeightingHuc8Index;

	@Autowired
	@Qualifier("buildProjectMLWeightingOrganizationIndex")
	private Tasklet buildProjectMLWeightingOrganizationIndex;

	@Autowired
	@Qualifier("buildProjectMLWeightingProjectIdentifierIndex")
	private Tasklet buildProjectMLWeightingProjectIdentifierIndex;

	@Autowired
	@Qualifier("buildProjectMLWeightingSiteIdIndex")
	private Tasklet buildProjectMLWeightingSiteIdIndex;

	@Autowired
	@Qualifier("buildProjectMLWeightingSiteTypeIndex")
	private Tasklet buildProjectMLWeightingSiteTypeIndex;

	@Autowired
	@Qualifier("buildProjectMLWeightingStateIndex")
	private Tasklet buildProjectMLWeightingStateIndex;

	@Autowired
	@Qualifier("buildProjectMLWeightingStationIdIndex")
	private Tasklet buildProjectMLWeightingStationIdIndex;

	@Bean
	public Step buildProjectMLWeightingCountryIndexStep() {
		return stepBuilderFactory.get("buildProjectMLWeightingCountryIndexStep")
				.tasklet(buildProjectMLWeightingCountryIndex)
				.build();
	}

	@Bean
	public Step buildProjectMLWeightingCountyIndexStep() {
		return stepBuilderFactory.get("buildProjectMLWeightingCountyIndexStep")
				.tasklet(buildProjectMLWeightingCountyIndex)
				.build();
	}

	@Bean
	public Step buildProjectMLWeightingGeomIndexStep() {
		return stepBuilderFactory.get("buildProjectMLWeightingGeomIndexStep")
				.tasklet(buildProjectMLWeightingGeomIndex)
				.build();
	}

	@Bean
	public Step buildProjectMLWeightingGeom2163IndexStep() {
		return stepBuilderFactory.get("buildProjectMLWeightingGeom2163IndexStep")
				.tasklet(buildProjectMLWeightingGeom2163Index)
				.build();
	}

	@Bean
	public Step buildProjectMLWeightingHuc10IndexStep() {
		return stepBuilderFactory.get("buildProjectMLWeightingHuc10IndexStep")
				.tasklet(buildProjectMLWeightingHuc10Index)
				.build();
	}

	@Bean
	public Step buildProjectMLWeightingHuc12IndexStep() {
		return stepBuilderFactory.get("buildProjectMLWeightingHuc12IndexStep")
				.tasklet(buildProjectMLWeightingHuc12Index)
				.build();
	}

	@Bean
	public Step buildProjectMLWeightingHuc2IndexStep() {
		return stepBuilderFactory.get("buildProjectMLWeightingHuc2IndexStep")
				.tasklet(buildProjectMLWeightingHuc2Index)
				.build();
	}

	@Bean
	public Step buildProjectMLWeightingHuc4IndexStep() {
		return stepBuilderFactory.get("buildProjectMLWeightingHuc4IndexStep")
				.tasklet(buildProjectMLWeightingHuc4Index)
				.build();
	}

	@Bean
	public Step buildProjectMLWeightingHuc6IndexStep() {
		return stepBuilderFactory.get("buildProjectMLWeightingHuc6IndexStep")
				.tasklet(buildProjectMLWeightingHuc6Index)
				.build();
	}

	@Bean
	public Step buildProjectMLWeightingHuc8IndexStep() {
		return stepBuilderFactory.get("buildProjectMLWeightingHuc8IndexStep")
				.tasklet(buildProjectMLWeightingHuc8Index)
				.build();
	}

	@Bean
	public Step buildProjectMLWeightingOrganizationIndexStep() {
		return stepBuilderFactory.get("buildProjectMLWeightingOrganizationIndexStep")
				.tasklet(buildProjectMLWeightingOrganizationIndex)
				.build();
	}

	@Bean
	public Step buildProjectMLWeightingProjectIdentifierIndexStep() {
		return stepBuilderFactory.get("buildProjectMLWeightingProjectIdentifierIndexStep")
				.tasklet(buildProjectMLWeightingProjectIdentifierIndex)
				.build();
	}

	@Bean
	public Step buildProjectMLWeightingSiteIdIndexStep() {
		return stepBuilderFactory.get("buildProjectMLWeightingSiteIdIndexStep")
				.tasklet(buildProjectMLWeightingSiteIdIndex)
				.build();
	}

	@Bean
	public Step buildProjectMLWeightingSiteTypeIndexStep() {
		return stepBuilderFactory.get("buildProjectMLWeightingSiteTypeIndexStep")
				.tasklet(buildProjectMLWeightingSiteTypeIndex)
				.build();
	}

	@Bean
	public Step buildProjectMLWeightingStateIndexStep() {
		return stepBuilderFactory.get("buildProjectMLWeightingStateIndexStep")
				.tasklet(buildProjectMLWeightingStateIndex)
				.build();
	}

	@Bean
	public Step buildProjectMLWeightingStationIdIndexStep() {
		return stepBuilderFactory.get("buildProjectMLWeightingStationIdIndexStep")
				.tasklet(buildProjectMLWeightingStationIdIndex)
				.build();
	}

	@Bean
	public Flow buildProjectMLWeightingIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildProjectMLWeightingIndexesFlow")
				.start(buildProjectMLWeightingCountryIndexStep())
				.next(buildProjectMLWeightingCountyIndexStep())
				.next(buildProjectMLWeightingGeomIndexStep())
				.next(buildProjectMLWeightingGeom2163IndexStep())
				.next(buildProjectMLWeightingHuc10IndexStep())
				.next(buildProjectMLWeightingHuc12IndexStep())
				.next(buildProjectMLWeightingHuc2IndexStep())
				.next(buildProjectMLWeightingHuc4IndexStep())
				.next(buildProjectMLWeightingHuc6IndexStep())
				.next(buildProjectMLWeightingHuc8IndexStep())
				.next(buildProjectMLWeightingOrganizationIndexStep())
				.next(buildProjectMLWeightingProjectIdentifierIndexStep())
				.next(buildProjectMLWeightingSiteIdIndexStep())
				.next(buildProjectMLWeightingSiteTypeIndexStep())
				.next(buildProjectMLWeightingStateIndexStep())
				.next(buildProjectMLWeightingStationIdIndexStep())
				.build();
	}

}
