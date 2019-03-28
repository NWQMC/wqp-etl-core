package gov.acwi.wqp.etl.codes;

public abstract class BaseLookupCode {

	protected Integer dataSourceId;
	protected String codeValue;
	protected String description;

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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder rtn = new StringBuilder("BaseLookupCode [");
		rtn.append("dataSourceId=").append(dataSourceId == null ? "{null}" : dataSourceId.toString());
		rtn.append(", codeValue=").append(codeValue == null ? "{null}" : codeValue.toString());
		rtn.append(", description=").append(description == null ? "{null}" : description.toString());
		return rtn.append("]").toString();
	}
}
