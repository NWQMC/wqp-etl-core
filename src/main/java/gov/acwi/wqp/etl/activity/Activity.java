package gov.acwi.wqp.etl.activity;

import java.time.LocalDate;

import org.postgis.PGgeometry;

public class Activity {

	public static final String BASE_TABLE_NAME = "activity";

	private Integer dataSourceId;
	private String dataSource;
	private Integer stationId;
	private String siteId;
	private LocalDate eventDate;
	private String activity;
	private String sampleMedia;
	private String organization;
	private String siteType;
	private String huc;
	private String governmentalUnitCode;
	private PGgeometry geom;
	private String organizationName;
	private Integer activityId;
	private String activityTypeCode;
	private String activityMediaSubdivName;
	private String activityStartTime;
	private String actStartTimeZone;
	private String activityStopDate;
	private String activityStopTime;
	private String actStopTimeZone;
	private String activityDepth;
	private String activityDepthUnit;
	private String activityDepthRefPoint;
	private String activityUpperDepth;
	private String activityUpperDepthUnit;
	private String activityLowerDepth;
	private String activityLowerDepthUnit;
	private String projectId;
	private String activityConductingOrg;
	private String sampleAqfrName;
	private String hydrologicConditionName;
	private String hydrologicEventName;
	private String projectName;
	private String monitoringLocationName;
	private String activityComment;
	private String sampleCollectMethodId;
	private String sampleCollectMethodCtx;
	private String sampleCollectMethodName;
	private String sampleCollectEquipName;

	public Integer getDataSourceId() {
		return dataSourceId;
	}
	public void setDataSourceId(Integer dataSourceId) {
		this.dataSourceId = dataSourceId;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
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
	public LocalDate getEventDate() {
		return eventDate;
	}
	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getSampleMedia() {
		return sampleMedia;
	}
	public void setSampleMedia(String sampleMedia) {
		this.sampleMedia = sampleMedia;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getSiteType() {
		return siteType;
	}
	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}
	public String getHuc() {
		return huc;
	}
	public void setHuc(String huc) {
		this.huc = huc;
	}
	public String getGovernmentalUnitCode() {
		return governmentalUnitCode;
	}
	public void setGovernmentalUnitCode(String governmentalUnitCode) {
		this.governmentalUnitCode = governmentalUnitCode;
	}
	public PGgeometry getGeom() {
		return geom;
	}
	public void setGeom(PGgeometry geom) {
		this.geom = geom;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public String getActivityTypeCode() {
		return activityTypeCode;
	}
	public void setActivityTypeCode(String activityTypeCode) {
		this.activityTypeCode = activityTypeCode;
	}
	public String getActivityMediaSubdivName() {
		return activityMediaSubdivName;
	}
	public void setActivityMediaSubdivName(String activityMediaSubdivName) {
		this.activityMediaSubdivName = activityMediaSubdivName;
	}
	public String getActivityStartTime() {
		return activityStartTime;
	}
	public void setActivityStartTime(String activityStartTime) {
		this.activityStartTime = activityStartTime;
	}
	public String getActStartTimeZone() {
		return actStartTimeZone;
	}
	public void setActStartTimeZone(String actStartTimeZone) {
		this.actStartTimeZone = actStartTimeZone;
	}
	public String getActivityStopDate() {
		return activityStopDate;
	}
	public void setActivityStopDate(String activityStopDate) {
		this.activityStopDate = activityStopDate;
	}
	public String getActivityStopTime() {
		return activityStopTime;
	}
	public void setActivityStopTime(String activityStopTime) {
		this.activityStopTime = activityStopTime;
	}
	public String getActStopTimeZone() {
		return actStopTimeZone;
	}
	public void setActStopTimeZone(String actStopTimeZone) {
		this.actStopTimeZone = actStopTimeZone;
	}	
	public String getActivityDepth() {
		return activityDepth;
	}
	public void setActivityDepth(String activityDepth) {
		this.activityDepth = activityDepth;
	}
	public String getActivityDepthUnit() {
		return activityDepthUnit;
	}
	public void setActivityDepthUnit(String activityDepthUnit) {
		this.activityDepthUnit = activityDepthUnit;
	}
	public String getActivityDepthRefPoint() {
		return activityDepthRefPoint;
	}
	public void setActivityDepthRefPoint(String activityDepthRefPoint) {
		this.activityDepthRefPoint = activityDepthRefPoint;
	}
	public String getActivityUpperDepth() {
		return activityUpperDepth;
	}
	public void setActivityUpperDepth(String activityUpperDepth) {
		this.activityUpperDepth = activityUpperDepth;
	}
	public String getActivityUpperDepthUnit() {
		return activityUpperDepthUnit;
	}
	public void setActivityUpperDepthUnit(String activityUpperDepthUnit) {
		this.activityUpperDepthUnit = activityUpperDepthUnit;
	}
	public String getActivityLowerDepth() {
		return activityLowerDepth;
	}
	public void setActivityLowerDepth(String activityLowerDepth) {
		this.activityLowerDepth = activityLowerDepth;
	}
	public String getActivityLowerDepthUnit() {
		return activityLowerDepthUnit;
	}
	public void setActivityLowerDepthUnit(String activityLowerDepthUnit) {
		this.activityLowerDepthUnit = activityLowerDepthUnit;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getActivityConductingOrg() {
		return activityConductingOrg;
	}
	public void setActivityConductingOrg(String activityConductingOrg) {
		this.activityConductingOrg = activityConductingOrg;
	}
	public String getActivityComment() {
		return activityComment;
	}
	public void setActivityComment(String activityComment) {
		this.activityComment = activityComment;
	}
	public String getSampleAqfrName() {
		return sampleAqfrName;
	}
	public void setSampleAqfrName(String sampleAqfrName) {
		this.sampleAqfrName = sampleAqfrName;
	}
	public String getHydrologicConditionName() {
		return hydrologicConditionName;
	}
	public void setHydrologicConditionName(String hydrologicConditionName) {
		this.hydrologicConditionName = hydrologicConditionName;
	}
	public String getHydrologicEventName() {
		return hydrologicEventName;
	}
	public void setHydrologicEventName(String hydrologicEventName) {
		this.hydrologicEventName = hydrologicEventName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getMonitoringLocationName() {
		return monitoringLocationName;
	}
	public void setMonitoringLocationName(String monitoringLocationName) {
		this.monitoringLocationName = monitoringLocationName;
	}
	public String getSampleCollectMethodId() {
		return sampleCollectMethodId;
	}
	public void setSampleCollectMethodId(String sampleCollectMethodId) {
		this.sampleCollectMethodId = sampleCollectMethodId;
	}
	public String getSampleCollectMethodCtx() {
		return sampleCollectMethodCtx;
	}
	public void setSampleCollectMethodCtx(String sampleCollectMethodCtx) {
		this.sampleCollectMethodCtx = sampleCollectMethodCtx;
	}
	public String getSampleCollectMethodName() {
		return sampleCollectMethodName;
	}
	public void setSampleCollectMethodName(String sampleCollectMethodName) {
		this.sampleCollectMethodName = sampleCollectMethodName;
	}
	public String getSampleCollectEquipName() {
		return sampleCollectEquipName;
	}
	public void setSampleCollectEquipName(String sampleCollectEquipName) {
		this.sampleCollectEquipName = sampleCollectEquipName;
	}
}
