package gov.acwi.wqp.etl.codes.monitoringLoc;

import gov.acwi.wqp.etl.codes.BaseLookupCode;

public class MonitoringLoc extends BaseLookupCode {

	public static final String BASE_TABLE_NAME = "monitoring_loc";

	private String organization;
	private String text;

	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
