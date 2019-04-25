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
	private String index_identifier;
	private String index_type_identifier;
	private String index_type_context;
	private String index_type_name;
	private String resource_title_name;
	private String resource_creator_name;
	private String resource_subject_text;
	private String resource_publisher_name;
	private String resource_date;
	private String resource_identifier;
	private String index_type_scale_text;
	private String index_score_numeric;
	private String index_qualifier_code;
	private String index_comment;
	private String index_calculated_date;

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
	public String getIndex_identifier() {
		return index_identifier;
	}
	public void setIndex_identifier(String index_identifier) {
		this.index_identifier = index_identifier;
	}
	public String getIndex_type_identifier() {
		return index_type_identifier;
	}
	public void setIndex_type_identifier(String index_type_identifier) {
		this.index_type_identifier = index_type_identifier;
	}
	public String getIndex_type_context() {
		return index_type_context;
	}
	public void setIndex_type_context(String index_type_context) {
		this.index_type_context = index_type_context;
	}
	public String getIndex_type_name() {
		return index_type_name;
	}
	public void setIndex_type_name(String index_type_name) {
		this.index_type_name = index_type_name;
	}
	public String getResource_title_name() {
		return resource_title_name;
	}
	public void setResource_title_name(String resource_title_name) {
		this.resource_title_name = resource_title_name;
	}
	public String getResource_creator_name() {
		return resource_creator_name;
	}
	public void setResource_creator_name(String resource_creator_name) {
		this.resource_creator_name = resource_creator_name;
	}
	public String getResource_subject_text() {
		return resource_subject_text;
	}
	public void setResource_subject_text(String resource_subject_text) {
		this.resource_subject_text = resource_subject_text;
	}
	public String getResource_publisher_name() {
		return resource_publisher_name;
	}
	public void setResource_publisher_name(String resource_publisher_name) {
		this.resource_publisher_name = resource_publisher_name;
	}
	public String getResource_date() {
		return resource_date;
	}
	public void setResource_date(String resource_date) {
		this.resource_date = resource_date;
	}
	public String getResource_identifier() {
		return resource_identifier;
	}
	public void setResource_identifier(String resource_identifier) {
		this.resource_identifier = resource_identifier;
	}
	public String getIndex_type_scale_text() {
		return index_type_scale_text;
	}
	public void setIndex_type_scale_text(String index_type_scale_text) {
		this.index_type_scale_text = index_type_scale_text;
	}
	public String getIndex_score_numeric() {
		return index_score_numeric;
	}
	public void setIndex_score_numeric(String index_score_numeric) {
		this.index_score_numeric = index_score_numeric;
	}
	public String getIndex_qualifier_code() {
		return index_qualifier_code;
	}
	public void setIndex_qualifier_code(String index_qualifier_code) {
		this.index_qualifier_code = index_qualifier_code;
	}
	public String getIndex_comment() {
		return index_comment;
	}
	public void setIndex_comment(String index_comment) {
		this.index_comment = index_comment;
	}
	public String getIndex_calculated_date() {
		return index_calculated_date;
	}
	public void setIndex_calculated_date(String index_calculated_date) {
		this.index_calculated_date = index_calculated_date;
	}
}
