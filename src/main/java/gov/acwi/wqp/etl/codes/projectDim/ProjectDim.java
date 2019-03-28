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

	@Override
	public String toString() {
		StringBuilder rtn = new StringBuilder("BaseLookupCode [");
		rtn.append("dataSourceId=").append(dataSourceId == null ? "{null}" : dataSourceId.toString());
		rtn.append(", codeValue=").append(codeValue == null ? "{null}" : codeValue.toString());
		rtn.append(", projectDimValue=").append(projectDimValue == null ? "{null}" : projectDimValue.toString());
		return rtn.append("]").toString();
	}
}
