package gov.acwi.wqp.etl.biologicalHabitatMetric;

import org.postgis.PGgeometry;

public class BiologicalHabitatMetric {

	public static final String BASE_TABLE_NAME = "bio_hab_metric";

	private Integer dataSourceId;
	private String dataSource;
	private Integer stationId;
	private String siteId;
	private String organization;
	private String siteType;
	private String huc;
	private String governmentalUnitCode;
	private PGgeometry geom;
	private String indexIdentifier;
	private String indexTypeIdentifier;
	private String indexTypeContext;
	private String indexTypeName;
	private String resourceTitleName;
	private String resourceCreatorName;
	private String resourceSubjectText;
	private String resourcePublisherName;
	private String resourceDate;
	private String resourceIdentifier;
	private String indexTypeScaleText;
	private String indexScoreNumeric;
	private String indexQualifierCode;
	private String indexComment;
	private String indexCalculatedDate;

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
	public String getIndexIdentifier() {
		return indexIdentifier;
	}
	public void setIndexIdentifier(String indexIdentifier) {
		this.indexIdentifier = indexIdentifier;
	}
	public String getIndexTypeIdentifier() {
		return indexTypeIdentifier;
	}
	public void setIndexTypeIdentifier(String indexTypeIdentifier) {
		this.indexTypeIdentifier = indexTypeIdentifier;
	}
	public String getIndexTypeContext() {
		return indexTypeContext;
	}
	public void setIndexTypeContext(String indexTypeContext) {
		this.indexTypeContext = indexTypeContext;
	}
	public String getIndexTypeName() {
		return indexTypeName;
	}
	public void setIndexTypeName(String indexTypeName) {
		this.indexTypeName = indexTypeName;
	}
	public String getResourceTitleName() {
		return resourceTitleName;
	}
	public void setResourceTitleName(String resourceTitleName) {
		this.resourceTitleName = resourceTitleName;
	}
	public String getResourceCreatorName() {
		return resourceCreatorName;
	}
	public void setResourceCreatorName(String resourceCreatorName) {
		this.resourceCreatorName = resourceCreatorName;
	}
	public String getResourceSubjectText() {
		return resourceSubjectText;
	}
	public void setResourceSubjectText(String resourceSubjectText) {
		this.resourceSubjectText = resourceSubjectText;
	}
	public String getResourcePublisherName() {
		return resourcePublisherName;
	}
	public void setResourcePublisherName(String resourcePublisherName) {
		this.resourcePublisherName = resourcePublisherName;
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
	public String getIndexTypeScaleText() {
		return indexTypeScaleText;
	}
	public void setIndexTypeScaleText(String indexTypeScaleText) {
		this.indexTypeScaleText = indexTypeScaleText;
	}
	public String getIndexScoreNumeric() {
		return indexScoreNumeric;
	}
	public void setIndexScoreNumeric(String indexScoreNumeric) {
		this.indexScoreNumeric = indexScoreNumeric;
	}
	public String getIndexQualifierCode() {
		return indexQualifierCode;
	}
	public void setIndexQualifierCode(String indexQualifierCode) {
		this.indexQualifierCode = indexQualifierCode;
	}
	public String getIndexComment() {
		return indexComment;
	}
	public void setIndexComment(String indexComment) {
		this.indexComment = indexComment;
	}
	public String getIndexCalculatedDate() {
		return indexCalculatedDate;
	}
	public void setIndexCalculatedDate(String indexCalculatedDate) {
		this.indexCalculatedDate = indexCalculatedDate;
	}
}
