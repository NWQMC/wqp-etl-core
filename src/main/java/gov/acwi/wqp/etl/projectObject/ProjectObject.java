package gov.acwi.wqp.etl.projectObject;

public class ProjectObject {

	public static final String BASE_TABLE_NAME = "project_object";

	private Integer dataSourceId;
	private Integer objectId;
	private String dataSource;
	private String organization;
	private String projectIdentifier;
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
	public String getProjectIdentifier() {
		return projectIdentifier;
	}
	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
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
