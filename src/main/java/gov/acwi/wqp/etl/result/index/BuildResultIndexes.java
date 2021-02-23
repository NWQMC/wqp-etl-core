package gov.acwi.wqp.etl.result.index;

import gov.acwi.wqp.etl.ConcurrentDbStepsUtil;
import gov.acwi.wqp.etl.ConfigurationService;
import gov.acwi.wqp.etl.index.CreateBasicIndex;
import gov.acwi.wqp.etl.index.CreateGeomIndex;
import gov.acwi.wqp.etl.partition.PgDateRangePart;
import gov.acwi.wqp.etl.result.table.ResultPartitionStrategy;
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
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Configuration
public class BuildResultIndexes {

	@Autowired
	private ConfigurationService config;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	ResultPartitionStrategy resultPartitionStrategy;

	@Autowired
	private ConcurrentDbStepsUtil concurrent;

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
	@Qualifier("buildResultGeom2163Index")
	private Tasklet buildResultGeom2163Index;

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

	public Step buildResultActivityIndexStep(String tableName) {
		return buildBasicIndex(tableName, "activity");
	}

	//
	//Note:  Once we trust the dynamic build of the indexes, these @Bean dedicated class versions can be deleted.
	@Bean
	public Step buildResultAnalyticalMethodIndexStep() {
		return stepBuilderFactory.get("buildResultAnalyticalMethodIndexStep")
				.tasklet(buildResultAnalyticalMethodIndex)
				.build();
	}

	public Step buildResultAnalyticalMethodIndexStep(String tableName) {
		return buildBasicIndex(tableName, "analytical_method", "analytical_meth");
	}

	@Bean
	public Step buildResultAssemblageSampledNameIndexStep() {
		return stepBuilderFactory.get("buildResultAssemblageSampledNameIndexStep")
				.tasklet(buildResultAssemblageSampledNameIndex)
				.build();
	}

	public Step buildResultAssemblageSampledNameIndexStep(String tableName) {
		return buildBasicIndex(tableName, "assemblage_sampled_name", "assm_samp_name");
	}

	@Bean
	public Step buildResultCharacteristicNameIndexStep() {
		return stepBuilderFactory.get("buildResultCharacteristicNameIndexStep")
				.tasklet(buildResultCharacteristicNameIndex)
				.build();
	}

	public Step buildResultCharacteristicNameIndexStep(String tableName) {
		return buildBasicIndex(tableName, "characteristic_name", "charistic_name");
	}

	@Bean
	public Step buildResultCharacteristicTypeIndexStep() {
		return stepBuilderFactory.get("buildResultCharacteristicTypeIndexStep")
				.tasklet(buildResultCharacteristicTypeIndex)
				.build();
	}

	public Step buildResultCharacteristicTypeIndexStep(String tableName) {
		return buildBasicIndex(tableName, "characteristic_type", "charistic_type");
	}

	@Bean
	public Step buildResultCountryIndexStep() {
		return stepBuilderFactory.get("buildResultCountryIndexStep")
				.tasklet(buildResultCountryIndex)
				.build();
	}

	public Step buildResultCountryIndexStep(String tableName) {
		return buildBasicIndex(tableName, "substring(governmental_unit_code, '^[^:]+')", "country");
	}

	@Bean
	public Step buildResultCountyIndexStep() {
		return stepBuilderFactory.get("buildResultCountyIndexStep")
				.tasklet(buildResultCountyIndex)
				.build();
	}

	public Step buildResultCountyIndexStep(String tableName) {
		return buildBasicIndex(tableName, "substring(governmental_unit_code, '^[^:]+:[^:]+:[^:]+$')", "county");
	}

	@Bean
	public Step buildResultEventDateIndexStep() {
		return stepBuilderFactory.get("buildResultEventDateIndexStep")
				.tasklet(buildResultEventDateIndex)
				.build();
	}

	public Step buildResultEventDateIndexStep(String tableName) {
		return buildBasicIndex(tableName, "event_date");
	}

