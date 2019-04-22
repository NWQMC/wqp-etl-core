package gov.acwi.wqp.etl.summaries.monitoringLocationSum;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.EtlConstantUtils;

@Component
@StepScope
public class MonitoringLocationSumPreparedStatementSetter implements PreparedStatementSetter {

	private final String wqpDataSource;
	private final String schemaName;
	private final String geoSchemaName;

	public MonitoringLocationSumPreparedStatementSetter(
			@Value(EtlConstantUtils.VALUE_JOB_PARM_DATA_SOURCE) String wqpDataSource,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_WQP_SCHEMA) String schemaName,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_GEO_SCHEMA) String geoSchemaName) {
		this.wqpDataSource = wqpDataSource;
		this.schemaName = schemaName;
		this.geoSchemaName = geoSchemaName;
	}

	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
		ps.setString(1, wqpDataSource);
		ps.setString(2, schemaName);
		ps.setString(3, geoSchemaName);
	}

}
