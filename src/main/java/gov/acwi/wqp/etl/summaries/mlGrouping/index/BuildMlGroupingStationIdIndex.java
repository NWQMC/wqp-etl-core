package gov.acwi.wqp.etl.summaries.mlGrouping.index;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BuildStationIdIndex;
import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.summaries.mlGrouping.MlGrouping;

@Component
@StepScope
public class BuildMlGroupingStationIdIndex extends BuildStationIdIndex {

	@Autowired
	public BuildMlGroupingStationIdIndex(JdbcTemplate jdbcTemplate,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_DATA_SOURCE) String wqpDataSource,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_WQP_SCHEMA) String wqpSchemaName) {
		super(jdbcTemplate, wqpDataSource, wqpSchemaName);
	}

	protected String getBaseTableName() {
		return MlGrouping.BASE_TABLE_NAME;
	}
}
