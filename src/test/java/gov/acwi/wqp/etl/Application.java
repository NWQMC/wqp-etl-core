package gov.acwi.wqp.etl;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
public class Application {

	public static final Integer DATA_SOURCE_ID = 1;
	public static final String DATA_SOURCE = "STEWARDS";
	public static final Integer ORGANIZATION_ID = 3000000;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Step noopStep() {
		return stepBuilderFactory
				.get("noopStep")
				.tasklet(new NoopTasklet())
				.build();
	}

	@Bean
	public Job testJob() {
		return jobBuilderFactory.get("testJob")
			.start(noopStep())
			.build();
	}

}
