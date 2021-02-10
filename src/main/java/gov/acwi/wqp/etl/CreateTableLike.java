package gov.acwi.wqp.etl;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;

/**
 * Creates a table 'like' another table.  It is an error if the table already exists.
 */
public class CreateTableLike extends SqlTemplateTasklet {

	private final String schemaName;
	private final String tableName;
	private final String likeTableName;

	public CreateTableLike(final JdbcTemplate jdbcTemplate, final String schemaName,
	                       final String tableName, final String likeTableName) {

		super(jdbcTemplate);

		this.schemaName = schemaName;
		this.tableName = tableName;
		this.likeTableName = likeTableName;
	}

	@Override
	public String getTemplateClassPath() {
		return "gov/acwi/wqp/etl/createTableLike.sql";
	}

	@Override
	public String getSqlString() {

		HashMap<String, String> params = new HashMap();
		params.put("schema_name", schemaName);
		params.put("table_name", tableName);
		params.put("like_table_name", likeTableName);

		return super.getSqlString(params);
	}

}
