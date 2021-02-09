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
	@Value("#{T(java.lang.Integer).parseInt('${DB_OPERATION_CONCURRENCY:3}')}")
	private Integer dbOperationConcurrency;

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

	/**
	 * Fetch how many concurrent operations are allowed to be submitted to the db concurrently.
	 * @return
	 */
	public Integer getDbOperationConcurrency() { return dbOperationConcurrency; }

	/**
	 * Set how many concurrent operations are allowed to be submitted to the db concurrently.
	 * Defaults to 3 unless specified by the DB_OPERATION_CONCURRENCY env var.
	 * 2 or 3 is reasonable, but 4 may use all db threads if multiple ETL jobs are running.
	 * @return
	 */
	public void setDbOperationConcurrency(final Integer indexCreationConcurrency) { this.dbOperationConcurrency = indexCreationConcurrency; }

}
