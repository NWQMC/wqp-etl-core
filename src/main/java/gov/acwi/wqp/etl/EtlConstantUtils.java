package gov.acwi.wqp.etl;

public final class EtlConstantUtils {

	//Job Parameter Keys
	public static final String JOB_ID = "jobId";
	public static final String JOB_PARM_DATA_SOURCE = "wqpDataSource";
	public static final String JOB_PARM_DATA_SOURCE_ID = "wqpDataSourceId";
	public static final String JOB_PARM_WQP_SCHEMA = "wqpSchemaName";
	public static final String JOB_PARM_GEO_SCHEMA = "geoSchemaName";
	public static final String JOB_PARM_NWIS_OR_EPA = "nwisOrEpa";

	//Job Parameter Access by Key
	public static final String VALUE_JOB_PARM_DATA_SOURCE = "#{jobParameters['" + JOB_PARM_DATA_SOURCE + "']}";
	public static final String VALUE_JOB_PARM_DATA_SOURCE_ID = "#{jobParameters['" + JOB_PARM_DATA_SOURCE_ID + "']}";
	public static final String VALUE_JOB_PARM_WQP_SCHEMA = "#{jobParameters['" + JOB_PARM_WQP_SCHEMA + "']}";
	public static final String VALUE_JOB_PARM_GEO_SCHEMA = "#{jobParameters['" + JOB_PARM_GEO_SCHEMA + "']}";
	public static final String VALUE_JOB_PARM_NWIS_OR_EPA = "#{jobParameters['" + JOB_PARM_NWIS_OR_EPA + "']}";

	//Connection Properties
	public static final String SPRING_DATASOURCE_WQP = "spring.datasource-wqp";
	public static final String SPRING_DATASOURCE_NWIS = "spring.datasource-nwis";
	public static final String SPRING_DATASOURCE_WQX = "spring.datasource-wqx";

	//Shared Flow Names
	public static final String SETUP_ORG_DATA_SWAP_TABLE_FLOW = "setupOrgDataSwapTableFlow";
	@Deprecated
	public static final String BUILD_ORG_DATA_INDEXES_FLOW = "buildOrgDataIndexesFlow";
	@Deprecated
	public static final String ANALYZE_ORG_DATA_FLOW = "analyzeOrgDataFlow";
	public static final String AFTER_LOAD_ORG_DATA_FLOW = "afterLoadOrgDataFlow";

	public static final String SETUP_PROJECT_DATA_SWAP_TABLE_FLOW = "setupProjectDataSwapTableFlow";
	@Deprecated
	public static final String BUILD_PROJECT_DATA_INDEXES_FLOW = "buildProjectDataIndexesFlow";
	@Deprecated
	public static final String ANALYZE_PROJECT_DATA_FLOW = "analyzeProjectDataFlow";
	public static final String AFTER_LOAD_PROJECT_DATA_FLOW = "afterLoadProjectDataFlow";

	public static final String SETUP_PROJECT_OBJECT_SWAP_TABLE_FLOW = "setupProjectObjectSwapTableFlow";
	@Deprecated
	public static final String BUILD_PROJECT_OBJECT_INDEXES_FLOW = "buildProjectObjectIndexesFlow";
	@Deprecated
	public static final String ANALYZE_PROJECT_OBJECT_FLOW = "analyzeProjectObjectFlow";

	public static final String SETUP_MONITORING_LOCATION_SWAP_TABLE_FLOW = "setupMonitoringLocationSwapTableFlow";
	@Deprecated
	public static final String BUILD_MONITORING_LOCATION_INDEXES_FLOW = "buildMonitoringLocationIndexesFlow";
	@Deprecated
	public static final String ANALYZE_MONITORING_LOCATION_FLOW = "analyzeMonitoringLocationFlow";
	public static final String AFTER_LOAD_MONITORING_LOCATION_FLOW = "afterLoadMonitoringLocationFlow";

	public static final String SETUP_BIOLOGICAL_HABITAT_METRIC_SWAP_TABLE_FLOW = "setupBiologicalHabitatMetricSwapTableFlow";
	@Deprecated
	public static final String BUILD_BIOLOGICAL_HABITAT_METRIC_INDEXES_FLOW = "buildBiologicalHabitatMetricIndexesFlow";
	@Deprecated
	public static final String ANALYZE_BIOLOGICAL_HABITAT_METRIC_FLOW = "analyzeBiologicalHabitatMetricFlow";
	public static final String AFTER_LOAD_BIOLOGICAL_HABITAT_METRIC_FLOW = "afterLoadBiologicalHabitatMetricFlow";

