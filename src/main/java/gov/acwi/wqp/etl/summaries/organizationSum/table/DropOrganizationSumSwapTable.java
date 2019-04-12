package gov.acwi.wqp.etl.summaries.organizationSum.table;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.DropSwapTable;
import gov.acwi.wqp.etl.summaries.organizationSum.OrganizationSum;

@Component
@StepScope
public class DropOrganizationSumSwapTable extends DropSwapTable {

	@Autowired
	public DropOrganizationSumSwapTable(JdbcTemplate jdbcTemplate,
			@Value("#{jobParameters['wqpDataSource']}") String wqpDataSource,
			@Value("#{jobParameters['schemaName']}") String schemaName) {
		super(jdbcTemplate, wqpDataSource, schemaName);
	}

	protected String getBaseTableName() {
		return OrganizationSum.BASE_TABLE_NAME;
	}
}
