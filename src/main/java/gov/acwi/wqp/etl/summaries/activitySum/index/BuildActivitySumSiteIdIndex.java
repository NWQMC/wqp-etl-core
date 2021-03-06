package gov.acwi.wqp.etl.summaries.activitySum.index;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.index.BuildSiteIdIndex;
import gov.acwi.wqp.etl.summaries.activitySum.ActivitySum;

@Component
@StepScope
public class BuildActivitySumSiteIdIndex extends BuildSiteIdIndex {

	@Autowired
	public BuildActivitySumSiteIdIndex(JdbcTemplate jdbcTemplate,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_DATA_SOURCE) String wqpDataSource,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_WQP_SCHEMA) String wqpSchemaName) {
		super(jdbcTemplate, wqpDataSource, wqpSchemaName);
	}

	protected String getBaseTableName() {
		return ActivitySum.BASE_TABLE_NAME;
	}

}
