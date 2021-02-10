package gov.acwi.wqp.etl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

	//Result table partitioning params
	//Any/all of these can be spec'ed as a LocalDate in the format 'YYYY-MM-DD', but generally only the year is significant.
	//See DateRangePartitionStrategy for param details or the Readme.md.

	//Note:  The complex @Value Spel expressions basically say that if the value is unspecified, its null.  If its
	//specified, parse the value into a LocalDate or LocalDateTime.
	@Value("#{'${ETL_RESULT_PARTITION_START_DATE:null}' == 'null' ? null : T(java.time.LocalDate).parse('${ETL_RESULT_PARTITION_START_DATE:null}')}")
	private LocalDate etlResultPartitionStartDate;

	@Value("#{'${ETL_RESULT_PARTITION_ONE_YEAR_BREAK:null}' == 'null' ? null : T(java.time.LocalDate).parse('${ETL_RESULT_PARTITION_ONE_YEAR_BREAK:null}')}")
	private LocalDate etlResultPartitionOneYearBreak;

	@Value("#{'${ETL_RESULT_PARTITION_QUARTER_BREAK:null}' == 'null' ? null : T(java.time.LocalDate).parse('${ETL_RESULT_PARTITION_QUARTER_BREAK:null}')}")
	private LocalDate etlResultPartitionQuarterBreak;

	@Value("#{'${ETL_RESULT_PARTITION_END_DATE:null}' == 'null' ? null : T(java.time.LocalDate).parse('${ETL_RESULT_PARTITION_END_DATE:null}')}")
	private LocalDate etlResultPartitionEndDate;

	//
	// Testing-only parameter.
	// Used by ResultPartitionStrategy to spec a time used in unique partition table names.  Defaults to now.
	@Value("#{'${ETL_RUN_TIME:null}' == 'null' ? null : T(java.time.LocalDateTime).parse('${ETL_RUN_TIME:null}')}")
	private LocalDateTime etlRunTime;

	public LocalDate getEtlResultPartitionStartDate() { return etlResultPartitionStartDate; }

	public LocalDate getEtlResultPartitionOneYearBreak() {
		return etlResultPartitionOneYearBreak;
	}

	public LocalDate getEtlResultPartitionQuarterBreak() {
		return etlResultPartitionQuarterBreak;
	}

	public LocalDate getEtlResultPartitionEndDate() {
		return etlResultPartitionEndDate;
	}

	public LocalDateTime getEtlRunTime() {
		if (etlRunTime == null) {
			etlRunTime = LocalDateTime.now();
		}
		return etlRunTime;
	}
}
