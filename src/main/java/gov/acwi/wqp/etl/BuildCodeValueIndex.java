package gov.acwi.wqp.etl;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BuildCodeValueIndex extends FunctionCallTasklet {

	public static final String FUNCTION_NAME = "build_code_value_index";

	public BuildCodeValueIndex(JdbcTemplate jdbcTemplate,
			String wqpDataSource,
			String schemaName) {
		super(jdbcTemplate, wqpDataSource, schemaName);
	}

	protected String getFunctionName() {
		return FUNCTION_NAME;
	}
}