	public static final String SETUP_MONITORING_LOCATION_OBJECT_SWAP_TABLE_FLOW = "setupMonitoringLocationObjectSwapTableFlow";
	@Deprecated
	public static final String BUILD_MONITORING_LOCATION_OBJECT_INDEXES_FLOW = "buildMonitoringLocationObjectIndexesFlow";
	@Deprecated
	public static final String ANALYZE_MONITORING_LOCATION_OBJECT_FLOW = "analyzeMonitoringLocationObjectFlow";

	public static final String SETUP_ACTIVITY_SWAP_TABLE_FLOW = "setupActivitySwapTableFlow";
	@Deprecated
	public static final String BUILD_ACTIVITY_INDEXES_FLOW = "buildActivityIndexesFlow";
	@Deprecated
	public static final String ANALYZE_ACTIVITY_FLOW = "analyzeActivityFlow";
	public static final String AFTER_LOAD_ACTIVITY_FLOW = "afterLoadActivityFlow";

	public static final String SETUP_ACTIVITY_OBJECT_SWAP_TABLE_FLOW = "setupActivityObjectSwapTableFlow";
	@Deprecated
	public static final String BUILD_ACTIVITY_OBJECT_INDEXES_FLOW = "buildActivityObjectIndexesFlow";
	@Deprecated
	public static final String ANALYZE_ACTIVITY_OBJECT_FLOW = "analyzeActivityObjectFlow";

	public static final String SETUP_ACTIVITY_METRIC_SWAP_TABLE_FLOW = "setupActivityMetricSwapTableFlow";
	@Deprecated
	public static final String BUILD_ACTIVITY_METRIC_INDEXES_FLOW = "buildActivityMetricIndexesFlow";
	@Deprecated
	public static final String ANALYZE_ACTIVITY_METRIC_FLOW = "analyzeActivityMetricFlow";
	public static final String AFTER_LOAD_ACTIVITY_METRIC_FLOW = "afterLoadActivityMetricFlow";

	public static final String SETUP_RESULT_SWAP_TABLE_FLOW = "setupResultSwapTableFlow";
	@Deprecated
	public static final String BUILD_RESULT_INDEXES_FLOW = "buildResultIndexesFlow";
	@Deprecated
	public static final String ANALYZE_RESULT_FLOW = "analyzeResultFlow";
	public static final String AFTER_LOAD_RESULT_FLOW = "afterLoadResultFlow";

	public static final String SETUP_RESULT_OBJECT_SWAP_TABLE_FLOW = "setupResultObjectSwapTableFlow";
	@Deprecated
	public static final String BUILD_RESULT_OBJECT_INDEXES_FLOW = "buildResultObjectIndexesFlow";
	@Deprecated
	public static final String ANALYZE_RESULT_OBJECT_FLOW = "analyzeResultObjectFlow";

	public static final String SETUP_RES_DETECT_QNT_LIMIT_SWAP_TABLE_FLOW = "setupResDetectQntLimitSwapTableFlow";
	@Deprecated
	public static final String BUILD_RES_DETECT_QNT_LIMIT_INDEXES_FLOW = "buildResDetectQntLimitIndexesFlow";
	@Deprecated
	public static final String ANALYZE_RES_DETECT_QNT_LIMIT_FLOW = "analyzeResDetectQntLimitFlow";
	public static final String AFTER_LOAD_RES_DETECT_QNT_LIMIT_FLOW = "afterLoadResDetectQntLimitFlow";

	public static final String SETUP_PROJECT_ML_WEIGHTING_SWAP_TABLE_FLOW = "setupProjectMLWeightingSwapTableFlow";
	@Deprecated
	public static final String BUILD_PROJECT_ML_WEIGHTING_INDEXES_FLOW = "buildProjectMLWeightingIndexesFlow";
	@Deprecated
	public static final String ANALYZE_PROJECT_ML_WEIGHTING_FLOW = "analyzeProjectMLWeightingFlow";
	public static final String AFTER_LOAD_PROJECT_ML_WEIGHTING_FLOW = "afterLoadProjectMLWeightingFlow";

	public static final String CREATE_SUMMARIES_FLOW = "createSummariesFlow";
	public static final String CREATE_LOOKUP_CODES_FLOW = "createLookupCodesFlow";
	public static final String CREATE_DATABASE_FINALIZE_FLOW = "databaseFinalizeFlow";

	private EtlConstantUtils() {}

}
