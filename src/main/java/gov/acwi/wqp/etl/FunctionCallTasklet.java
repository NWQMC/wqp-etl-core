package gov.acwi.wqp.etl;

import java.sql.Types;
import java.util.HashMap;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public abstract class FunctionCallTasklet implements Tasklet {

	private final JdbcTemplate jdbcTemplate;
	private final String wqpDataSource;
	private final String wqpSchemaName;

	public FunctionCallTasklet(JdbcTemplate jdbcTemplate,
			String wqpDataSource,
			String wqpSchemaName) {
		this.wqpDataSource = wqpDataSource;
		this.wqpSchemaName = wqpSchemaName;
		this.jdbcTemplate = jdbcTemplate;
	}

	protected abstract String getFunctionName();

	protected abstract String getBaseTableName();

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
				.withSchemaName(wqpSchemaName)
				.withoutProcedureColumnMetaDataAccess()
				.withFunctionName(getFunctionName())
				.declareParameters(
						new SqlParameter("wqp_data_source", Types.VARCHAR),
						new SqlParameter("wqp_schema_name", Types.VARCHAR),
						new SqlParameter("base_table_name", Types.VARCHAR)
						);
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("wqp_data_source", wqpDataSource);
		params.put("wqp_schema_name", wqpSchemaName);
		params.put("base_table_name", getBaseTableName());
		call.executeFunction(String.class, params);
		return RepeatStatus.FINISHED;
	}
}
