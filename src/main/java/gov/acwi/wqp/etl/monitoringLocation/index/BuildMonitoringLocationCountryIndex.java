package gov.acwi.wqp.etl.monitoringLocation.index;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class BuildMonitoringLocationCountryIndex implements Tasklet {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public BuildMonitoringLocationCountryIndex(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		jdbcTemplate.update("create index if not exists station_stewards_country on station_swap_stewards(governmental_unit_code)");
//TODO correct SQL
//		jdbcTemplate.update("create index if not exists station_stewards_country on ${schemaName}.station_stewards(substring(governmental_unit_code, '[^:]+')) with (fillfactor = 100)");
		return RepeatStatus.FINISHED;
	}
}
