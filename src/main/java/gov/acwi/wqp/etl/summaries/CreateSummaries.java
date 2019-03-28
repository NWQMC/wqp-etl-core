package gov.acwi.wqp.etl.summaries;

import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateSummaries {

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
//	@Autowired
//	@Qualifier("orgSumFlow")
//	private Flow orgSumFlow;
	@Autowired
	@Qualifier("monitoringLocationSumFlow")
	private Flow monitoringLocationSumFlow;
//	@Autowired
//	@Qualifier("qwportalSumFlow")
//	private Flow qwportalSumFlow;

	@Bean
	public Flow createSummariesFlow() {
		return new FlowBuilder<SimpleFlow>("createSummariesFlow")
				.start(activitySumFlow)
				.next(resultSumFlow)
				.next(orgGroupingFlow)
				.next(mlGroupingFlow)
				//TODO				.next(orgSumFlow)
				.next(monitoringLocationSumFlow)
//TODO				.next(qwportalSumFlow)
				.build();
	}

}
