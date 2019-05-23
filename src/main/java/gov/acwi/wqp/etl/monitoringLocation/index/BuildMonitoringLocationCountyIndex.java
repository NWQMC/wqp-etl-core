package gov.acwi.wqp.etl.monitoringLocation.index;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.index.BuildCountyIndex;
import gov.acwi.wqp.etl.monitoringLocation.MonitoringLocation;

@Component
@StepScope
public class BuildMonitoringLocationCountyIndex extends BuildCountyIndex {

	@Autowired
	public BuildMonitoringLocationCountyIndex(JdbcTemplate jdbcTemplate,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_DATA_SOURCE) String wqpDataSource,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_WQP_SCHEMA) String wqpSchemaName) {
		super(jdbcTemplate, wqpDataSource, wqpSchemaName);
	}

	protected String getBaseTableName() {
		return MonitoringLocation.BASE_TABLE_NAME;
	}
}
