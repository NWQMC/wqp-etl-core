package gov.acwi.wqp.etl.summaries.monitoringLocationSum;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

//TODO
@Component
@StepScope
public class CreateMonitoringLocationSumPkTasklet implements Tasklet {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public CreateMonitoringLocationSumPkTasklet(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

//	@Value("classpath:sql/resDetectQntLimit/writeResDetectQntLimit.sql")
//	private Resource executeResource;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//		jdbcTemplate.execute(new String(FileCopyUtils.copyToByteArray(executeResource.getInputStream())));
		jdbcTemplate.execute("alter table station_sum_swap_stewards add primary key (data_source_id, station_id)");
		return RepeatStatus.FINISHED;
	}

}
