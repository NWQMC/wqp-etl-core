package gov.acwi.wqp.etl.codes.sampleMedia.index;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BuildCodeValueIndex;
import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.codes.sampleMedia.SampleMedia;

@Component
@StepScope
public class BuildSampleMediaCodeValueIndex extends BuildCodeValueIndex {

	@Autowired
	public BuildSampleMediaCodeValueIndex(JdbcTemplate jdbcTemplate,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_DATA_SOURCE) String wqpDataSource,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_WQP_SCHEMA) String wqpSchemaName) {
		super(jdbcTemplate, wqpDataSource, wqpSchemaName);
	}

	protected String getBaseTableName() {
		return SampleMedia.BASE_TABLE_NAME;
	}
}
