package gov.acwi.wqp.etl.codes.characteristicName.table;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.CreateSwapTable;
import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.codes.characteristicName.CharacteristicName;

@Component
@StepScope
public class CreateCharacteristicNameSwapTable extends CreateSwapTable {

	@Autowired
	public CreateCharacteristicNameSwapTable(JdbcTemplate jdbcTemplate,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_DATA_SOURCE) String wqpDataSource,
			@Value(EtlConstantUtils.VALUE_JOB_PARM_WQP_SCHEMA) String wqpSchemaName) {
		super(jdbcTemplate, wqpDataSource, wqpSchemaName);
	}

	protected String getBaseTableName() {
		return CharacteristicName.BASE_TABLE_NAME;
	}
}
