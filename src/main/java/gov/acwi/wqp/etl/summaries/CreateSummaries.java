package gov.acwi.wqp.etl.summaries;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateSummaries {

//	@Autowired
//	@Qualifier("createActivitySumFlow")
//	private Flow createActivitySumFlow;
//	@Autowired
//	@Qualifier("createResultSumFlow")
//	private Flow createResultSumFlow;
//	@Autowired
//	@Qualifier("createOrgGroupingFlow")
//	private Flow createOrgGroupingFlow;
//	@Autowired
//	@Qualifier("createMlGroupingFlow")
//	private Flow createMlGroupingFlow;
//	@Autowired
//	@Qualifier("createOrganizationSumFlow")
//	private Flow createOrganizationSumFlow;
//	@Autowired
//	@Qualifier("createStationSumFlow")
//	private Flow createStationSumFlow;
//	@Autowired
//	@Qualifier("createQwportalSumFlow")
//	private Flow createQwportalSumFlow;


	@Autowired
	@Qualifier("noopStep")
	private Step noopStep;
	@Bean
	public Flow createSummariesFlow() {
		return new FlowBuilder<SimpleFlow>("createSummariesFlow")
				.start(noopStep)
//				.start(createActivitySumFlow)
//				.next(createResultSumFlow)
//				.next(createOrgGroupingFlow)
//				.next(createMlGroupingFlow)
//				.next(createOrganizationSumFlow)
//				.next(createStationSumFlow)
//				.next(createQwportalSumFlow)
				.build();
	}

}
