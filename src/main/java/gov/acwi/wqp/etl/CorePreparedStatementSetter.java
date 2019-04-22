package gov.acwi.wqp.etl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

@Component
@StepScope
@Primary
public class CorePreparedStatementSetter implements PreparedStatementSetter {

	private final String wqpDataSource;
	private final String wqpSchemaName;

	public CorePreparedStatementSetter(
			@Value(EtlConstantUtils.VALUE_JOB_PARM_DATA_SOURCE) String wqpDataSource,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_WQP_SCHEMA) String wqpSchemaName) {
		this.wqpDataSource = wqpDataSource;
		this.wqpSchemaName = wqpSchemaName;
	}

	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
		ps.setString(1, wqpDataSource);
		ps.setString(2, wqpSchemaName);
	}

}
