package gov.acwi.wqp.etl.resDetectQntLimit.index;

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
public class BuildResDetectQntLimitIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildResDetectQntLimitActivityIndex")
	private Tasklet buildResDetectQntLimitActivityIndex;

	@Autowired
	@Qualifier("buildResDetectQntLimitAnalyticalMethodIndex")
	private Tasklet buildResDetectQntLimitAnalyticalMethodIndex;

	@Autowired
	@Qualifier("buildResDetectQntLimitAssemblageSampledNameIndex")
	private Tasklet buildResDetectQntLimitAssemblageSampledNameIndex;

	@Autowired
	@Qualifier("buildResDetectQntLimitCharacteristicNameIndex")
	private Tasklet buildResDetectQntLimitCharacteristicNameIndex;

	@Autowired
	@Qualifier("buildResDetectQntLimitCharacteristicTypeIndex")
	private Tasklet buildResDetectQntLimitCharacteristicTypeIndex;

	@Autowired
	@Qualifier("buildResDetectQntLimitCountryIndex")
	private Tasklet buildResDetectQntLimitCountryIndex;

	@Autowired
	@Qualifier("buildResDetectQntLimitCountyIndex")
	private Tasklet buildResDetectQntLimitCountyIndex;

	@Autowired
	@Qualifier("buildResDetectQntLimitEventDateIndex")
	private Tasklet buildResDetectQntLimitEventDateIndex;

	@Autowired
	@Qualifier("buildResDetectQntLimitGeomIndex")
	private Tasklet buildResDetectQntLimitGeomIndex;

	@Autowired
	@Qualifier("buildResDetectQntLimitGeom2163Index")
	private Tasklet buildResDetectQntLimitGeom2163Index;

	@Autowired
	@Qualifier("buildResDetectQntLimitHuc10Index")
	private Tasklet buildResDetectQntLimitHuc10Index;

	@Autowired
	@Qualifier("buildResDetectQntLimitHuc12Index")
	private Tasklet buildResDetectQntLimitHuc12Index;

	@Autowired
	@Qualifier("buildResDetectQntLimitHuc2Index")
	private Tasklet buildResDetectQntLimitHuc2Index;

	@Autowired
	@Qualifier("buildResDetectQntLimitHuc4Index")
	private Tasklet buildResDetectQntLimitHuc4Index;

	@Autowired
	@Qualifier("buildResDetectQntLimitHuc6Index")
	private Tasklet buildResDetectQntLimitHuc6Index;

	@Autowired
	@Qualifier("buildResDetectQntLimitHuc8Index")
	private Tasklet buildResDetectQntLimitHuc8Index;

	@Autowired
	@Qualifier("buildResDetectQntLimitOrganizationIndex")
	private Tasklet buildResDetectQntLimitOrganizationIndex;

	@Autowired
	@Qualifier("buildResDetectQntLimitProjectIdIndex")
	private Tasklet buildResDetectQntLimitProjectIdIndex;

	@Autowired
	@Qualifier("buildResDetectQntLimitPCodeIndex")
	private Tasklet buildResDetectQntLimitPCodeIndex;

	@Autowired
	@Qualifier("buildResDetectQntLimitResultIdIndex")
	private Tasklet buildResDetectQntLimitResultIdIndex;

	@Autowired
	@Qualifier("buildResDetectQntLimitSampleMediaIndex")
	private Tasklet buildResDetectQntLimitSampleMediaIndex;

	@Autowired
	@Qualifier("buildResDetectQntLimitSampleTissueTaxonomicNameIndex")
	private Tasklet buildResDetectQntLimitSampleTissueTaxonomicNameIndex;

	@Autowired
	@Qualifier("buildResDetectQntLimitSiteIdIndex")
	private Tasklet buildResDetectQntLimitSiteIdIndex;

	@Autowired
	@Qualifier("buildResDetectQntLimitSiteTypeIndex")
	private Tasklet buildResDetectQntLimitSiteTypeIndex;

	@Autowired
	@Qualifier("buildResDetectQntLimitStateIndex")
	private Tasklet buildResDetectQntLimitStateIndex;

	@Autowired
	@Qualifier("buildResDetectQntLimitStationIdIndex")
	private Tasklet buildResDetectQntLimitStationIdIndex;


	@Bean
	public Step buildResDetectQntLimitActivityIndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitActivityIndexStep")
				.tasklet(buildResDetectQntLimitActivityIndex)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitAnalyticalMethodIndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitAnalyticalMethodIndexStep")
				.tasklet(buildResDetectQntLimitAnalyticalMethodIndex)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitAssemblageSampledNameIndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitAssemblageSampledNameIndexStep")
				.tasklet(buildResDetectQntLimitAssemblageSampledNameIndex)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitCharacteristicNameIndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitCharacteristicNameIndexStep")
				.tasklet(buildResDetectQntLimitCharacteristicNameIndex)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitCharacteristicTypeIndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitCharacteristicTypeIndexStep")
				.tasklet(buildResDetectQntLimitCharacteristicTypeIndex)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitCountryIndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitCountryIndexStep")
				.tasklet(buildResDetectQntLimitCountryIndex)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitCountyIndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitCountyIndexStep")
				.tasklet(buildResDetectQntLimitCountyIndex)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitEventDateIndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitEventDateIndexStep")
				.tasklet(buildResDetectQntLimitEventDateIndex)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitGeomIndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitGeomIndexStep")
				.tasklet(buildResDetectQntLimitGeomIndex)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitGeom2163IndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitGeom2163IndexStep")
				.tasklet(buildResDetectQntLimitGeom2163Index)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitHuc10IndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitHuc10IndexStep")
				.tasklet(buildResDetectQntLimitHuc10Index)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitHuc12IndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitHuc12IndexStep")
				.tasklet(buildResDetectQntLimitHuc12Index)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitHuc2IndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitHuc2IndexStep")
				.tasklet(buildResDetectQntLimitHuc2Index)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitHuc4IndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitHuc4IndexStep")
				.tasklet(buildResDetectQntLimitHuc4Index)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitHuc6IndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitHuc6IndexStep")
				.tasklet(buildResDetectQntLimitHuc6Index)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitHuc8IndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitHuc8IndexStep")
				.tasklet(buildResDetectQntLimitHuc8Index)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitOrganizationIndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitOrganizationIndexStep")
				.tasklet(buildResDetectQntLimitOrganizationIndex)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitProjectIdIndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitProjectIdIndexStep")
				.tasklet(buildResDetectQntLimitProjectIdIndex)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitPCodeIndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitPCodeIndexStep")
				.tasklet(buildResDetectQntLimitPCodeIndex)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitResultIdIndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitResultIdIndexStep")
				.tasklet(buildResDetectQntLimitResultIdIndex)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitSampleMediaIndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitSampleMediaIndexStep")
				.tasklet(buildResDetectQntLimitSampleMediaIndex)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitSampleTissueTaxonomicNameIndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitSampleTissueTaxonomicNameIndexStep")
				.tasklet(buildResDetectQntLimitSampleTissueTaxonomicNameIndex)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitSiteIdIndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitSiteIdIndexStep")
				.tasklet(buildResDetectQntLimitSiteIdIndex)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitSiteTypeIndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitSiteTypeIndexStep")
				.tasklet(buildResDetectQntLimitSiteTypeIndex)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitStateIndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitStateIndexStep")
				.tasklet(buildResDetectQntLimitStateIndex)
				.build();
	}

	@Bean
	public Step buildResDetectQntLimitStationIdIndexStep() {
		return stepBuilderFactory.get("buildResDetectQntLimitStationIdIndexStep")
				.tasklet(buildResDetectQntLimitStationIdIndex)
				.build();
	}

	@Bean
	public Flow buildResDetectQntLimitIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildResDetectQntLimitIndexesFlow")
				.start(buildResDetectQntLimitActivityIndexStep())
				.next(buildResDetectQntLimitAnalyticalMethodIndexStep())
				.next(buildResDetectQntLimitAssemblageSampledNameIndexStep())
				.next(buildResDetectQntLimitCharacteristicNameIndexStep())
				.next(buildResDetectQntLimitCharacteristicTypeIndexStep())
				.next(buildResDetectQntLimitCountryIndexStep())
				.next(buildResDetectQntLimitCountyIndexStep())
				.next(buildResDetectQntLimitEventDateIndexStep())
				.next(buildResDetectQntLimitGeomIndexStep())
				.next(buildResDetectQntLimitGeom2163IndexStep())
				.next(buildResDetectQntLimitHuc10IndexStep())
				.next(buildResDetectQntLimitHuc12IndexStep())
				.next(buildResDetectQntLimitHuc2IndexStep())
				.next(buildResDetectQntLimitHuc4IndexStep())
				.next(buildResDetectQntLimitHuc6IndexStep())
				.next(buildResDetectQntLimitHuc8IndexStep())
				.next(buildResDetectQntLimitOrganizationIndexStep())
				.next(buildResDetectQntLimitProjectIdIndexStep())
				.next(buildResDetectQntLimitPCodeIndexStep())
				.next(buildResDetectQntLimitResultIdIndexStep())
				.next(buildResDetectQntLimitSampleMediaIndexStep())
				.next(buildResDetectQntLimitSampleTissueTaxonomicNameIndexStep())
				.next(buildResDetectQntLimitSiteIdIndexStep())
				.next(buildResDetectQntLimitSiteTypeIndexStep())
				.next(buildResDetectQntLimitStateIndexStep())
				.next(buildResDetectQntLimitStationIdIndexStep())
				.build();
	}

}
