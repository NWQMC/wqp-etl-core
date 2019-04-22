package gov.acwi.wqp.etl;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class DropSwapTable extends FunctionCallTasklet {

	public static final String FUNCTION_NAME = "destroy_swap_table";

	public DropSwapTable(JdbcTemplate jdbcTemplate,
			String wqpDataSource,
			String wqpSchemaName) {
		super(jdbcTemplate, wqpDataSource, wqpSchemaName);
	}

	protected String getFunctionName() {
		return FUNCTION_NAME;
	}
}
