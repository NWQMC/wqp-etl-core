package gov.acwi.wqp.etl.summaries.resultSum.index;

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
public class BuildResultSumIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildResultSumActivityIdIndex")
	private Tasklet buildResultSumActivityIdIndex;

	@Autowired
	@Qualifier("buildResultSumAnalyticalMethodIndex")
	private Tasklet buildResultSumAnalyticalMethodIndex;

	@Autowired
	@Qualifier("buildResultSumAssemblageSampledNameIndex")
	private Tasklet buildResultSumAssemblageSampledNameIndex;

	@Autowired
	@Qualifier("buildResultSumCharacteristicNameIndex")
	private Tasklet buildResultSumCharacteristicNameIndex;

	@Autowired
	@Qualifier("buildResultSumCharacteristicTypeIndex")
	private Tasklet buildResultSumCharacteristicTypeIndex;

	@Autowired
	@Qualifier("buildResultSumCountryIndex")
	private Tasklet buildResultSumCountryIndex;

	@Autowired
	@Qualifier("buildResultSumCountyIndex")
	private Tasklet buildResultSumCountyIndex;

	@Autowired
	@Qualifier("buildResultSumEventDateIndex")
	private Tasklet buildResultSumEventDateIndex;

	@Autowired
	@Qualifier("buildResultSumGeomIndex")
	private Tasklet buildResultSumGeomIndex;

	@Autowired
	@Qualifier("buildResultSumHuc10Index")
	private Tasklet buildResultSumHuc10Index;

	@Autowired
	@Qualifier("buildResultSumHuc12Index")
	private Tasklet buildResultSumHuc12Index;

	@Autowired
	@Qualifier("buildResultSumHuc2Index")
	private Tasklet buildResultSumHuc2Index;

	@Autowired
	@Qualifier("buildResultSumHuc4Index")
	private Tasklet buildResultSumHuc4Index;

	@Autowired
	@Qualifier("buildResultSumHuc6Index")
	private Tasklet buildResultSumHuc6Index;

	@Autowired
	@Qualifier("buildResultSumHuc8Index")
	private Tasklet buildResultSumHuc8Index;

	@Autowired
	@Qualifier("buildResultSumOrganizationIndex")
	private Tasklet buildResultSumOrganizationIndex;

	@Autowired
	@Qualifier("buildResultSumProjectIdIndex")
	private Tasklet buildResultSumProjectIdIndex;

	@Autowired
	@Qualifier("buildResultSumPCodeIndex")
	private Tasklet buildResultSumPCodeIndex;

	@Autowired
	@Qualifier("buildResultSumSampleMediaIndex")
	private Tasklet buildResultSumSampleMediaIndex;

	@Autowired
	@Qualifier("buildResultSumSampleTissueTaxonomicNameIndex")
	private Tasklet buildResultSumSampleTissueTaxonomicNameIndex;

	@Autowired
	@Qualifier("buildResultSumSiteIdIndex")
	private Tasklet buildResultSumSiteIdIndex;

	@Autowired
	@Qualifier("buildResultSumSiteTypeIndex")
	private Tasklet buildResultSumSiteTypeIndex;

	@Autowired
	@Qualifier("buildResultSumStateIndex")
	private Tasklet buildResultSumStateIndex;

	@Autowired
	@Qualifier("buildResultSumStationIdIndex")
	private Tasklet buildResultSumStationIdIndex;


	@Bean
	public Step buildResultSumActivityIdIndexStep() {
		return stepBuilderFactory.get("buildResultSumActivityIdIndexStep")
				.tasklet(buildResultSumActivityIdIndex)
				.build();
	}

	@Bean
	public Step buildResultSumAnalyticalMethodIndexStep() {
		return stepBuilderFactory.get("buildResultSumAnalyticalMethodIndexStep")
				.tasklet(buildResultSumAnalyticalMethodIndex)
				.build();
	}

	@Bean
	public Step buildResultSumAssemblageSampledNameIndexStep() {
		return stepBuilderFactory.get("buildResultSumAssemblageSampledNameIndexStep")
				.tasklet(buildResultSumAssemblageSampledNameIndex)
				.build();
	}

	@Bean
	public Step buildResultSumCharacteristicNameIndexStep() {
		return stepBuilderFactory.get("buildResultSumCharacteristicNameIndexStep")
				.tasklet(buildResultSumCharacteristicNameIndex)
				.build();
	}

	@Bean
	public Step buildResultSumCharacteristicTypeIndexStep() {
		return stepBuilderFactory.get("buildResultSumCharacteristicTypeIndexStep")
				.tasklet(buildResultSumCharacteristicTypeIndex)
				.build();
	}

	@Bean
	public Step buildResultSumCountryIndexStep() {
		return stepBuilderFactory.get("buildResultSumCountryIndexStep")
				.tasklet(buildResultSumCountryIndex)
				.build();
	}

	@Bean
	public Step buildResultSumCountyIndexStep() {
		return stepBuilderFactory.get("buildResultSumCountyIndexStep")
				.tasklet(buildResultSumCountyIndex)
				.build();
	}

	@Bean
	public Step buildResultSumEventDateIndexStep() {
		return stepBuilderFactory.get("buildResultSumEventDateIndexStep")
				.tasklet(buildResultSumEventDateIndex)
				.build();
	}

	@Bean
	public Step buildResultSumGeomIndexStep() {
		return stepBuilderFactory.get("buildResultSumGeomIndexStep")
				.tasklet(buildResultSumGeomIndex)
				.build();
	}

	@Bean
	public Step buildResultSumHuc10IndexStep() {
		return stepBuilderFactory.get("buildResultSumHuc10IndexStep")
				.tasklet(buildResultSumHuc10Index)
				.build();
	}

	@Bean
	public Step buildResultSumHuc12IndexStep() {
		return stepBuilderFactory.get("buildResultSumHuc12IndexStep")
				.tasklet(buildResultSumHuc12Index)
				.build();
	}

	@Bean
	public Step buildResultSumHuc2IndexStep() {
		return stepBuilderFactory.get("buildResultSumHuc2IndexStep")
				.tasklet(buildResultSumHuc2Index)
				.build();
	}

	@Bean
	public Step buildResultSumHuc4IndexStep() {
		return stepBuilderFactory.get("buildResultSumHuc4IndexStep")
				.tasklet(buildResultSumHuc4Index)
				.build();
	}

	@Bean
	public Step buildResultSumHuc6IndexStep() {
		return stepBuilderFactory.get("buildResultSumHuc6IndexStep")
				.tasklet(buildResultSumHuc6Index)
				.build();
	}

	@Bean
	public Step buildResultSumHuc8IndexStep() {
		return stepBuilderFactory.get("buildResultSumHuc8IndexStep")
				.tasklet(buildResultSumHuc8Index)
				.build();
	}

	@Bean
	public Step buildResultSumOrganizationIndexStep() {
		return stepBuilderFactory.get("buildResultSumOrganizationIndexStep")
				.tasklet(buildResultSumOrganizationIndex)
				.build();
	}

	@Bean
	public Step buildResultSumProjectIdIndexStep() {
		return stepBuilderFactory.get("buildResultSumProjectIdIndexStep")
				.tasklet(buildResultSumProjectIdIndex)
				.build();
	}

	@Bean
	public Step buildResultSumPCodeIndexStep() {
		return stepBuilderFactory.get("buildResultSumPCodeIndexStep")
				.tasklet(buildResultSumPCodeIndex)
				.build();
	}

	@Bean
	public Step buildResultSumSampleMediaIndexStep() {
		return stepBuilderFactory.get("buildResultSumSampleMediaIndexStep")
				.tasklet(buildResultSumSampleMediaIndex)
				.build();
	}

	@Bean
	public Step buildResultSumSampleTissueTaxonomicNameIndexStep() {
		return stepBuilderFactory.get("buildResultSumSampleTissueTaxonomicNameIndexStep")
				.tasklet(buildResultSumSampleTissueTaxonomicNameIndex)
				.build();
	}

	@Bean
	public Step buildResultSumSiteIdIndexStep() {
		return stepBuilderFactory.get("buildResultSumSiteIdIndexStep")
				.tasklet(buildResultSumSiteIdIndex)
				.build();
	}

	@Bean
	public Step buildResultSumSiteTypeIndexStep() {
		return stepBuilderFactory.get("buildResultSumSiteTypeIndexStep")
				.tasklet(buildResultSumSiteTypeIndex)
				.build();
	}

	@Bean
	public Step buildResultSumStateIndexStep() {
		return stepBuilderFactory.get("buildResultSumStateIndexStep")
				.tasklet(buildResultSumStateIndex)
				.build();
	}

	@Bean
	public Step buildResultSumStationIdIndexStep() {
		return stepBuilderFactory.get("buildResultSumStationIdIndexStep")
				.tasklet(buildResultSumStationIdIndex)
				.build();
	}

	@Bean
	public Flow buildResultSumIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildResultSumIndexesFlow")
				.start(buildResultSumActivityIdIndexStep())
				.next(buildResultSumAnalyticalMethodIndexStep())
				.next(buildResultSumAssemblageSampledNameIndexStep())
				.next(buildResultSumCharacteristicNameIndexStep())
				.next(buildResultSumCharacteristicTypeIndexStep())
				.next(buildResultSumCountryIndexStep())
				.next(buildResultSumCountyIndexStep())
				.next(buildResultSumEventDateIndexStep())
				.next(buildResultSumGeomIndexStep())
				.next(buildResultSumHuc10IndexStep())
				.next(buildResultSumHuc12IndexStep())
				.next(buildResultSumHuc2IndexStep())
				.next(buildResultSumHuc4IndexStep())
				.next(buildResultSumHuc6IndexStep())
				.next(buildResultSumHuc8IndexStep())
				.next(buildResultSumOrganizationIndexStep())
				.next(buildResultSumProjectIdIndexStep())
				.next(buildResultSumPCodeIndexStep())
				.next(buildResultSumSampleMediaIndexStep())
				.next(buildResultSumSampleTissueTaxonomicNameIndexStep())
				.next(buildResultSumSiteIdIndexStep())
				.next(buildResultSumSiteTypeIndexStep())
				.next(buildResultSumStateIndexStep())
				.next(buildResultSumStationIdIndexStep())
				.build();
	}

}
