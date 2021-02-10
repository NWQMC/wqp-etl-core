package gov.acwi.wqp.etl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.*;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestExecutionListeners({
		DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class
})
@ContextConfiguration(classes = {ConfigurationService.class})
@DirtiesContext
class ConfigurationServiceWithNullResultPartitionDatesTest {

	/* No propeerties set - compare to ConfigurationServiceTest */

	@Autowired
	ConfigurationService config;

	@Test
	void getEtlResultPartitionStartDate() {
		assertNull(config.getEtlResultPartitionStartDate());
	}

	@Test
	void getEtlResultPartitionOneYearBreak() {
		assertNull(config.getEtlResultPartitionOneYearBreak());
	}

	@Test
	void getEtlResultPartitionQuarterBreak() {
		assertNull(config.getEtlResultPartitionQuarterBreak());
	}

	@Test
	void getEtlResultPartitionEndDate() {
		assertNull(config.getEtlResultPartitionEndDate());
	}

	@Test
	void getEtlRunTime() {
		assertNotNull(config.getEtlRunTime());  //Defaults to now()
	}

}
