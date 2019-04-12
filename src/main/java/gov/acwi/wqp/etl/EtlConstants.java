package gov.acwi.wqp.etl;

public final class EtlConstants {

	private EtlConstants() {}

	public static final String JOB_ID = "jobId";
	//TODO make configurable
	public static final String WQP_SCHEMA_NAME = "wqp";
	//TODO make configurable
	public static final String NWIS_SCHEMA_NAME = "nwis";

	public static final String JOB_PARM_DATA_SOURCE = "wqpDataSource";
	public static final String JOB_PARM_DATA_SOURCE_ID = "wqpDataSourceId";
	public static final String JOB_PARM_SCHEMA = "schemaName";
	public static final String JOB_PARM_GEO_SCHEMA = "geoSchemaName";

}
