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
public class BuildMonitoringLocationHuc2Index implements Tasklet {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public BuildMonitoringLocationHuc2Index(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		jdbcTemplate.update("create index if not exists station_testsrc_huc2 on station_swap_testsrc(huc)");
//TODO correct SQL - WQP-1400
//		jdbcTemplate.update("create index if not exists station_testsrc_huc2 on ${schemaName}.station_testsrc(substring(huc, '[0-9]{2}')) with (fillfactor = 100)");
		return RepeatStatus.FINISHED;
	}
}
