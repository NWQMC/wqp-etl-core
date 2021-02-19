package gov.acwi.wqp.etl.result.index;

import gov.acwi.wqp.etl.*;
import gov.acwi.wqp.etl.index.CreateBasicIndex;
import gov.acwi.wqp.etl.partition.PgDateRangePart;
import gov.acwi.wqp.etl.result.table.ResultPartitionStrategy;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.step.builder.StepBuilder;
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

	public Flow buildResultActivityIndexStep(String tableName) {
		return buildBasicIndex(tableName, "activity");
	}


	@Bean
	public Step buildResultAnalyticalMethodIndexStep() {
		return stepBuilderFactory.get("buildResultAnalyticalMethodIndexStep")
				.tasklet(buildResultAnalyticalMethodIndex)
				.build();
	}

	public Flow buildResultAnalyticalMethodIndexStep(String tableName) {
		return buildBasicIndex(tableName, "analytical_method", "analytical_meth");
	}

	@Bean
	public Step buildResultAssemblageSampledNameIndexStep() {
		return stepBuilderFactory.get("buildResultAssemblageSampledNameIndexStep")
				.tasklet(buildResultAssemblageSampledNameIndex)
				.build();
	}

	public Flow buildResultAssemblageSampledNameIndexStep(String tableName) {
		return buildBasicIndex(tableName, "assemblage_sampled_name", "assm_samp_name");
	}

	@Bean
	public Step buildResultCharacteristicNameIndexStep() {
		return stepBuilderFactory.get("buildResultCharacteristicNameIndexStep")
				.tasklet(buildResultCharacteristicNameIndex)
				.build();
	}

	public Flow buildResultCharacteristicNameIndexStep(String tableName) {
		return buildBasicIndex(tableName, "characteristic_name", "charistic_name");
	}

	@Bean
	public Step buildResultCharacteristicTypeIndexStep() {
		return stepBuilderFactory.get("buildResultCharacteristicTypeIndexStep")
				.tasklet(buildResultCharacteristicTypeIndex)
				.build();
	}

	public Flow buildResultCharacteristicTypeIndexStep(String tableName) {
		return buildBasicIndex(tableName, "characteristic_type", "charistic_type");
	}

	@Bean
	public Step buildResultCountryIndexStep() {
		return stepBuilderFactory.get("buildResultCountryIndexStep")
				.tasklet(buildResultCountryIndex)
				.build();
	}

	public Flow buildResultCountryIndexStep(String tableName) {
		return buildBasicIndex(tableName, "substring(governmental_unit_code, '^[^:]+')", "country");
	}

	@Bean
	public Step buildResultCountyIndexStep() {
		return stepBuilderFactory.get("buildResultCountyIndexStep")
				.tasklet(buildResultCountyIndex)
				.build();
	}

	public Flow buildResultCountyIndexStep(String tableName) {
		return buildBasicIndex(tableName, "substring(governmental_unit_code, '^[^:]+:[^:]+:[^:]+$')", "county");
	}

	@Bean
	public Step buildResultEventDateIndexStep() {
		return stepBuilderFactory.get("buildResultEventDateIndexStep")
				.tasklet(buildResultEventDateIndex)
				.build();
	}

	public Flow buildResultEventDateIndexStep(String tableName) {
		return buildBasicIndex(tableName, "event_date");
	}

	@Bean
	public Step buildResultGeomIndexStep() {
		return stepBuilderFactory.get("buildResultGeomIndexStep")
				.tasklet(buildResultGeomIndex)
				.build();
	}

	public Flow buildResultGeomIndexStep(String tableName) {
		return buildGeomIndex(tableName, "geom", null);
	}

	@Bean
	public Step buildResultGeom2163IndexStep() {
		return stepBuilderFactory.get("buildResultGeom2163IndexStep")
				.tasklet(buildResultGeom2163Index)
				.build();
	}

	public Flow buildResultGeom2163IndexStep(String tableName) {
		return buildGeomIndex(tableName, "geom", 2163);
	}

	@Bean
	public Step buildResultHuc10IndexStep() {
		return stepBuilderFactory.get("buildResultHuc10IndexStep")
				.tasklet(buildResultHuc10Index)
				.build();
	}

	public Flow buildResultHuc10IndexStep(String tableName) {
		return buildBasicIndex(tableName, "substring(huc, '[0-9]{10}')", "huc_10");
	}

	@Bean
	public Step buildResultHuc12IndexStep() {
		return stepBuilderFactory.get("buildResultHuc12IndexStep")
				.tasklet(buildResultHuc12Index)
				.build();
	}

	public Flow buildResultHuc12IndexStep(String tableName) {
		return buildBasicIndex(tableName, "substring(huc, '[0-9]{12}')", "huc_12");
	}

	@Bean
	public Step buildResultHuc2IndexStep() {
		return stepBuilderFactory.get("buildResultHuc2IndexStep")
				.tasklet(buildResultHuc2Index)
				.build();
	}

	public Flow buildResultHuc2IndexStep(String tableName) {
		return buildBasicIndex(tableName, "substring(huc, '[0-9]{2}')", "huc_2");
	}

	@Bean
	public Step buildResultHuc4IndexStep() {
		return stepBuilderFactory.get("buildResultHuc4IndexStep")
				.tasklet(buildResultHuc4Index)
				.build();
	}

	public Flow buildResultHuc4IndexStep(String tableName) {
		return buildBasicIndex(tableName, "substring(huc, '[0-9]{4}')", "huc_4");
	}

	@Bean
	public Step buildResultHuc6IndexStep() {
		return stepBuilderFactory.get("buildResultHuc6IndexStep")
				.tasklet(buildResultHuc6Index)
				.build();
	}

	public Flow buildResultHuc6IndexStep(String tableName) {
		return buildBasicIndex(tableName, "substring(huc, '[0-9]{6}')", "huc_6");
	}

	@Bean
	public Step buildResultHuc8IndexStep() {
		return stepBuilderFactory.get("buildResultHuc8IndexStep")
				.tasklet(buildResultHuc8Index)
				.build();
	}

	public Flow buildResultHuc8IndexStep(String tableName) {
		return buildBasicIndex(tableName, "substring(huc, '[0-9]{8}')", "huc_8");
	}

	@Bean
	public Step buildResultOrganizationIndexStep() {
		return stepBuilderFactory.get("buildResultOrganizationIndexStep")
				.tasklet(buildResultOrganizationIndex)
				.build();
	}

	public Flow buildResultOrganizationIndexStep(String tableName) {
		return buildBasicIndex(tableName, "organization");
	}

	@Bean
	public Step buildResultProjectIdIndexStep() {
		return stepBuilderFactory.get("buildResultProjectIdIndexStep")
				.tasklet(buildResultProjectIdIndex)
				.build();
	}

	public Flow buildResultProjectIdIndexStep(String tableName) {
		return buildBasicIndex(tableName, "project_id");
	}

	@Bean
	public Step buildResultPCodeIndexStep() {
		return stepBuilderFactory.get("buildResultPCodeIndexStep")
				.tasklet(buildResultPCodeIndex)
				.build();
	}

	public Flow buildResultPCodeIndexStep(String tableName) {
		return buildBasicIndex(tableName, "p_code");
	}

	@Bean
	public Step buildResultSampleMediaIndexStep() {
		return stepBuilderFactory.get("buildResultSampleMediaIndexStep")
				.tasklet(buildResultSampleMediaIndex)
				.build();
	}

	public Flow buildResultSampleMediaIndexStep(String tableName) {
		return buildBasicIndex(tableName, "sample_media");
	}

	@Bean
	public Step buildResultSampleTissueTaxonomicNameIndexStep() {
		return stepBuilderFactory.get("buildResultSampleTissueTaxonomicNameIndexStep")
				.tasklet(buildResultSampleTissueTaxonomicNameIndex)
				.build();
	}

	public Flow buildResultSampleTissueTaxonomicNameIndexStep(String tableName) {
		return buildBasicIndex(tableName, "sample_tissue_taxonomic_name", "tissue_taxo");
	}

	@Bean
	public Step buildResultSiteIdIndexStep() {
		return stepBuilderFactory.get("buildResultSiteIdIndexStep")
				.tasklet(buildResultSiteIdIndex)
				.build();
	}

	public Flow buildResultSiteIdIndexStep(String tableName) {
		return buildBasicIndex(tableName, "site_id");
	}

	@Bean
	public Step buildResultSiteTypeIndexStep() {
		return stepBuilderFactory.get("buildResultSiteTypeIndexStep")
				.tasklet(buildResultSiteTypeIndex)
				.build();
	}

	public Flow buildResultSiteTypeIndexStep(String tableName) {
		return buildBasicIndex(tableName, "site_type");
	}

	@Bean
	public Step buildResultStateIndexStep() {
		return stepBuilderFactory.get("buildResultStateIndexStep")
				.tasklet(buildResultStateIndex)
				.build();
	}

	public Flow buildResultStateIndexStep(String tableName) {
		return buildBasicIndex(tableName, "substring(governmental_unit_code, '^[^:]+:[^:]+')", "state");
	}

	@Bean
	public Step buildResultStationIdIndexStep() {
		return stepBuilderFactory.get("buildResultStationIdIndexStep")
				.tasklet(buildResultStationIdIndex)
				.build();
	}

	public Flow buildResultStationIdIndexStep(String tableName) {
		return buildBasicIndex(tableName, "station_id");
	}

	public Flow buildBasicIndex(String tableName, String columnName) {
		return buildBasicIndex(tableName, columnName, columnName);
	}

	public Flow buildBasicIndex(String tableName, String columnName, String shortColName) {
		String idxName = tableName + "_" + shortColName;

		return concurrent.makeFlow(stepBuilderFactory.get("buildIndex_" + idxName)
				                           .tasklet(new CreateBasicIndex(jdbcTemplate, config.getWqpSchemaName(),
						                           tableName, idxName, columnName))
				                           .build());
	}

	public Flow buildGeomIndex(String tableName, String columnName, Integer projection) {
		return buildGeomIndex(tableName, columnName, columnName, projection);
	}

	public Flow buildGeomIndex(String tableName, String columnName, String shortColName, Integer projection) {
		String idxName = tableName + "_" + shortColName;

		if (projection != null) {
			idxName+= "_" + projection;
		}

		return concurrent.makeFlow(stepBuilderFactory.get("buildIndex_" + idxName)
				                           .tasklet(new CreateBasicIndex(jdbcTemplate, config.getWqpSchemaName(),
						                           tableName, idxName, columnName))
				                           .build());
	}

	@Bean
	public Flow buildResultIndexesFlow() {

		List<PgDateRangePart> parts = resultPartitionStrategy.getPartitions();

		FlowBuilder<SimpleFlow> mainFlow = new FlowBuilder<SimpleFlow>("buildResultIndexesFlow");

		FlowBuilder.SplitBuilder<SimpleFlow> splitChildIdxBuild = mainFlow.split(concurrent.taskExecutor());
		//Create indexes individually on each partition table
		for (PgDateRangePart part : parts) {
			String tblName = part.getTableName();
			{
				splitChildIdxBuild.add(buildResultActivityIndexStep(tblName));
				splitChildIdxBuild.add(buildResultAnalyticalMethodIndexStep(tblName));
				splitChildIdxBuild.add(buildResultAssemblageSampledNameIndexStep(tblName));
				splitChildIdxBuild.add(buildResultCharacteristicNameIndexStep(tblName));
				splitChildIdxBuild.add(buildResultCharacteristicTypeIndexStep(tblName));
				splitChildIdxBuild.add(buildResultCountryIndexStep(tblName));
				splitChildIdxBuild.add(buildResultCountyIndexStep(tblName));
				splitChildIdxBuild.add(buildResultEventDateIndexStep(tblName));
				splitChildIdxBuild.add(buildResultGeomIndexStep(tblName));
				splitChildIdxBuild.add(buildResultGeom2163IndexStep(tblName));
				splitChildIdxBuild.add(buildResultHuc10IndexStep(tblName));
				splitChildIdxBuild.add(buildResultHuc12IndexStep(tblName));
				splitChildIdxBuild.add(buildResultHuc2IndexStep(tblName));
				splitChildIdxBuild.add(buildResultHuc4IndexStep(tblName));
				splitChildIdxBuild.add(buildResultHuc6IndexStep(tblName));
				splitChildIdxBuild.add(buildResultHuc8IndexStep(tblName));
				splitChildIdxBuild.add(buildResultOrganizationIndexStep(tblName));
				splitChildIdxBuild.add(buildResultProjectIdIndexStep(tblName));
				splitChildIdxBuild.add(buildResultPCodeIndexStep(tblName));
				splitChildIdxBuild.add(buildResultSampleMediaIndexStep(tblName));
				splitChildIdxBuild.add(buildResultSampleTissueTaxonomicNameIndexStep(tblName));
				splitChildIdxBuild.add(buildResultSiteIdIndexStep(tblName));
				splitChildIdxBuild.add(buildResultSiteTypeIndexStep(tblName));
				splitChildIdxBuild.add(buildResultStateIndexStep(tblName));
				splitChildIdxBuild.add(buildResultStationIdIndexStep(tblName));
			}

		}

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
