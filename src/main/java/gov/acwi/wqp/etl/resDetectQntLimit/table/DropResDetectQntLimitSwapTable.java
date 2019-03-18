package gov.acwi.wqp.etl.resDetectQntLimit.table;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.DropSwapTable;
import gov.acwi.wqp.etl.resDetectQntLimit.ResDetectQntLimit;

@Component
@StepScope
public class DropResDetectQntLimitSwapTable extends DropSwapTable {

	@Autowired
	public DropResDetectQntLimitSwapTable(JdbcTemplate jdbcTemplate,
			@Value("#{jobParameters['wqpDataSource']}") String wqpDataSource,
			@Value("#{jobParameters['schemaName']}") String schemaName) {
		super(jdbcTemplate, wqpDataSource, schemaName);
	}

	protected String getBaseTableName() {
		return ResDetectQntLimit.BASE_TABLE_NAME;
	}
}
