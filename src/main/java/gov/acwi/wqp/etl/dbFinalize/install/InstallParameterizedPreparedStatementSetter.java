package gov.acwi.wqp.etl.dbFinalize.install;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class InstallParameterizedPreparedStatementSetter implements ParameterizedPreparedStatementSetter<InstallTable> {

	@Override
	public void setValues(PreparedStatement ps, InstallTable installTable) throws SQLException {
		ps.setString(1, installTable.getWqpDataSource());
		ps.setString(2, installTable.getWqpSchemaName());
		ps.setString(3, installTable.getBase_table_name());
		ps.setInt(4, installTable.getData_source_id());
	}

}
