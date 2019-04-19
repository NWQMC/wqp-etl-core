package gov.acwi.wqp.etl;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class CreateSwapTable extends FunctionCallTasklet {

	public static final String FUNCTION_NAME = "create_swap_table";

	public CreateSwapTable(JdbcTemplate jdbcTemplate,
			String wqpDataSource,
			String wqpSchemaName) {
		super(jdbcTemplate, wqpDataSource, wqpSchemaName);
	}

	protected String getFunctionName() {
		return FUNCTION_NAME;
	}
}
