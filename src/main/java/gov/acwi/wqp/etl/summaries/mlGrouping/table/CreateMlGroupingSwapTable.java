package gov.acwi.wqp.etl.summaries.mlGrouping.table;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.CreateSwapTable;
import gov.acwi.wqp.etl.summaries.mlGrouping.MlGrouping;

@Component
@StepScope
public class CreateMlGroupingSwapTable extends CreateSwapTable {

	@Autowired
	public CreateMlGroupingSwapTable(JdbcTemplate jdbcTemplate,
			@Value("#{jobParameters['wqpDataSource']}") String wqpDataSource,
			@Value("#{jobParameters['schemaName']}") String schemaName) {
		super(jdbcTemplate, wqpDataSource, schemaName);
	}

	protected String getBaseTableName() {
		return MlGrouping.BASE_TABLE_NAME;
	}
}
