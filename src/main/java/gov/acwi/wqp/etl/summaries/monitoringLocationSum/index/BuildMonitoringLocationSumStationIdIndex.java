package gov.acwi.wqp.etl.summaries.monitoringLocationSum.index;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BuildStationIdIndex;
import gov.acwi.wqp.etl.summaries.monitoringLocationSum.MonitoringLocationSum;

@Component
@StepScope
public class BuildMonitoringLocationSumStationIdIndex extends BuildStationIdIndex {

	@Autowired
	public BuildMonitoringLocationSumStationIdIndex(JdbcTemplate jdbcTemplate,
			@Value("#{jobParameters['wqpDataSource']}") String wqpDataSource,
			@Value("#{jobParameters['schemaName']}") String schemaName) {
		super(jdbcTemplate, wqpDataSource, schemaName);
	}

	protected String getBaseTableName() {
		return MonitoringLocationSum.BASE_TABLE_NAME;
	}
}
