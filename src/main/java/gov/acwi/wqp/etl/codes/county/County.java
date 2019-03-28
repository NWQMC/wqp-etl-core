package gov.acwi.wqp.etl.codes.county;

import gov.acwi.wqp.etl.codes.BaseLookupCode;

public class County extends BaseLookupCode {

	public static final String BASE_TABLE_NAME = "county";

	private String countryCode;
	private String stateCode;
	private String descriptionWithoutCountryState;

	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getDescriptionWithoutCountryState() {
		return descriptionWithoutCountryState;
	}
	public void setDescriptionWithoutCountryState(String descriptionWithoutCountryState) {
		this.descriptionWithoutCountryState = descriptionWithoutCountryState;
	}
}
