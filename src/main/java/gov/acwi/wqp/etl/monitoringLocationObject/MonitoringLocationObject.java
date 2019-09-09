package gov.acwi.wqp.etl.monitoringLocationObject;

import gov.acwi.wqp.etl.BlobObject;

public class MonitoringLocationObject extends BlobObject {

	public static final String BASE_TABLE_NAME = "station_object";

	private Integer stationId;
	private String siteId;

	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
}
