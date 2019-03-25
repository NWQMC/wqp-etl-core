package gov.acwi.wqp.etl.codes;

import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CreateLookupCodes {

	@Autowired
	@Qualifier("createAssemblageFlow")
	private Flow createAssemblageFlow;

	@Bean
	public Flow createLookupCodesFlow() {
		return new FlowBuilder<SimpleFlow>("createLookupCodesFlow")
				.start(createAssemblageFlow)
				.build();
	}
}
