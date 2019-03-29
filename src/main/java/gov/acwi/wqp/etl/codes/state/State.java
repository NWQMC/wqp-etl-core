package gov.acwi.wqp.etl.codes.state;

public class State {

	public static final String BASE_TABLE_NAME = "state";

	protected Integer dataSourceId;
	protected String codeValue;
	protected String descriptionWithCountry;
	protected String descriptionWithoutCountry;
	protected String countryCode;

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
	public String getDescriptionWithCountry() {
		return descriptionWithCountry;
	}
	public void setDescriptionWithCountry(String description) {
		this.descriptionWithCountry = description;
	}
	public String getDescriptionWithoutCountry() {
		return descriptionWithoutCountry;
	}
	public void setDescriptionWithoutCountry(String descriptionWithoutCountry) {
		this.descriptionWithoutCountry = descriptionWithoutCountry;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
}
