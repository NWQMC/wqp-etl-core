package gov.acwi.wqp.etl.partition;

import java.time.LocalDate;

/**
 * A single partition table of a parent partitioned by date range.
 */
public interface RangePartition<T> {

	/**
	 * The name of the table for this partition.
	 * @return
	 */
	String getTableName();

	void setRangeStart(T rangeStart);

	T getRangeStart();

	void setRangeEnd(T rangeEnd);

	T getRangeEnd();


	String getSqlFormatRangeStart();


	String getSqlFormatRangeEnd();
}
