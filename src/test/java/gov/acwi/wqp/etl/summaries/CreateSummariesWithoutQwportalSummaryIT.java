package gov.acwi.wqp.etl.summaries;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import gov.acwi.wqp.etl.DBTestConfig;
import gov.acwi.wqp.etl.EtlConstantUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import({DBTestConfig.class})
@TestPropertySource(properties={
		"WQP_SCHEMA_NAME>wqp",
		"GEO_SCHEMA_NAME:nwis",
		"ETL_DATA_SOURCE_ID:0",
		"ETL_DATA_SOURCE:testsrc",
		"QWPORTAL_SUMMARY_ETL:false"
	})
public class CreateSummariesWithoutQwportalSummaryIT {

	@Autowired
	@Qualifier(EtlConstantUtils.CREATE_SUMMARIES_FLOW)
	private Flow flow;

	@Test
	public void excludedQwportalSum() {
		assertNotNull(flow.getState("createSummariesFlow.activitySumFlow"));
		assertNotNull(flow.getState("createSummariesFlow.resultSumFlow"));
		assertNotNull(flow.getState("createSummariesFlow.orgGroupingFlow"));
		assertNotNull(flow.getState("createSummariesFlow.mlGroupingFlow"));
		assertNotNull(flow.getState("createSummariesFlow.monitoringLocationSumFlow"));
		assertNull(flow.getState("createSummariesFlow.qwportalSummaryFlow"));
	}
}
