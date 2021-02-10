package gov.acwi.wqp.etl.partition;

import gov.acwi.wqp.etl.SqlTemplateTasklet;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;

/**
 * Attaches a partition to a parent table that is partitioned via range.
 */
public class AttachRangePartitionTable extends SqlTemplateTasklet {

	private final String schemaName;
	private final String tableName;
	private final String parentName;
	private final String rangeStart;
	private final String rangeEnd;

	public AttachRangePartitionTable(final JdbcTemplate jdbcTemplate, final String schemaName,
	                                 final String tableName, final String parentName,
	                                 final String rangeStart, final String rangeEnd) {

		super(jdbcTemplate);

		this.schemaName = schemaName;
		this.tableName = tableName;
		this.parentName = parentName;
		this.rangeStart = rangeStart;
		this.rangeEnd = rangeEnd;
	}

	@Override
	public String getTemplateClassPath() {
		return "gov/acwi/wqp/etl/partition/attachRangePartitionTable.sql";
	}

	@Override
	public String getSqlString() {
		HashMap<String, String> params = new HashMap();
		params.put("schema_name", schemaName);
		params.put("table_name", tableName);
		params.put("parent_table", parentName);
		params.put("range_start", rangeStart);
		params.put("range_end", rangeEnd);

		return super.getSqlString(params);
	}

}
