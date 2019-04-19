package gov.acwi.wqp.etl.dbFinalize;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.NoopResultSetExtractor;

//TODO proper implementations - WQP-1399
@Component
@StepScope
public class Install implements Tasklet {

	private final JdbcTemplate jdbcTemplate;
	private final PreparedStatementSetter pss;

	@Autowired
	public Install(JdbcTemplate jdbcTemplate,
			PreparedStatementSetter pss) {
		this.jdbcTemplate = jdbcTemplate;
		this.pss = pss;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		jdbcTemplate.query("select * from install_new_data(?,?)", pss, new NoopResultSetExtractor());
		return RepeatStatus.FINISHED;
	}
}
