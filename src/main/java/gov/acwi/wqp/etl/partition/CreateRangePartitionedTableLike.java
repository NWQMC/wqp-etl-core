package gov.acwi.wqp.etl.partition;

import gov.acwi.wqp.etl.SqlTemplateTasklet;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;

/**
 * Create a new partitioned table that is partitioned by range, like an existing table.
 *
 * Creating tables in this way assumes unique table names are used, possibly with date and hour encoded in the name.
 */
public class CreateRangePartitionedTableLike extends SqlTemplateTasklet {

	private final String schemaName;
	private final String tableName;
	private final String likeTableName;
	private final String partition_column;

	public CreateRangePartitionedTableLike(final JdbcTemplate jdbcTemplate, final String schemaName, final String tableName,
	                                       final String likeTableName, final String partition_column) {

		super(jdbcTemplate);

		this.schemaName = schemaName;
		this.tableName = tableName;
		this.likeTableName = likeTableName;
		this.partition_column = partition_column;
	}

	@Override
	public String getTemplateClassPath() {
		return "gov/acwi/wqp/etl/partition/createRangePartitionedTableLike.sql";
	}

	@Override
	public String getSqlString() {

		HashMap<String, String> params = new HashMap();
		params.put("schema_name", schemaName);
		params.put("table_name", tableName);
		params.put("like_table_name", likeTableName);
		params.put("partition_column", partition_column);

		return super.getSqlString(params);
	}

}
