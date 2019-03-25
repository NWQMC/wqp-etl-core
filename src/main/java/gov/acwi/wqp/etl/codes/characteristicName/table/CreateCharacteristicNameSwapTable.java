package gov.acwi.wqp.etl.codes.characteristicName.table;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.CreateSwapTable;
import gov.acwi.wqp.etl.codes.characteristicName.CharacteristicName;

@Component
@StepScope
public class CreateCharacteristicNameSwapTable extends CreateSwapTable {

	@Autowired
	public CreateCharacteristicNameSwapTable(JdbcTemplate jdbcTemplate,
			@Value("#{jobParameters['wqpDataSource']}") String wqpDataSource,
			@Value("#{jobParameters['schemaName']}") String schemaName) {
		super(jdbcTemplate, wqpDataSource, schemaName);
	}

	protected String getBaseTableName() {
		return CharacteristicName.BASE_TABLE_NAME;
	}
}
