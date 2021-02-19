package gov.acwi.wqp.etl.index;

import gov.acwi.wqp.etl.SqlTemplateTasklet;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;

/**
 * Creates basic index if it does not already exist
 */
public class CreateBasicIndex extends SqlTemplateTasklet {

	private final String schemaName;
	private final String tableName;
	private final String indexName;
	private final String columnName;
	private final int fillFactor;

	public CreateBasicIndex(final JdbcTemplate jdbcTemplate, final String schemaName,
	                        final String tableName, final String indexName, String columnName) {

		super(jdbcTemplate);

		this.schemaName = schemaName;
		this.tableName = tableName;
		this.indexName = indexName;
		this.columnName = columnName;
		this.fillFactor = 100;
	}

	public CreateBasicIndex(final JdbcTemplate jdbcTemplate, final String schemaName,
	                        final String tableName, final String indexName, String columnName, int fillFactor) {

		super(jdbcTemplate);

		this.schemaName = schemaName;
		this.tableName = tableName;
		this.indexName = indexName;
		this.columnName = columnName;
		this.fillFactor = fillFactor;
	}

	@Override
	public String getTemplateClassPath() {
		return "gov/acwi/wqp/etl/index/createBasicIndex.sql";
	}

	@Override
	public String getSqlString() {

		HashMap<String, String> params = new HashMap();
		params.put("schema_name", schemaName);
		params.put("table_name", tableName);
		params.put("index_name", indexName);
		params.put("column_name", columnName);
		params.put("fill_factor", Integer.toString(fillFactor));

		return super.getSqlString(params);
	}

}
