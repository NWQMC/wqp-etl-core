package gov.acwi.wqp.etl.codes.projectDim;

public class ProjectDim  {

	public static final String BASE_TABLE_NAME = "project_dim";

	protected Integer dataSourceId;
	protected String codeValue;
	protected String projectDimValue;

	public Integer getDataSourceId() {
		return dataSourceId;
	}
	public void setDataSourceId(Integer dataSourceId) {
		this.dataSourceId = dataSourceId;
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	public String getProjectDimValue() {
		return projectDimValue;
	}
	public void setProjectDimValue(String projectDimValue) {
		this.projectDimValue = projectDimValue;
	}
}
