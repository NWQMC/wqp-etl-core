package gov.acwi.wqp.etl.summaries;

import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gov.acwi.wqp.etl.ConfigurationService;
import gov.acwi.wqp.etl.EtlConstantUtils;

@Configuration
public class CreateSummaries {

	@Autowired
	private ConfigurationService configurationService;
	@Autowired
	@Qualifier("activitySumFlow")
	private Flow activitySumFlow;
	@Autowired
	@Qualifier("resultSumFlow")
	private Flow resultSumFlow;
	@Autowired
	@Qualifier("orgGroupingFlow")
	private Flow orgGroupingFlow;
	@Autowired
	@Qualifier("mlGroupingFlow")
	private Flow mlGroupingFlow;
	@Autowired
	@Qualifier("organizationSumFlow")
	private Flow organizationSumFlow;
	@Autowired
	@Qualifier("monitoringLocationSumFlow")
	private Flow monitoringLocationSumFlow;
	@Autowired
	@Qualifier("qwportalSummaryFlow")
	private Flow qwportalSummaryFlow;
	@Autowired
	@Qualifier("addSummariesForeignKeysFlow")
	private Flow addSummariesForeignKeysFlow;

	@Bean
	public Flow createSummariesFlow() {
		FlowBuilder<SimpleFlow> flowBuilder = new FlowBuilder<SimpleFlow>(EtlConstantUtils.CREATE_SUMMARIES_FLOW)
				.start(activitySumFlow)
				.next(resultSumFlow)
				.next(orgGroupingFlow)
				.next(mlGroupingFlow)
				.next(organizationSumFlow)
				.next(monitoringLocationSumFlow)
				.next(addSummariesForeignKeysFlow);
		if (configurationService.isQwportalSummary()) {
			flowBuilder = flowBuilder.next(qwportalSummaryFlow);
		}
		return flowBuilder.build();
	}

}
