package gov.acwi.wqp.etl.summaries.qwportalSummary;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.EtlConstantUtils;

@Component
@StepScope
public class QwportalSummaryPreparedStatementSetter implements PreparedStatementSetter {

	private final String wqpDataSource;
	private final String schemaName;
	private final String nwisOrEpa;

	public QwportalSummaryPreparedStatementSetter(
			@Value(EtlConstantUtils.VALUE_JOB_PARM_DATA_SOURCE) String wqpDataSource,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_WQP_SCHEMA) String schemaName,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_NWIS_OR_EPA) String nwisOrEpa) {
		this.wqpDataSource = wqpDataSource;
		this.schemaName = schemaName;
		this.nwisOrEpa = nwisOrEpa;
	}

	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
		ps.setString(1, wqpDataSource);
		ps.setString(2, schemaName);
		ps.setString(3, nwisOrEpa);
	}

}
