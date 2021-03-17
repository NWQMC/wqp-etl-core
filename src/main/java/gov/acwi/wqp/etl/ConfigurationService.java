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

	//
	//These values all have type conversions and use @Value on a set method
	private LocalDate etlResultPartitionStartDate;
	private LocalDate etlResultPartitionOneYearBreak;
	private LocalDate etlResultPartitionQuarterBreak;
	private LocalDate etlResultPartitionEndDate;
	private LocalDateTime etlRunTime;
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
	 * Set how many concurrent operations are allowed to be submitted to the db concurrently.
	 * Defaults to 3 unless specified by the DB_OPERATION_CONCURRENCY env var.
	 * 2 or 3 is reasonable, but 4 may use all db threads if multiple ETL jobs are running.
	 * @param dbOperationConcurrencyStr An integer as a string , intended to be set by the DB_OPERATION_CONCURRENCY env var.  Null OK, zero is not.
	 */
	@Value("${DB_OPERATION_CONCURRENCY:#{null}}")
	public void setDbOperationConcurrency(String dbOperationConcurrencyStr) {
		if (dbOperationConcurrencyStr != null) {
			try {
				dbOperationConcurrency = Math.max(Integer.parseInt(dbOperationConcurrencyStr), 1);
			} catch (NumberFormatException e) {
				throw new RuntimeException("Unable to parse '" + dbOperationConcurrencyStr + "' as an integer for DB_OPERATION_CONCURRENCY.", e);
			}
		} else {
			dbOperationConcurrency = 3;
		}
	}

	/**
	 * How many concurrent operations are allowed to be submitted to the db concurrently.
	 * Currently only used for result table index building.
	 * @return  non-null and non-zero.
	 */
	public Integer getDbOperationConcurrency() { return dbOperationConcurrency; }

	//
	//Result table partitioning params
	//Any/all of these can be spec'ed as a LocalDate in the format 'YYYY-MM-DD', but generally only the year is significant.
	//See DateRangePartitionStrategy for param details or the Readme.md.

	/**
	 * Sets a start date for result table partitioning.  See the README.md for more detailed info.
	 * @param etlResultPartitionStartDateStr String, parsable as a LocalDate or null.  Intended to be set via ETL_RESULT_PARTITION_START_DATE env var.
	 */
	@Value("${ETL_RESULT_PARTITION_START_DATE:#{null}}")
	public void setEtlResultPartitionStartDate(String etlResultPartitionStartDateStr) {
		if (etlResultPartitionStartDateStr != null) {
			try {
				etlResultPartitionStartDate = LocalDate.parse(etlResultPartitionStartDateStr);
			} catch (RuntimeException e) {
				throw new RuntimeException("Unable to parse '" + etlResultPartitionStartDateStr + "' as a LocalDate for ETL_RESULT_PARTITION_START_DATE.", e);
			}
		} else {
			etlResultPartitionStartDate = null;
		}
	}

	/**
	 * The start date for result table partitioning.  See the README.md for more detailed info.
	 * @return  The date or null if not configured.
	 */
	public LocalDate getEtlResultPartitionStartDate() { return etlResultPartitionStartDate; }

	/**
	 * Sets a date to end five year partitions and begin one year partitions for the result table.
	 * See the README.md for more detailed info.
	 * @param etlResultPartitionOneYearBreakStr String, parsable as a LocalDate or null.  Intended to be set via ETL_RESULT_PARTITION_ONE_YEAR_BREAK env var.
	 */
	@Value("${ETL_RESULT_PARTITION_ONE_YEAR_BREAK:#{null}}")
	public void setEtlResultPartitionOneYearBreak(String etlResultPartitionOneYearBreakStr) {
		if (etlResultPartitionOneYearBreakStr != null) {
			try {
				etlResultPartitionOneYearBreak = LocalDate.parse(etlResultPartitionOneYearBreakStr);
			} catch (RuntimeException e) {
				throw new RuntimeException("Unable to parse '" + etlResultPartitionOneYearBreakStr + "' as a LocalDate for ETL_RESULT_PARTITION_ONE_YEAR_BREAK.", e);
			}
		} else {
			etlResultPartitionOneYearBreak = null;
		}
	}

	/**
	 * A date to end five year partitions and begin one year partitions for the result table.
	 * @return  The date or null if not configured.
	 */
	public LocalDate getEtlResultPartitionOneYearBreak() {
		return etlResultPartitionOneYearBreak;
	}

	/**
	 * Sets a date to end one year partitions and begin quarter year partitions for the result table.
	 * See the README.md for more detailed info.
	 * @param etlResultPartitionQuarterBreakStr String, parsable as a LocalDate or null.  Intended to be set via ETL_RESULT_PARTITION_QUARTER_BREAK env var.
	 */
	@Value("${ETL_RESULT_PARTITION_QUARTER_BREAK:#{null}}")
	public void setEtlResultPartitionQuarterBreak(String etlResultPartitionQuarterBreakStr) {
		if (etlResultPartitionQuarterBreakStr != null) {
			try {
				etlResultPartitionQuarterBreak = LocalDate.parse(etlResultPartitionQuarterBreakStr);
			} catch (RuntimeException e) {
				throw new RuntimeException("Unable to parse '" + etlResultPartitionQuarterBreakStr + "' as a LocalDate for ETL_RESULT_PARTITION_QUARTER_BREAK.", e);
			}
		} else {
			etlResultPartitionQuarterBreak = null;
		}
	}

	/**
	 * A date to end one year partitions and begin quarter year partitions for the result table.
	 * @return  The date or null if not configured.
	 */
	public LocalDate getEtlResultPartitionQuarterBreak() {
		return etlResultPartitionQuarterBreak;
	}

	/**
	 * Sets an end date for result table partitioning.  See the README.md for more detailed info.
	 * Intended to be configured via ETL_RESULT_PARTITION_END_DATE env var.  Null allowed.
	 * @param etlResultPartitionEndDateStr String, parsable as a LocalDate or null.  Intended to be set via ETL_RESULT_PARTITION_END_DATE env var.
	 */
	@Value("${ETL_RESULT_PARTITION_END_DATE:#{null}}")
	public void setEtlResultPartitionEndDate(String etlResultPartitionEndDateStr) {
		if (etlResultPartitionEndDateStr != null) {
			try {
				etlResultPartitionEndDate = LocalDate.parse(etlResultPartitionEndDateStr);
			} catch (RuntimeException e) {
				throw new RuntimeException("Unable to parse '" + etlResultPartitionEndDateStr + "' as a LocalDate for ETL_RESULT_PARTITION_END_DATE.", e);
			}
		} else {
			etlResultPartitionEndDate = null;
		}
	}

	/**
	 * End date for result table partitioning.  See the README.md for more detailed info.
	 * @return  The date or null if not configured.
	 */
	public LocalDate getEtlResultPartitionEndDate() {
		return etlResultPartitionEndDate;
	}

	/**
	 * Set a runtime for use by ResultPartitionStrategy to time generate unique partition table names.
	 * This is a testing-only parameter, since only in testing would we expect multiple runs w/in the same hour.  Defaults to now.
	 * @param etlRunTimeStr A string, parsable as a LocalDateTime or null, to be used as the run time.  Intended to be configured via ETL_RUN_TIME
	 */
	@Value("${ETL_RUN_TIME:#{null}}")
	public void setEtlRunTime(String etlRunTimeStr) {
		if (etlRunTimeStr != null) {
			try {
				etlRunTime = LocalDateTime.parse(etlRunTimeStr);
			} catch (RuntimeException e) {
				throw new RuntimeException("Unable to parse '" + etlRunTimeStr + "' as a LocalDateTime for ETL_RUN_TIME.", e);
			}
		} else {
			etlRunTime = LocalDateTime.now();
		}
	}

	/**
	 * A runtime for use by ResultPartitionStrategy to time generate unique partition table names.
	 * @return A non-null LocalDateTime.
	 */
	public LocalDateTime getEtlRunTime() {
		return etlRunTime;
	}
}
