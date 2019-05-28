package gov.acwi.wqp.etl.summaries.monitoringLocationSum.index;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.index.BuildHuc2Index;
import gov.acwi.wqp.etl.summaries.monitoringLocationSum.MonitoringLocationSum;

@Component
@StepScope
public class BuildMonitoringLocationSumHuc2Index extends BuildHuc2Index {

	@Autowired
	public BuildMonitoringLocationSumHuc2Index(JdbcTemplate jdbcTemplate,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_DATA_SOURCE) String wqpDataSource,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_WQP_SCHEMA) String wqpSchemaName) {
		super(jdbcTemplate, wqpDataSource, wqpSchemaName);
	}

	protected String getBaseTableName() {
		return MonitoringLocationSum.BASE_TABLE_NAME;
	}
}
