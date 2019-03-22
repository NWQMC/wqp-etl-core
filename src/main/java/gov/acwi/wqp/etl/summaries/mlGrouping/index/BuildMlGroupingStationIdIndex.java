package gov.acwi.wqp.etl.summaries.mlGrouping.index;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BuildStationIdIndex;
import gov.acwi.wqp.etl.summaries.mlGrouping.MlGrouping;

@Component
@StepScope
public class BuildMlGroupingStationIdIndex extends BuildStationIdIndex {

	@Autowired
	public BuildMlGroupingStationIdIndex(JdbcTemplate jdbcTemplate,
			@Value("#{jobParameters['wqpDataSource']}") String wqpDataSource,
			@Value("#{jobParameters['schemaName']}") String schemaName) {
		super(jdbcTemplate, wqpDataSource, schemaName);
	}

	protected String getBaseTableName() {
		return MlGrouping.BASE_TABLE_NAME;
	}
}
