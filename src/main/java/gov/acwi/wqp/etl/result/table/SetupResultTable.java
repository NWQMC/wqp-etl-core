package gov.acwi.wqp.etl.result.table;

import gov.acwi.wqp.etl.*;
import gov.acwi.wqp.etl.partition.*;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;


@Configuration
public class SetupResultTable {

	@Autowired
	private ConfigurationService config;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dropResultSwapTable")
	private Tasklet dropResultSwapTable;

	@Autowired
	ResultPartitionStrategy resultPartitionStrategy;


	@Bean
	public Step dropResultSwapTableStep() {
		return stepBuilderFactory.get("dropResultSwapTableStep")
				.tasklet(dropResultSwapTable)
				.build();
	}

	@Bean
	public Step createResultSwapTableStep() {

		Tasklet task = new CreateResultSwapTable(
				jdbcTemplate, config.getWqpSchemaName(),
				"result_swap_" + config.getEtlDataSource(), "result", "event_date");

		return stepBuilderFactory.get("createResultSwapTableStep")
				.tasklet(task)
				.build();
	}

	@Bean
	public Flow setupResultSwapTableFlow() {

		List<PgDateRangePart> parts = resultPartitionStrategy.getPartitions();

		FlowBuilder<SimpleFlow> fb = new FlowBuilder<SimpleFlow>(EtlConstantUtils.SETUP_RESULT_SWAP_TABLE_FLOW);

		fb.start(dropResultSwapTableStep());
		fb.next(createResultSwapTableStep());

		//Build up the list of partitions
		for (PgDateRangePart part : parts) {

			{
				StepBuilder sb = stepBuilderFactory.get("create_partition_table_" + part.getTableName());

				Tasklet task = new CreateTableLike(jdbcTemplate, config.getWqpSchemaName(),
						part.getTableName(), "result");

				fb.next(sb.tasklet(task).build());
			}

			{
				StepBuilder sb = stepBuilderFactory.get("attach_partition_table_" + part.getTableName());

				Tasklet task = new AttachRangePartitionTable(jdbcTemplate, config.getWqpSchemaName(),
						part.getTableName(), "result_swap_" + config.getEtlDataSource(),
						part.getSqlFormatRangeStart(), part.getSqlFormatRangeEnd());

				fb.next(sb.tasklet(task).build());
			}
		}

		return fb.build();
	}
}
