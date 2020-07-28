package gov.acwi.wqp.etl.codes.projectDim.index;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.codes.projectDim.ProjectDim;
import gov.acwi.wqp.etl.index.BuildProjectDimValueIndex;

@Component
@StepScope
public class BuildProjectDimProjectDimValueIndex extends BuildProjectDimValueIndex {

	@Autowired
	public BuildProjectDimProjectDimValueIndex(JdbcTemplate jdbcTemplate,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_DATA_SOURCE) String wqpDataSource,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_WQP_SCHEMA) String wqpSchemaName) {
		super(jdbcTemplate, wqpDataSource, wqpSchemaName);
	}

	protected String getBaseTableName() {
		return ProjectDim.BASE_TABLE_NAME;
	}
}
