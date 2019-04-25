package gov.acwi.wqp.etl.activityMetric;

import java.time.LocalDate;

import org.postgis.PGgeometry;

public class ActivityMetric {

	public static final String BASE_TABLE_NAME = "act_metric";

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
	private String typeIdentifier;
	private String identifierContext;
	private String typeName;
	private String resourceTitle;
	private String resourceCreator;
	private String resourceSubject;
	private String resourcePublisher;
	private String resourceDate;
	private String resourceIdentifier;
	private String typeScale;
	private String formulaDescription;
	private String measureValue;
	private String unitCode;
	private String score;
	private String commentText;
	private String indexIdentifier;

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
	public String getTypeIdentifier() {
		return typeIdentifier;
	}
	public void setTypeIdentifier(String typeIdentifier) {
		this.typeIdentifier = typeIdentifier;
	}
	public String getIdentifierContext() {
		return identifierContext;
	}
	public void setIdentifierContext(String identifierContext) {
		this.identifierContext = identifierContext;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
	public String getTypeScale() {
		return typeScale;
	}
	public void setTypeScale(String typeScale) {
		this.typeScale = typeScale;
	}
	public String getFormulaDescription() {
		return formulaDescription;
	}
	public void setFormulaDescription(String formulaDescription) {
		this.formulaDescription = formulaDescription;
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
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public String getIndexIdentifier() {
		return indexIdentifier;
	}
	public void setIndexIdentifier(String indexIdentifier) {
		this.indexIdentifier = indexIdentifier;
	}
}
