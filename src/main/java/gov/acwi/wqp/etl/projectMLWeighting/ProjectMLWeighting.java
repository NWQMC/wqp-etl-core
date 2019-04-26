package gov.acwi.wqp.etl.projectMLWeighting;

import org.postgis.PGgeometry;

public class ProjectMLWeighting {

	public static final String BASE_TABLE_NAME = "prj_ml_weighting";

	private Integer dataSourceId;
	private String dataSource;
	private Integer stationId;
	private String siteId;
	private String organization;
	private String organizationName;
	private String siteType;
	private String huc;
	private String governmentalUnitCode;
	private PGgeometry geom;
	private String projectIdentifier;
	private String measureValue;
	private String unitCode;
	private String statisticalStratum;
	private String locationCategory;
	private String locationStatus;
	private String refLocationTypeCode;
	private String refLocationStartDate;
	private String refLocationEndDate;
	private String resourceTitle;
	private String resourceCreator;
	private String resourceSubject;
	private String resourcePublisher;
	private String resourceDate;
	private String resourceIdentifier;
	private String commentText;

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
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
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
	public String getProjectIdentifier() {
		return projectIdentifier;
	}
	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}
	public String getMeasureValue() {
		return measureValue;
	}
	public void setMeasureValue(String measureValue) {
		this.measureValue = measureValue;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getStatisticalStratum() {
		return statisticalStratum;
	}
	public void setStatisticalStratum(String statisticalStratum) {
		this.statisticalStratum = statisticalStratum;
	}
	public String getLocationCategory() {
		return locationCategory;
	}
	public void setLocationCategory(String locationCategory) {
		this.locationCategory = locationCategory;
	}
	public String getLocationStatus() {
		return locationStatus;
	}
	public void setLocationStatus(String locationStatus) {
		this.locationStatus = locationStatus;
	}
	public String getRefLocationTypeCode() {
		return refLocationTypeCode;
	}
	public void setRefLocationTypeCode(String refLocationTypeCode) {
		this.refLocationTypeCode = refLocationTypeCode;
	}
	public String getRefLocationStartDate() {
		return refLocationStartDate;
	}
	public void setRefLocationStartDate(String refLocationStartDate) {
		this.refLocationStartDate = refLocationStartDate;
	}
	public String getRefLocationEndDate() {
		return refLocationEndDate;
	}
	public void setRefLocationEndDate(String refLocationEndDate) {
		this.refLocationEndDate = refLocationEndDate;
	}
	public String getResourceTitle() {
		return resourceTitle;
	}
	public void setResourceTitle(String resourceTitle) {
		this.resourceTitle = resourceTitle;
	}
	public String getResourceCreator() {
		return resourceCreator;
	}
	public void setResourceCreator(String resourceCreator) {
		this.resourceCreator = resourceCreator;
	}
	public String getResourceSubject() {
		return resourceSubject;
	}
	public void setResourceSubject(String resourceSubject) {
		this.resourceSubject = resourceSubject;
	}
	public String getResourcePublisher() {
		return resourcePublisher;
	}
	public void setResourcePublisher(String resourcePublisher) {
		this.resourcePublisher = resourcePublisher;
	}
	public String getResourceDate() {
		return resourceDate;
	}
	public void setResourceDate(String resourceDate) {
		this.resourceDate = resourceDate;
	}
	public String getResourceIdentifier() {
		return resourceIdentifier;
	}
	public void setResourceIdentifier(String resourceIdentifier) {
		this.resourceIdentifier = resourceIdentifier;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
}
