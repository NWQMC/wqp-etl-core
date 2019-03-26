package gov.acwi.wqp.etl.codes.state.index;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BuildCodeValueIndex;
import gov.acwi.wqp.etl.codes.state.State;

@Component
@StepScope
public class BuildStateCodeValueIndex extends BuildCodeValueIndex {

	@Autowired
	public BuildStateCodeValueIndex(JdbcTemplate jdbcTemplate,
			@Value("#{jobParameters['wqpDataSource']}") String wqpDataSource,
			@Value("#{jobParameters['schemaName']}") String schemaName) {
		super(jdbcTemplate, wqpDataSource, schemaName);
	}

	protected String getBaseTableName() {
		return State.BASE_TABLE_NAME;
	}
}
