package gov.acwi.wqp.etl.resDetectQntLimit.index;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.index.BuildHuc2Index;
import gov.acwi.wqp.etl.resDetectQntLimit.ResDetectQntLimit;

@Component
@StepScope
public class BuildResDetectQntLimitHuc2Index extends BuildHuc2Index {

	@Autowired
	public BuildResDetectQntLimitHuc2Index(JdbcTemplate jdbcTemplate,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_DATA_SOURCE) String wqpDataSource,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_WQP_SCHEMA) String wqpSchemaName) {
		super(jdbcTemplate, wqpDataSource, wqpSchemaName);
	}

	protected String getBaseTableName() {
		return ResDetectQntLimit.BASE_TABLE_NAME;
	}

}
