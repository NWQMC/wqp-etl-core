package gov.acwi.wqp.etl;

import java.util.HashMap;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public abstract class DropSwapTable implements Tasklet {

	public static final String FUNCTION_NAME = "destroy_swap_table";
	private final JdbcTemplate jdbcTemplate;
	private final String wqpDataSource;
	private final String schemaName;

	public DropSwapTable(JdbcTemplate jdbcTemplate,
			String wqpDataSource,
			String schemaName) {
		this.wqpDataSource = wqpDataSource;
		this.schemaName = schemaName;
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withSchemaName(schemaName).withFunctionName(FUNCTION_NAME);
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("wqp_data_source", wqpDataSource);
		params.put("schema_name", schemaName);
		params.put("base_table_name", getBaseTableName());
		call.execute(params);
		return RepeatStatus.FINISHED;
	}

	protected abstract String getBaseTableName();
}
