package gov.acwi.wqp.etl.projectMLWeighting.index;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.index.BuildGeom2163Index;
import gov.acwi.wqp.etl.projectMLWeighting.ProjectMLWeighting;

@Component
@StepScope
public class BuildProjectMLWeightingGeom2163Index extends BuildGeom2163Index {

	@Autowired
	public BuildProjectMLWeightingGeom2163Index(JdbcTemplate jdbcTemplate,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_DATA_SOURCE) String wqpDataSource,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_WQP_SCHEMA) String wqpSchemaName) {
		super(jdbcTemplate, wqpDataSource, wqpSchemaName);
	}

	protected String getBaseTableName() {
		return ProjectMLWeighting.BASE_TABLE_NAME;
	}

}