	@Bean
	public Step buildResultGeomIndexStep() {
		return stepBuilderFactory.get("buildResultGeomIndexStep")
				.tasklet(buildResultGeomIndex)
				.build();
	}

	public Step buildResultGeomIndexStep(String tableName) {
		return buildGeomIndex(tableName, "geom", null);
	}

	@Bean
	public Step buildResultGeom2163IndexStep() {
		return stepBuilderFactory.get("buildResultGeom2163IndexStep")
				.tasklet(buildResultGeom2163Index)
				.build();
	}

	public Step buildResultGeom2163IndexStep(String tableName) {
		return buildGeomIndex(tableName, "geom", 2163);
	}

	@Bean
	public Step buildResultHuc10IndexStep() {
		return stepBuilderFactory.get("buildResultHuc10IndexStep")
				.tasklet(buildResultHuc10Index)
				.build();
	}

	public Step buildResultHuc10IndexStep(String tableName) {
		return buildBasicIndex(tableName, "substring(huc, '[0-9]{10}')", "huc_10");
	}

	@Bean
	public Step buildResultHuc12IndexStep() {
		return stepBuilderFactory.get("buildResultHuc12IndexStep")
				.tasklet(buildResultHuc12Index)
				.build();
	}

	public Step buildResultHuc12IndexStep(String tableName) {
		return buildBasicIndex(tableName, "substring(huc, '[0-9]{12}')", "huc_12");
	}

	@Bean
	public Step buildResultHuc2IndexStep() {
		return stepBuilderFactory.get("buildResultHuc2IndexStep")
				.tasklet(buildResultHuc2Index)
				.build();
	}

	public Step buildResultHuc2IndexStep(String tableName) {
		return buildBasicIndex(tableName, "substring(huc, '[0-9]{2}')", "huc_2");
	}

	@Bean
	public Step buildResultHuc4IndexStep() {
		return stepBuilderFactory.get("buildResultHuc4IndexStep")
				.tasklet(buildResultHuc4Index)
				.build();
	}

	public Step buildResultHuc4IndexStep(String tableName) {
		return buildBasicIndex(tableName, "substring(huc, '[0-9]{4}')", "huc_4");
	}

	@Bean
	public Step buildResultHuc6IndexStep() {
		return stepBuilderFactory.get("buildResultHuc6IndexStep")
				.tasklet(buildResultHuc6Index)
				.build();
	}

	public Step buildResultHuc6IndexStep(String tableName) {
		return buildBasicIndex(tableName, "substring(huc, '[0-9]{6}')", "huc_6");
	}

	@Bean
	public Step buildResultHuc8IndexStep() {
		return stepBuilderFactory.get("buildResultHuc8IndexStep")
				.tasklet(buildResultHuc8Index)
				.build();
	}

	public Step buildResultHuc8IndexStep(String tableName) {
		return buildBasicIndex(tableName, "substring(huc, '[0-9]{8}')", "huc_8");
	}

	@Bean
	public Step buildResultOrganizationIndexStep() {
		return stepBuilderFactory.get("buildResultOrganizationIndexStep")
				.tasklet(buildResultOrganizationIndex)
				.build();
	}

	public Step buildResultOrganizationIndexStep(String tableName) {
		return buildBasicIndex(tableName, "organization");
	}

	@Bean
	public Step buildResultProjectIdIndexStep() {
		return stepBuilderFactory.get("buildResultProjectIdIndexStep")
				.tasklet(buildResultProjectIdIndex)
				.build();
	}

	public Step buildResultProjectIdIndexStep(String tableName) {
		return buildBasicIndex(tableName, "project_id");
	}

	@Bean
	public Step buildResultPCodeIndexStep() {
		return stepBuilderFactory.get("buildResultPCodeIndexStep")
				.tasklet(buildResultPCodeIndex)
				.build();
	}

	public Step buildResultPCodeIndexStep(String tableName) {
		return buildBasicIndex(tableName, "p_code");
	}

