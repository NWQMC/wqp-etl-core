package gov.acwi.wqp.etl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.*;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestExecutionListeners({
		DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class
})
@ContextConfiguration(classes = {ConfigurationService.class})
@DirtiesContext
@TestPropertySource(properties = {"DB_OPERATION_CONCURRENCY=2",
                                  "WQP_SCHEMA_NAME=xyz",
                                  "ETL_RESULT_PARTITION_START_DATE=1990-01-01",
                                  "ETL_RESULT_PARTITION_ONE_YEAR_BREAK=2015-01-01",
                                  "ETL_RESULT_PARTITION_QUARTER_BREAK=2015-01-01",
                                  "ETL_RESULT_PARTITION_END_DATE=2015-01-01",
                                  "ETL_RUN_TIME=2021-01-01T10:15:30"})
class ConfigurationServiceTest {


	@Autowired
	ConfigurationService config;

	@Test
	void getWqpSchemaName() {
		assertEquals("xyz", config.getWqpSchemaName());
	}

	@Test
	public void dbConcurrencyShouldBeSetTo2ViaProperty() {
		assertEquals(2, config.getDbOperationConcurrency());
	}

	@Test
	void getEtlResultPartitionStartDate() {
		assertEquals(LocalDate.parse("1990-01-01"), config.getEtlResultPartitionStartDate());
	}

	@Test
	void getEtlResultPartitionOneYearBreak() {
		assertEquals(LocalDate.parse("2015-01-01"), config.getEtlResultPartitionOneYearBreak());
	}

	@Test
	void getEtlResultPartitionQuarterBreak() {
		assertEquals(LocalDate.parse("2015-01-01"), config.getEtlResultPartitionQuarterBreak());
	}

	@Test
	void getEtlResultPartitionEndDate() {
		assertEquals(LocalDate.parse("2015-01-01"), config.getEtlResultPartitionEndDate());
	}

	@Test
	void getEtlRunTime() {
		assertEquals(LocalDateTime.parse("2021-01-01T10:15:30"), config.getEtlRunTime());
	}
}
