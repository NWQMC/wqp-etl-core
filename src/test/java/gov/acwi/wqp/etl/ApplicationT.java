package gov.acwi.wqp.etl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication
//@Profile("it")
public class ApplicationT {
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationT.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	@Qualifier("noopStep")
	private Step noopStep;

	public static void main(String[] args) {
		LOG.info(args.toString());
		SpringApplication.run(ApplicationT.class, args);
	}

	@Bean
	Job testJob() {
		return jobBuilderFactory.get("testJob")
			.start(noopStep)
			.build();
	}

}
