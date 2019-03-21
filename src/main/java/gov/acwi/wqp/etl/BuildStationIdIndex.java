package gov.acwi.wqp.etl;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BuildStationIdIndex extends FunctionCallTasklet {

	public static final String FUNCTION_NAME = "build_station_id_index";

	public BuildStationIdIndex(JdbcTemplate jdbcTemplate,
			String wqpDataSource,
			String schemaName) {
		super(jdbcTemplate, wqpDataSource, schemaName);
	}

	protected String getFunctionName() {
		return FUNCTION_NAME;
	}
}
