package gov.acwi.wqp.etl.summaries.orgGrouping.index;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BuildOrganizationIndex;
import gov.acwi.wqp.etl.summaries.orgGrouping.OrgGrouping;

@Component
@StepScope
public class BuildOrgGroupingOrganizationIndex extends BuildOrganizationIndex {

	@Autowired
	public BuildOrgGroupingOrganizationIndex(JdbcTemplate jdbcTemplate,
			@Value("#{jobParameters['wqpDataSource']}") String wqpDataSource,
			@Value("#{jobParameters['schemaName']}") String schemaName) {
		super(jdbcTemplate, wqpDataSource, schemaName);
	}

	protected String getBaseTableName() {
		return OrgGrouping.BASE_TABLE_NAME;
	}
}
