package gov.acwi.wqp.etl.result.index;

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
public class BuildResultIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildResultActivityIndex")
	private Tasklet buildResultActivityIndex;

	@Autowired
	@Qualifier("buildResultAnalyticalMethodIndex")
	private Tasklet buildResultAnalyticalMethodIndex;

	@Autowired
	@Qualifier("buildResultAssemblageSampledNameIndex")
	private Tasklet buildResultAssemblageSampledNameIndex;

	@Autowired
	@Qualifier("buildResultCharacteristicNameIndex")
	private Tasklet buildResultCharacteristicNameIndex;

	@Autowired
	@Qualifier("buildResultCharacteristicTypeIndex")
	private Tasklet buildResultCharacteristicTypeIndex;

	@Autowired
	@Qualifier("buildResultCountryIndex")
	private Tasklet buildResultCountryIndex;

	@Autowired
	@Qualifier("buildResultCountyIndex")
	private Tasklet buildResultCountyIndex;

	@Autowired
	@Qualifier("buildResultEventDateIndex")
	private Tasklet buildResultEventDateIndex;

	@Autowired
	@Qualifier("buildResultGeomIndex")
	private Tasklet buildResultGeomIndex;

	@Autowired
	@Qualifier("buildResultHuc10Index")
	private Tasklet buildResultHuc10Index;

	@Autowired
	@Qualifier("buildResultHuc12Index")
	private Tasklet buildResultHuc12Index;

	@Autowired
	@Qualifier("buildResultHuc2Index")
	private Tasklet buildResultHuc2Index;

	@Autowired
	@Qualifier("buildResultHuc4Index")
	private Tasklet buildResultHuc4Index;

	@Autowired
	@Qualifier("buildResultHuc6Index")
	private Tasklet buildResultHuc6Index;

	@Autowired
	@Qualifier("buildResultHuc8Index")
	private Tasklet buildResultHuc8Index;

	@Autowired
	@Qualifier("buildResultOrganizationIndex")
	private Tasklet buildResultOrganizationIndex;

	@Autowired
	@Qualifier("buildResultProjectIdIndex")
	private Tasklet buildResultProjectIdIndex;

	@Autowired
	@Qualifier("buildResultPCodeIndex")
	private Tasklet buildResultPCodeIndex;

	@Autowired
	@Qualifier("buildResultSampleMediaIndex")
	private Tasklet buildResultSampleMediaIndex;

	@Autowired
	@Qualifier("buildResultSampleTissueTaxonomicNameIndex")
	private Tasklet buildResultSampleTissueTaxonomicNameIndex;

	@Autowired
	@Qualifier("buildResultSiteIdIndex")
	private Tasklet buildResultSiteIdIndex;

	@Autowired
	@Qualifier("buildResultSiteTypeIndex")
	private Tasklet buildResultSiteTypeIndex;

	@Autowired
	@Qualifier("buildResultStateIndex")
	private Tasklet buildResultStateIndex;

	@Autowired
	@Qualifier("buildResultStationIdIndex")
	private Tasklet buildResultStationIdIndex;


	@Bean
	public Step buildResultActivityIndexStep() {
		return stepBuilderFactory.get("buildResultActivityIndexStep")
				.tasklet(buildResultActivityIndex)
				.build();
	}

	@Bean
	public Step buildResultAnalyticalMethodIndexStep() {
		return stepBuilderFactory.get("buildResultAnalyticalMethodIndexStep")
				.tasklet(buildResultAnalyticalMethodIndex)
				.build();
	}

	@Bean
	public Step buildResultAssemblageSampledNameIndexStep() {
		return stepBuilderFactory.get("buildResultAssemblageSampledNameIndexStep")
				.tasklet(buildResultAssemblageSampledNameIndex)
				.build();
	}

	@Bean
	public Step buildResultCharacteristicNameIndexStep() {
		return stepBuilderFactory.get("buildResultCharacteristicNameIndexStep")
				.tasklet(buildResultCharacteristicNameIndex)
				.build();
	}

	@Bean
	public Step buildResultCharacteristicTypeIndexStep() {
		return stepBuilderFactory.get("buildResultCharacteristicTypeIndexStep")
				.tasklet(buildResultCharacteristicTypeIndex)
				.build();
	}

	@Bean
	public Step buildResultCountryIndexStep() {
		return stepBuilderFactory.get("buildResultCountryIndexStep")
				.tasklet(buildResultCountryIndex)
				.build();
	}

	@Bean
	public Step buildResultCountyIndexStep() {
		return stepBuilderFactory.get("buildResultCountyIndexStep")
				.tasklet(buildResultCountyIndex)
				.build();
	}

	@Bean
	public Step buildResultEventDateIndexStep() {
		return stepBuilderFactory.get("buildResultEventDateIndexStep")
				.tasklet(buildResultEventDateIndex)
				.build();
	}

	@Bean
	public Step buildResultGeomIndexStep() {
		return stepBuilderFactory.get("buildResultGeomIndexStep")
				.tasklet(buildResultGeomIndex)
				.build();
	}

	@Bean
	public Step buildResultHuc10IndexStep() {
		return stepBuilderFactory.get("buildResultHuc10IndexStep")
				.tasklet(buildResultHuc10Index)
				.build();
	}

	@Bean
	public Step buildResultHuc12IndexStep() {
		return stepBuilderFactory.get("buildResultHuc12IndexStep")
				.tasklet(buildResultHuc12Index)
				.build();
	}

	@Bean
	public Step buildResultHuc2IndexStep() {
		return stepBuilderFactory.get("buildResultHuc2IndexStep")
				.tasklet(buildResultHuc2Index)
				.build();
	}

	@Bean
	public Step buildResultHuc4IndexStep() {
		return stepBuilderFactory.get("buildResultHuc4IndexStep")
				.tasklet(buildResultHuc4Index)
				.build();
	}

	@Bean
	public Step buildResultHuc6IndexStep() {
		return stepBuilderFactory.get("buildResultHuc6IndexStep")
				.tasklet(buildResultHuc6Index)
				.build();
	}

	@Bean
	public Step buildResultHuc8IndexStep() {
		return stepBuilderFactory.get("buildResultHuc8IndexStep")
				.tasklet(buildResultHuc8Index)
				.build();
	}

	@Bean
	public Step buildResultOrganizationIndexStep() {
		return stepBuilderFactory.get("buildResultOrganizationIndexStep")
				.tasklet(buildResultOrganizationIndex)
				.build();
	}

	@Bean
	public Step buildResultProjectIdIndexStep() {
		return stepBuilderFactory.get("buildResultProjectIdIndexStep")
				.tasklet(buildResultProjectIdIndex)
				.build();
	}

	@Bean
	public Step buildResultPCodeIndexStep() {
		return stepBuilderFactory.get("buildResultPCodeIndexStep")
				.tasklet(buildResultPCodeIndex)
				.build();
	}

	@Bean
	public Step buildResultSampleMediaIndexStep() {
		return stepBuilderFactory.get("buildResultSampleMediaIndexStep")
				.tasklet(buildResultSampleMediaIndex)
				.build();
	}

	@Bean
	public Step buildResultSampleTissueTaxonomicNameIndexStep() {
		return stepBuilderFactory.get("buildResultSampleTissueTaxonomicNameIndexStep")
				.tasklet(buildResultSampleTissueTaxonomicNameIndex)
				.build();
	}

	@Bean
	public Step buildResultSiteIdIndexStep() {
		return stepBuilderFactory.get("buildResultSiteIdIndexStep")
				.tasklet(buildResultSiteIdIndex)
				.build();
	}

	@Bean
	public Step buildResultSiteTypeIndexStep() {
		return stepBuilderFactory.get("buildResultSiteTypeIndexStep")
				.tasklet(buildResultSiteTypeIndex)
				.build();
	}

	@Bean
	public Step buildResultStateIndexStep() {
		return stepBuilderFactory.get("buildResultStateIndexStep")
				.tasklet(buildResultStateIndex)
				.build();
	}

	@Bean
	public Step buildResultStationIdIndexStep() {
		return stepBuilderFactory.get("buildResultStationIdIndexStep")
				.tasklet(buildResultStationIdIndex)
				.build();
	}

	@Bean
	public Flow buildResultIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildResultIndexesFlow")
				.start(buildResultActivityIndexStep())
				.next(buildResultAnalyticalMethodIndexStep())
				.next(buildResultAssemblageSampledNameIndexStep())
				.next(buildResultCharacteristicNameIndexStep())
				.next(buildResultCharacteristicTypeIndexStep())
				.next(buildResultCountryIndexStep())
				.next(buildResultCountyIndexStep())
				.next(buildResultEventDateIndexStep())
				.next(buildResultGeomIndexStep())
				.next(buildResultHuc10IndexStep())
				.next(buildResultHuc12IndexStep())
				.next(buildResultHuc2IndexStep())
				.next(buildResultHuc4IndexStep())
				.next(buildResultHuc6IndexStep())
				.next(buildResultHuc8IndexStep())
				.next(buildResultOrganizationIndexStep())
				.next(buildResultProjectIdIndexStep())
				.next(buildResultPCodeIndexStep())
				.next(buildResultSampleMediaIndexStep())
				.next(buildResultSampleTissueTaxonomicNameIndexStep())
				.next(buildResultSiteIdIndexStep())
				.next(buildResultSiteTypeIndexStep())
				.next(buildResultStateIndexStep())
				.next(buildResultStationIdIndexStep())
				.build();
	}

}
