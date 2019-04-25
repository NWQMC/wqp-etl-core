package gov.acwi.wqp.etl.projectMLWeighting;

import org.postgis.PGgeometry;

public class ProjectMLWeighting {

	public static final String BASE_TABLE_NAME = "prj_ml_weighting";

	private Integer dataSourceId;
	private String dataSource;
	private Integer stationId;
	private String data_source;
	private String site_id;
	private String organization;
	private String organization_name;
	private String site_type;
	private String huc;
	private String governmental_unit_code;
	private PGgeometry geom;
	private String project_identifier;
	private String measure_value;
	private String unit_code;
	private String statistical_stratum;
	private String location_category;
	private String location_status;
	private String ref_location_type_code;
	private String ref_location_start_date;
	private String ref_location_end_date;
	private String resource_title;
	private String resource_creator;
	private String resource_subject;
	private String resource_publisher;
	private String resource_date;
	private String resource_identifier;
	private String comment_text;

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
	public String getData_source() {
		return data_source;
	}
	public void setData_source(String data_source) {
		this.data_source = data_source;
	}
	public String getSite_id() {
		return site_id;
	}
	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getOrganization_name() {
		return organization_name;
	}
	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}
	public String getSite_type() {
		return site_type;
	}
	public void setSite_type(String site_type) {
		this.site_type = site_type;
	}
	public String getHuc() {
		return huc;
	}
	public void setHuc(String huc) {
		this.huc = huc;
	}
	public String getGovernmental_unit_code() {
		return governmental_unit_code;
	}
	public void setGovernmental_unit_code(String governmental_unit_code) {
		this.governmental_unit_code = governmental_unit_code;
	}
	public PGgeometry getGeom() {
		return geom;
	}
	public void setGeom(PGgeometry geom) {
		this.geom = geom;
	}
	public String getProject_identifier() {
		return project_identifier;
	}
	public void setProject_identifier(String project_identifier) {
		this.project_identifier = project_identifier;
	}
	public String getMeasure_value() {
		return measure_value;
	}
	public void setMeasure_value(String measure_value) {
		this.measure_value = measure_value;
	}
	public String getUnit_code() {
		return unit_code;
	}
	public void setUnit_code(String unit_code) {
		this.unit_code = unit_code;
	}
	public String getStatistical_stratum() {
		return statistical_stratum;
	}
	public void setStatistical_stratum(String statistical_stratum) {
		this.statistical_stratum = statistical_stratum;
	}
	public String getLocation_category() {
		return location_category;
	}
	public void setLocation_category(String location_category) {
		this.location_category = location_category;
	}
	public String getLocation_status() {
		return location_status;
	}
	public void setLocation_status(String location_status) {
		this.location_status = location_status;
	}
	public String getRef_location_type_code() {
		return ref_location_type_code;
	}
	public void setRef_location_type_code(String ref_location_type_code) {
		this.ref_location_type_code = ref_location_type_code;
	}
	public String getRef_location_start_date() {
		return ref_location_start_date;
	}
	public void setRef_location_start_date(String ref_location_start_date) {
		this.ref_location_start_date = ref_location_start_date;
	}
	public String getRef_location_end_date() {
		return ref_location_end_date;
	}
	public void setRef_location_end_date(String ref_location_end_date) {
		this.ref_location_end_date = ref_location_end_date;
	}
	public String getResource_title() {
		return resource_title;
	}
	public void setResource_title(String resource_title) {
		this.resource_title = resource_title;
	}
	public String getResource_creator() {
		return resource_creator;
	}
	public void setResource_creator(String resource_creator) {
		this.resource_creator = resource_creator;
	}
	public String getResource_subject() {
		return resource_subject;
	}
	public void setResource_subject(String resource_subject) {
		this.resource_subject = resource_subject;
	}
	public String getResource_publisher() {
		return resource_publisher;
	}
	public void setResource_publisher(String resource_publisher) {
		this.resource_publisher = resource_publisher;
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
	public String getComment_text() {
		return comment_text;
	}
	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}
}
