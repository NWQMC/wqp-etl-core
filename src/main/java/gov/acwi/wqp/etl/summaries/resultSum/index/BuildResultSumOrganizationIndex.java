package gov.acwi.wqp.etl.summaries.resultSum.index;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BuildOrganizationIndex;
import gov.acwi.wqp.etl.summaries.resultSum.ResultSum;

@Component
@StepScope
public class BuildResultSumOrganizationIndex extends BuildOrganizationIndex {

	@Autowired
	public BuildResultSumOrganizationIndex(JdbcTemplate jdbcTemplate,
			@Value("#{jobParameters['wqpDataSource']}") String wqpDataSource,
			@Value("#{jobParameters['schemaName']}") String schemaName) {
		super(jdbcTemplate, wqpDataSource, schemaName);
	}

	protected String getBaseTableName() {
		return ResultSum.BASE_TABLE_NAME;
	}
}
