package gov.acwi.wqp.etl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class CorePreparedStatementSetter implements PreparedStatementSetter {

	private final String wqpDataSource;
	private final String schemaName;

	public CorePreparedStatementSetter(
			@Value("#{jobParameters['wqpDataSource']}") String wqpDataSource,
			@Value("#{jobParameters['schemaName']}") String schemaName) {
		this.wqpDataSource = wqpDataSource;
		this.schemaName = schemaName;
	}

	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
		ps.setString(1, wqpDataSource);
		ps.setString(2, schemaName);
	}

}
