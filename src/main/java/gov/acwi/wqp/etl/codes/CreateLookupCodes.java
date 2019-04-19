package gov.acwi.wqp.etl.codes;

import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.EtlConstantUtils;

@Component
public class CreateLookupCodes {

	@Autowired
	@Qualifier("createAssemblageFlow")
	private Flow createAssemblageFlow;

	@Autowired
	@Qualifier("createCharacteristicNameFlow")
	private Flow createCharacteristicNameFlow;

	@Autowired
	@Qualifier("createCharacteristicTypeFlow")
	private Flow createCharacteristicTypeFlow;

	@Autowired
	@Qualifier("createCountryFlow")
	private Flow createCountryFlow;

	@Autowired
	@Qualifier("createCountyFlow")
	private Flow createCountyFlow;

	@Autowired
	@Qualifier("createMonitoringLocFlow")
	private Flow createMonitoringLocFlow;

	@Autowired
	@Qualifier("createOrganizationFlow")
	private Flow createOrganizationFlow;

	@Autowired
	@Qualifier("createProjectDimFlow")
	private Flow createProjectDimFlow;

	@Autowired
	@Qualifier("createProjectFlow")
	private Flow createProjectFlow;

	@Autowired
	@Qualifier("createSampleMediaFlow")
	private Flow createSampleMediaFlow;

	@Autowired
	@Qualifier("createSiteTypeFlow")
	private Flow createSiteTypeFlow;

	@Autowired
	@Qualifier("createStateFlow")
	private Flow createStateFlow;

	@Autowired
	@Qualifier("createTaxaNameFlow")
	private Flow createTaxaNameFlow;

	@Bean
	public Flow createLookupCodesFlow() {
		return new FlowBuilder<SimpleFlow>(EtlConstantUtils.CREATE_LOOKUP_CODES_FLOW)
				.start(createAssemblageFlow)
				.next(createCharacteristicNameFlow)
				.next(createCharacteristicTypeFlow)
				.next(createCountryFlow)
				.next(createCountyFlow)
				.next(createMonitoringLocFlow)
				.next(createOrganizationFlow)
				.next(createProjectDimFlow)
				.next(createProjectFlow)
				.next(createSampleMediaFlow)
				.next(createSiteTypeFlow)
				.next(createStateFlow)
				.next(createTaxaNameFlow)
				.build();
	}
}
