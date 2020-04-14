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

@SpringBootTest
@Import({DBTestConfig.class})
@TestPropertySource(properties={
		"WQP_SCHEMA_NAME:wqp",
		"GEO_SCHEMA_NAME:nwis",
		"ETL_DATA_SOURCE_ID:0",
		"ETL_DATA_SOURCE:testsrc",
		"QWPORTAL_SUMMARY_ETL:false"
	})
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	StepScopeTestExecutionListener.class 
})
public class InstallWithOutQwportalSummaryIT {

	@Autowired
	private Install install;

	@Test
	public void doNotIncludeQwportalSum() {
		LinkedList<InstallTable> tables = (LinkedList<InstallTable>) install.getInstallTables();
		assertEquals(32, tables.size());
	}

}
