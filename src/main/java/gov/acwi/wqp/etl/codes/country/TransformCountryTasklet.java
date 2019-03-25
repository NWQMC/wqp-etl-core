package gov.acwi.wqp.etl.codes.country;

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

@Component
@StepScope
public class TransformCountryTasklet implements Tasklet {

	private final JdbcTemplate jdbcTemplate;
	private final PreparedStatementSetter pss;

	@Autowired
	public TransformCountryTasklet(JdbcTemplate jdbcTemplate,
			PreparedStatementSetter pss) {
		this.jdbcTemplate = jdbcTemplate;
		this.pss = pss;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		jdbcTemplate.query("select * from transform_country(?,?)", pss, new NoopResultSetExtractor());
		return RepeatStatus.FINISHED;
	}
}
