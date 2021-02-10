package gov.acwi.wqp.etl;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.FileCopyUtils;

import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.*;

class CreateTableLikeTest {


	@Test
	public void getSqlStringTest() {
		CreateTableLike task = new CreateTableLike(null, "xyz", "mytable", "othertable");

		String str = task.getSqlString();

		//Strip out the comments at the top of the file, since they will look the 'correct answer'.  Comments end with "*/".
		str = str.substring(str.indexOf("*/") + 2);

		//create table ${wqp_schema_name}.${table_name}
		//    (like ${wqp_schema_name}.${like_table_name} including comments including defaults including identity) with (fillfactor = 100)
		assertTrue(str.matches("(?si).*create table xyz\\.mytable\\s+.*"));
		assertTrue(str.matches("(?si).*\\(like xyz\\.othertable including comments including defaults including identity\\) with \\(fillfactor = 100\\)\\s*"));
	}

}
