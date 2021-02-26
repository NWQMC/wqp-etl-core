package gov.acwi.wqp.etl;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * A SpringBatch Tasklet that executes a SQL statement (jdbcTemplate.execute(sql text)).
 * Parameters are used to do ${param_name} replacement in the statement from a text template.
 * This intended for DDL, where parameters are table or index names and not for parameterized queries (which can
 * be done as prepared statements with params).
 */
public abstract class SqlTemplateTasklet implements Tasklet  {

	final JdbcTemplate jdbcTemplate;

	public SqlTemplateTasklet(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Returns the classpath of the SQL template
	 * @return
	 */
	public abstract String getTemplateClassPath();

	/**
	 * Get the fully replaced sql string to be executed.
	 * @return
	 */
	public abstract String getSqlString();

	public String getSqlTemplate() {

		String templateClassPath = getTemplateClassPath();

		Resource template = new ClassPathResource(templateClassPath);

		try (Reader reader = new InputStreamReader(template.getInputStream(), UTF_8)) {
			return FileCopyUtils.copyToString(reader);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	public String getSqlString(Map<String, String> params) {

		final String noComment = getSqlTemplate().replaceAll("(?s)\\/\\*.*?\\*\\/", ""); //remove all /* ... */ comments

		var wrap = new Object() { public String text = noComment; };

		params.entrySet().stream().forEach(e -> {
			wrap.text = wrap.text.replace("${" + e.getKey() + "}", e.getValue());
		});

		return wrap.text;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		String sql = getSqlString();
		jdbcTemplate.execute(sql);

		return RepeatStatus.FINISHED;
	}
}
