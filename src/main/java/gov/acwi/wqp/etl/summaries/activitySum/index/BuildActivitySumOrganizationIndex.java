package gov.acwi.wqp.etl.summaries.activitySum.index;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BuildOrganizationIndex;
import gov.acwi.wqp.etl.summaries.activitySum.ActivitySum;

@Component
@StepScope
public class BuildActivitySumOrganizationIndex extends BuildOrganizationIndex {

	@Autowired
	public BuildActivitySumOrganizationIndex(JdbcTemplate jdbcTemplate,
			@Value("#{jobParameters['wqpDataSource']}") String wqpDataSource,
			@Value("#{jobParameters['schemaName']}") String schemaName) {
		super(jdbcTemplate, wqpDataSource, schemaName);
	}

	protected String getBaseTableName() {
		return ActivitySum.BASE_TABLE_NAME;
	}
}
