package gov.acwi.wqp.etl.activityMetric.table;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.CreateSwapTable;
import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.activityMetric.ActivityMetric;

@Component
@StepScope
public class CreateActivityMetricSwapTable extends CreateSwapTable {

	@Autowired
	public CreateActivityMetricSwapTable(JdbcTemplate jdbcTemplate,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_DATA_SOURCE) String wqpDataSource,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_WQP_SCHEMA) String wqpSchemaName) {
		super(jdbcTemplate, wqpDataSource, wqpSchemaName);
	}

	protected String getBaseTableName() {
		return ActivityMetric.BASE_TABLE_NAME;
	}
}
