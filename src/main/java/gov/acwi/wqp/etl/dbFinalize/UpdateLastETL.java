package gov.acwi.wqp.etl.dbFinalize;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class UpdateLastETL implements Tasklet {

	private final JdbcTemplate jdbcTemplate;
	private final int wqpDataSourceId;

	@Autowired
	public UpdateLastETL(JdbcTemplate jdbcTemplate,
		@Value("#{jobParameters['wqpDataSourceId']}") int wqpDataSourceId) {
		this.jdbcTemplate = jdbcTemplate;
		this.wqpDataSourceId = wqpDataSourceId;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		int numRows = jdbcTemplate
				.update("update last_etl"
					+ "     set data_source_id = ?,"
					+ "         completed_utc = (now() at time zone 'utc')", wqpDataSourceId);

		if (0 == numRows) {
			jdbcTemplate
				.update("insert into last_etl"
					+ "  values (?, (now() at time zone 'utc'))", wqpDataSourceId);
		}
		return RepeatStatus.FINISHED;
	}
}
