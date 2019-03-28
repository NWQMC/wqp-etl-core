package gov.acwi.wqp.etl.codes.characteristicName.index;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BuildCodeValueIndex;
import gov.acwi.wqp.etl.codes.characteristicName.CharacteristicName;

@Component
@StepScope
public class BuildCharacteristicNameCodeValueIndex extends BuildCodeValueIndex {

	@Autowired
	public BuildCharacteristicNameCodeValueIndex(JdbcTemplate jdbcTemplate,
			@Value("#{jobParameters['wqpDataSource']}") String wqpDataSource,
			@Value("#{jobParameters['schemaName']}") String schemaName) {
		super(jdbcTemplate, wqpDataSource, schemaName);
	}

	protected String getBaseTableName() {
		return CharacteristicName.BASE_TABLE_NAME;
	}
}
