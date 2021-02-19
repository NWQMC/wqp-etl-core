package gov.acwi.wqp.etl.index;

import gov.acwi.wqp.etl.SqlTemplateTasklet;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;

/**
 * Creates a geom index if it does not already exist.
 */
public class CreateGeomIndex extends SqlTemplateTasklet {

	private final String schemaName;
	private final String tableName;
	private final String indexName;
	private final String columnName;
	private final Integer projection;
	private final int fillFactor;

	/**
	 *
	 * @param jdbcTemplate
	 * @param schemaName
	 * @param tableName
	 * @param indexName
	 * @param columnName
	 * @param projection Null to use the db column projection, otherwise, an int value spec'ing a projection known to the db
	 */
	public CreateGeomIndex(final JdbcTemplate jdbcTemplate, final String schemaName,
	                       final String tableName, final String indexName, String columnName, Integer projection) {

		super(jdbcTemplate);

		this.schemaName = schemaName;
		this.tableName = tableName;
		this.indexName = indexName;
		this.columnName = columnName;
		this.projection = projection;
		this.fillFactor = 100;
	}

	/**
	 *
	 * @param jdbcTemplate
	 * @param schemaName
	 * @param tableName
	 * @param indexName
	 * @param columnName
	 * @param projection Null to use the db column projection, otherwise, an int value spec'ing a projection known to the db
	 * @param fillFactor
	 */
	public CreateGeomIndex(final JdbcTemplate jdbcTemplate, final String schemaName,
	                       final String tableName, final String indexName, String columnName, Integer projection, int fillFactor) {

		super(jdbcTemplate);

		this.schemaName = schemaName;
		this.tableName = tableName;
		this.indexName = indexName;
		this.columnName = columnName;
		this.projection = projection;
		this.fillFactor = fillFactor;
	}

	@Override
	public String getTemplateClassPath() {
		return "gov/acwi/wqp/etl/index/createGeomIndex.sql";
	}

	@Override
	public String getSqlString() {

		HashMap<String, String> params = new HashMap();
		params.put("schema_name", schemaName);
		params.put("table_name", tableName);
		params.put("index_name", indexName);
		params.put("fill_factor", Integer.toString(fillFactor));

		if (projection != null) {
			params.put("column_name", "st_transform(" + columnName + ", " + projection + ")");
		} else {
			params.put("column_name", columnName);
		}

		return super.getSqlString(params);
	}

}
