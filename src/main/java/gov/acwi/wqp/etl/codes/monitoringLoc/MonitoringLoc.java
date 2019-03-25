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

	@Override
	public String toString() {
		StringBuilder rtn = new StringBuilder("Activity [");
		rtn.append("dataSourceId=").append(dataSourceId == null ? "{null}" : dataSourceId.toString());
		rtn.append(", codeValue=").append(codeValue == null ? "{null}" : codeValue.toString());
		rtn.append(", description=").append(description == null ? "{null}" : description.toString());
		rtn.append(", organization=").append(organization == null ? "{null}" : organization.toString());
		rtn.append(", text=").append(text == null ? "{null}" : text.toString());
		return rtn.append("]").toString();
	}
}
