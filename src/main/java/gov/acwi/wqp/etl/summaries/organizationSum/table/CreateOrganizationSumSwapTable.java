package gov.acwi.wqp.etl.summaries.organizationSum.table;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.CreateSwapTable;
import gov.acwi.wqp.etl.summaries.organizationSum.OrganizationSum;

@Component
@StepScope
public class CreateOrganizationSumSwapTable extends CreateSwapTable {

	@Autowired
	public CreateOrganizationSumSwapTable(JdbcTemplate jdbcTemplate,
			@Value("#{jobParameters['wqpDataSource']}") String wqpDataSource,
			@Value("#{jobParameters['schemaName']}") String schemaName) {
		super(jdbcTemplate, wqpDataSource, schemaName);
	}

	protected String getBaseTableName() {
		return OrganizationSum.BASE_TABLE_NAME;
	}
}
