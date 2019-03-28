package gov.acwi.wqp.etl.codes.characteristicType.table;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.DropSwapTable;
import gov.acwi.wqp.etl.codes.characteristicType.CharacteristicType;

@Component
@StepScope
public class DropCharacteristicTypeSwapTable extends DropSwapTable {

	@Autowired
	public DropCharacteristicTypeSwapTable(JdbcTemplate jdbcTemplate,
			@Value("#{jobParameters['wqpDataSource']}") String wqpDataSource,
			@Value("#{jobParameters['schemaName']}") String schemaName) {
		super(jdbcTemplate, wqpDataSource, schemaName);
	}

	protected String getBaseTableName() {
		return CharacteristicType.BASE_TABLE_NAME;
	}
}
