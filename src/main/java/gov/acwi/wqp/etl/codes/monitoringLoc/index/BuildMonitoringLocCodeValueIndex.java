package gov.acwi.wqp.etl.codes.monitoringLoc.index;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BuildCodeValueIndex;
import gov.acwi.wqp.etl.codes.monitoringLoc.MonitoringLoc;

@Component
@StepScope
public class BuildMonitoringLocCodeValueIndex extends BuildCodeValueIndex {

	@Autowired
	public BuildMonitoringLocCodeValueIndex(JdbcTemplate jdbcTemplate,
			@Value("#{jobParameters['wqpDataSource']}") String wqpDataSource,
			@Value("#{jobParameters['schemaName']}") String schemaName) {
		super(jdbcTemplate, wqpDataSource, schemaName);
	}

	protected String getBaseTableName() {
		return MonitoringLoc.BASE_TABLE_NAME;
	}
}
