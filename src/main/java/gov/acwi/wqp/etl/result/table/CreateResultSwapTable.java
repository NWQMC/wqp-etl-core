package gov.acwi.wqp.etl.result.table;

import gov.acwi.wqp.etl.partition.CreateRangePartitionedTableLike;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.result.Result;

/**
 * Creates the result swap table with a 'partition by' date range clause.
 */
@Component
@StepScope
public class CreateResultSwapTable extends CreateRangePartitionedTableLike {

	public CreateResultSwapTable(JdbcTemplate jdbcTemplate, String schemaName,
	                             final String tableName, final String likeTableName, final String partition_column) {

		super(jdbcTemplate, schemaName, tableName, likeTableName, partition_column);
	}

	protected String getBaseTableName() {
		return Result.BASE_TABLE_NAME;
	}
}
