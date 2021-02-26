package gov.acwi.wqp.etl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateTableLikeTest {


	@Test
	public void getSqlStringTest() {
		CreateTableLike task = new CreateTableLike(null, "xyz", "mytable", "othertable");

		String str = task.getSqlString();

		//create table ${wqp_schema_name}.${table_name}
		//    (like ${wqp_schema_name}.${like_table_name} including comments including defaults including identity) with (fillfactor = 100)
		assertTrue(str.matches("(?si).*create table xyz\\.mytable\\s+.*"));
		assertTrue(str.matches("(?si).*\\(like xyz\\.othertable including comments including defaults including identity\\) with \\(fillfactor = 100\\)\\s*"));
	}

}
