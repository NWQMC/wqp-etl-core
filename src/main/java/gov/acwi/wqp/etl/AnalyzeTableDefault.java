package gov.acwi.wqp.etl;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AnalyzeTableDefault extends FunctionCallTasklet {

	public static final String FUNCTION_NAME = "analyze_table_default";

	public AnalyzeTableDefault(JdbcTemplate jdbcTemplate,
			String wqpDataSource,
			String wqpSchemaName) {
		super(jdbcTemplate, wqpDataSource, wqpSchemaName);
	}

	protected String getFunctionName() {
		return FUNCTION_NAME;
	}

}
