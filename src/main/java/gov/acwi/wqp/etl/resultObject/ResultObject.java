package gov.acwi.wqp.etl.resultObject;

public class ResultObject {

	public static final String BASE_TABLE_NAME = "result_object";

	private Integer dataSourceId;
	private Integer objectId;
	private String dataSource;
	private String organization;
	private Integer activityId;
	private String activity;
	private Integer resultId;
	private String objectName;
	private String objectType;
	private byte[] objectContent;

	public Integer getDataSourceId() {
		return dataSourceId;
	}
	public void setDataSourceId(Integer dataSourceId) {
		this.dataSourceId = dataSourceId;
	}
	public Integer getObjectId() {
		return objectId;
	}
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public Integer getResultId() {
		return resultId;
	}
	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public byte[] getObjectContent() {
		return objectContent;
	}
	public void setObjectContent(byte[] objectContent) {
		this.objectContent = objectContent;
	}
	public static String getBaseTableName() {
		return BASE_TABLE_NAME;
	}
}
