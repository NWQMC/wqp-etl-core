package gov.acwi.wqp.etl;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;

/**
 * Creates a table 'like' another table.  It is an error if the table already exists.
 */
public class DropConstraint extends SqlTemplateTasklet {

	private final String schemaName;
	private final String tableName;
	private final String constraintName;

	public DropConstraint(final JdbcTemplate jdbcTemplate, final String schemaName,
	                      final String tableName, final String constraintName) {

		super(jdbcTemplate);

		this.schemaName = schemaName;
		this.tableName = tableName;
		this.constraintName = constraintName;
	}

	@Override
	public String getTemplateClassPath() {
		return "gov/acwi/wqp/etl/dropConstraint.sql";
	}

	@Override
	public String getSqlString() {

		HashMap<String, String> params = new HashMap();
		params.put("schema_name", schemaName);
		params.put("table_name", tableName);
		params.put("constraint_name", constraintName);

		return super.getSqlString(params);
	}

}
