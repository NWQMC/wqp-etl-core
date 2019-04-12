package gov.acwi.wqp.etl.summaries.organizationSum.index;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BuildOrganizationIndex;
import gov.acwi.wqp.etl.summaries.organizationSum.OrganizationSum;

@Component
@StepScope
public class BuildOrganizationSumOrganizationIndex extends BuildOrganizationIndex {

	@Autowired
	public BuildOrganizationSumOrganizationIndex(JdbcTemplate jdbcTemplate,
			@Value("#{jobParameters['wqpDataSource']}") String wqpDataSource,
			@Value("#{jobParameters['schemaName']}") String schemaName) {
		super(jdbcTemplate, wqpDataSource, schemaName);
	}

	protected String getBaseTableName() {
		return OrganizationSum.BASE_TABLE_NAME;
	}
}
