package gov.acwi.wqp.etl;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BuildOrganizationIndex extends FunctionCallTasklet {

	public static final String FUNCTION_NAME = "build_organization_index";

	public BuildOrganizationIndex(JdbcTemplate jdbcTemplate,
			String wqpDataSource,
			String wqpSchemaName) {
		super(jdbcTemplate, wqpDataSource, wqpSchemaName);
	}

	protected String getFunctionName() {
		return FUNCTION_NAME;
	}
}
