package gov.acwi.wqp.etl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {

	@Value("${WQP_SCHEMA_NAME}")
	private String wqpSchemaName;
	@Value("${GEO_SCHEMA_NAME}")
	private String geoSchemaName;
	@Value("${ETL_DATA_SOURCE_ID}")
	private Integer etlDataSourceId;
	@Value("${ETL_DATA_SOURCE}")
	private String etlDataSource;
	@Value("${QWPORTAL_SUMMARY_ETL:false}")
	private Boolean qwportalSummary;
	@Value("${NWIS_OR_EPA}")
	private String nwisOrEpa;
	public String getWqpSchemaName() {
		return wqpSchemaName;
	}
	public void setWqpSchemaName(String wqpSchemaName) {
		this.wqpSchemaName = wqpSchemaName;
	}
	public String getGeoSchemaName() {
		return geoSchemaName;
	}
	public void setGeoSchemaName(String geoSchemaName) {
		this.geoSchemaName = geoSchemaName;
	}
	public Integer getEtlDataSourceId() {
		return etlDataSourceId;
	}
	public void setEtlDataSourceId(Integer etlDaataSourceId) {
		this.etlDataSourceId = etlDaataSourceId;
	}
	public String getEtlDataSource() {
		return etlDataSource;
	}
	public void setEtlDataSource(String etlDataSource) {
		this.etlDataSource = etlDataSource;
	}
	public void setQwportalSummary(Boolean qwportalSummary) {
		this.qwportalSummary = qwportalSummary;
	}
	public boolean isQwportalSummary() {
		return qwportalSummary;
	}
	public String getNwisOrEpa() {
		return nwisOrEpa;
	}
	public void setNwisOrEpa(String nwisOrEpa) {
		this.nwisOrEpa = nwisOrEpa;
	}

}
