package gov.acwi.wqp.etl.index;

import org.springframework.jdbc.core.JdbcTemplate;

import gov.acwi.wqp.etl.FunctionCallTasklet;

public abstract class BuildHuc4Index extends FunctionCallTasklet {

	public static final String FUNCTION_NAME = "build_huc_4_index";

	public BuildHuc4Index(JdbcTemplate jdbcTemplate,
			String wqpDataSource,
			String wqpSchemaName) {
		super(jdbcTemplate, wqpDataSource, wqpSchemaName);
	}

	protected String getFunctionName() {
		return FUNCTION_NAME;
	}

}
