package gov.acwi.wqp.etl.dbFinalize.install;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import org.springframework.batch.test.StepScopeTestExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import gov.acwi.wqp.etl.DBTestConfig;
import gov.acwi.wqp.etl.summaries.qwportalSummary.QwportalSummary;

@SpringBootTest
@Import({DBTestConfig.class})
@TestPropertySource(properties={
		"WQP_SCHEMA_NAME:wqp",
		"GEO_SCHEMA_NAME:nwis",
		"ETL_DATA_SOURCE_ID:0",
		"ETL_DATA_SOURCE:testsrc",
		"QWPORTAL_SUMMARY_ETL:true"
	})
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	StepScopeTestExecutionListener.class 
})
public class InstallWithQwportalSummaryIT {

	@Autowired
	private Install install;

	@Test
	public void includeQwportalSum() {
		LinkedList<InstallTable> tables = (LinkedList<InstallTable>) install.getInstallTables();
		assertEquals(33, tables.size());
		assertEquals(QwportalSummary.BASE_TABLE_NAME, tables.get(19).getBaseTableName());
	}
}