	@Bean
	public Step buildResultSampleMediaIndexStep() {
		return stepBuilderFactory.get("buildResultSampleMediaIndexStep")
				.tasklet(buildResultSampleMediaIndex)
				.build();
	}

	public Step buildResultSampleMediaIndexStep(String tableName) {
		return buildBasicIndex(tableName, "sample_media");
	}

	@Bean
	public Step buildResultSampleTissueTaxonomicNameIndexStep() {
		return stepBuilderFactory.get("buildResultSampleTissueTaxonomicNameIndexStep")
				.tasklet(buildResultSampleTissueTaxonomicNameIndex)
				.build();
	}

	public Step buildResultSampleTissueTaxonomicNameIndexStep(String tableName) {
		return buildBasicIndex(tableName, "sample_tissue_taxonomic_name", "tissue_taxo");
	}

	@Bean
	public Step buildResultSiteIdIndexStep() {
		return stepBuilderFactory.get("buildResultSiteIdIndexStep")
				.tasklet(buildResultSiteIdIndex)
				.build();
	}

	public Step buildResultSiteIdIndexStep(String tableName) {
		return buildBasicIndex(tableName, "site_id");
	}

	@Bean
	public Step buildResultSiteTypeIndexStep() {
		return stepBuilderFactory.get("buildResultSiteTypeIndexStep")
				.tasklet(buildResultSiteTypeIndex)
				.build();
	}

	public Step buildResultSiteTypeIndexStep(String tableName) {
		return buildBasicIndex(tableName, "site_type");
	}

	@Bean
	public Step buildResultStateIndexStep() {
		return stepBuilderFactory.get("buildResultStateIndexStep")
				.tasklet(buildResultStateIndex)
				.build();
	}

	public Step buildResultStateIndexStep(String tableName) {
		return buildBasicIndex(tableName, "substring(governmental_unit_code, '^[^:]+:[^:]+')", "state");
	}

	@Bean
	public Step buildResultStationIdIndexStep() {
		return stepBuilderFactory.get("buildResultStationIdIndexStep")
				.tasklet(buildResultStationIdIndex)
				.build();
	}

	public Step buildResultStationIdIndexStep(String tableName) {
		return buildBasicIndex(tableName, "station_id");
	}

	public Step buildBasicIndex(String tableName, String columnName) {
		return buildBasicIndex(tableName, columnName, columnName);
	}

	public Step buildBasicIndex(String tableName, String columnName, String shortColName) {
		String idxName = tableName + "_" + shortColName;

		return stepBuilderFactory.get("buildIndex_" + idxName)
				                           .tasklet(new CreateBasicIndex(jdbcTemplate, config.getWqpSchemaName(),
						                           tableName, idxName, columnName))
				                           .build();
	}

	public Step buildGeomIndex(String tableName, String columnName, Integer projection) {
		return buildGeomIndex(tableName, columnName, columnName, projection);
	}

	public Step buildGeomIndex(String tableName, String columnName, String shortColName, Integer projection) {
		String idxName = tableName + "_" + shortColName + ((projection != null)?"_" + projection:"");

		return stepBuilderFactory.get("buildIndex_" + idxName)
				                           .tasklet(new CreateGeomIndex(jdbcTemplate, config.getWqpSchemaName(),
						                           tableName, idxName, columnName, projection))
				                           .build();
	}

