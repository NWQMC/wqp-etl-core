package gov.acwi.wqp.etl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
public class Application {
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static final String JOB_ID = "jobId";
	public static final Integer DATA_SOURCE_ID = 1;
	public static final String DATA_SOURCE = "STEWARDS";
	public static final Integer ORGANIZATION_ID = 3000000;

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	@Qualifier("noopStep")
	private Step noopStep;

	public static void main(String[] args) {
		LOG.info(args.toString());
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Job testJob() {
		return jobBuilderFactory.get("testJob")
			.start(noopStep)
			.build();
	}

}
