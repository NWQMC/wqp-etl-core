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
	public static final String BUILD_ORG_DATA_INDEXES_FLOW = "buildOrgDataIndexesFlow";

	public static final String SETUP_PROJECT_DATA_SWAP_TABLE_FLOW = "setupProjectDataSwapTableFlow";
	public static final String BUILD_PROJECT_DATA_INDEXES_FLOW = "buildProjectDataIndexesFlow";

	public static final String SETUP_MONITORING_LOCATION_SWAP_TABLE_FLOW = "setupMonitoringLocationSwapTableFlow";
	public static final String BUILD_MONITORING_LOCATION_INDEXES_FLOW = "buildMonitoringLocationIndexesFlow";

	public static final String SETUP_ACTIVITY_SWAP_TABLE_FLOW = "setupActivitySwapTableFlow";
	public static final String BUILD_ACTIVITY_INDEXES_FLOW = "buildActivityIndexesFlow";

	public static final String SETUP_RESULT_SWAP_TABLE_FLOW = "setupResultSwapTableFlow";
	public static final String BUILD_RESULT_INDEXES_FLOW = "buildResultIndexesFlow";

	public static final String SETUP_RES_DETECT_QNT_LIMIT_SWAP_TABLE_FLOW = "setupResDetectQntLimitSwapTableFlow";
	public static final String BUILD_RES_DETECT_QNT_LIMIT_INDEXES_FLOW = "buildResDetectQntLimitIndexesFlow";

	public static final String CREATE_SUMMARIES_FLOW = "createSummariesFlow";
	public static final String CREATE_LOOKUP_CODES_FLOW = "createLookupCodesFlow";
	public static final String CREATE_DATABASE_FINALIZE_FLOW = "databaseFinalizeFlow";

	private EtlConstantUtils() {}

}