	@Bean
	public Flow buildResultIndexesFlow() {

		List<PgDateRangePart> parts = resultPartitionStrategy.getPartitions();

		FlowBuilder<SimpleFlow> mainFlow = new FlowBuilder<SimpleFlow>("buildResultIndexesFlow");

		FlowBuilder.SplitBuilder<SimpleFlow> splitChildIdxBuild = mainFlow.split(concurrent.taskExecutor());


		ConcurrentDbStepsUtil.ParallelStepBuilder parallelBuild = new ConcurrentDbStepsUtil.ParallelStepBuilder();

		//Create indexes individually on each partition table
		for (PgDateRangePart part : parts) {
			String tblName = part.getTableName();
			{
				parallelBuild.add(buildResultActivityIndexStep(tblName));
				parallelBuild.add(buildResultAnalyticalMethodIndexStep(tblName));
				parallelBuild.add(buildResultAssemblageSampledNameIndexStep(tblName));
				parallelBuild.add(buildResultCharacteristicNameIndexStep(tblName));
				parallelBuild.add(buildResultCharacteristicTypeIndexStep(tblName));
				parallelBuild.add(buildResultCountryIndexStep(tblName));
				parallelBuild.add(buildResultCountyIndexStep(tblName));
				parallelBuild.add(buildResultEventDateIndexStep(tblName));
				parallelBuild.add(buildResultGeomIndexStep(tblName));
				parallelBuild.add(buildResultGeom2163IndexStep(tblName));
				parallelBuild.add(buildResultHuc10IndexStep(tblName));
				parallelBuild.add(buildResultHuc12IndexStep(tblName));
				parallelBuild.add(buildResultHuc2IndexStep(tblName));
				parallelBuild.add(buildResultHuc4IndexStep(tblName));
				parallelBuild.add(buildResultHuc6IndexStep(tblName));
				parallelBuild.add(buildResultHuc8IndexStep(tblName));
				parallelBuild.add(buildResultOrganizationIndexStep(tblName));
				parallelBuild.add(buildResultProjectIdIndexStep(tblName));
				parallelBuild.add(buildResultPCodeIndexStep(tblName));
				parallelBuild.add(buildResultSampleMediaIndexStep(tblName));
				parallelBuild.add(buildResultSampleTissueTaxonomicNameIndexStep(tblName));
				parallelBuild.add(buildResultSiteIdIndexStep(tblName));
				parallelBuild.add(buildResultSiteTypeIndexStep(tblName));
				parallelBuild.add(buildResultStateIndexStep(tblName));
				parallelBuild.add(buildResultStationIdIndexStep(tblName));
			}

		}

		splitChildIdxBuild.add(parallelBuild.getFlows());

		//After all the individual partitions are indexed, add the indexes to the parent table
		//As long as this happens after the child index creation, there should be no contention for index names
		//Note:  This uses the same logic as before to ensure index names are unchanged on the parent table.
		//Since these indexes are already created on the child tables, this should happen almost instantly.
		mainFlow.next(buildResultActivityIndexStep());
		mainFlow.next(buildResultAnalyticalMethodIndexStep());
		mainFlow.next(buildResultAssemblageSampledNameIndexStep());
		mainFlow.next(buildResultCharacteristicNameIndexStep());
		mainFlow.next(buildResultCharacteristicTypeIndexStep());
		mainFlow.next(buildResultCountryIndexStep());
		mainFlow.next(buildResultCountyIndexStep());
		mainFlow.next(buildResultEventDateIndexStep());
		mainFlow.next(buildResultGeomIndexStep());
		mainFlow.next(buildResultGeom2163IndexStep());
		mainFlow.next(buildResultHuc10IndexStep());
		mainFlow.next(buildResultHuc12IndexStep());
		mainFlow.next(buildResultHuc2IndexStep());
		mainFlow.next(buildResultHuc4IndexStep());
		mainFlow.next(buildResultHuc6IndexStep());
		mainFlow.next(buildResultHuc8IndexStep());
		mainFlow.next(buildResultOrganizationIndexStep());
		mainFlow.next(buildResultProjectIdIndexStep());
		mainFlow.next(buildResultPCodeIndexStep());
		mainFlow.next(buildResultSampleMediaIndexStep());
		mainFlow.next(buildResultSampleTissueTaxonomicNameIndexStep());
		mainFlow.next(buildResultSiteIdIndexStep());
		mainFlow.next(buildResultSiteTypeIndexStep());
		mainFlow.next(buildResultStateIndexStep());
		mainFlow.next(buildResultStationIdIndexStep());

		return mainFlow.build();

	}

}
