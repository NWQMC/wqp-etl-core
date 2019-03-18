package gov.acwi.wqp.etl.codes;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CreateCodes {

//	@Autowired
//	@Qualifier("createCodeFlow")
//	private Flow createCodeFlow;

	@Autowired
	@Qualifier("noopStep")
	private Step noopStep;

	@Bean
	public Flow createCodesFlow() {
		return new FlowBuilder<SimpleFlow>("createCodesFlow")
				.start(noopStep)
//				.start(createCodeFlow)
				.build();
	}
}
