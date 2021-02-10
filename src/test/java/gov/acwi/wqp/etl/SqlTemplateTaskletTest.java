package gov.acwi.wqp.etl;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SqlTemplateTaskletTest {



	@Test
	public void getSqlTemplateTest() {
		TestClass test = new TestClass("xyz", "mytable", "othertable");

		String str = test.getSqlTemplate();

		assertTrue(str.matches("(?si).*create table.*"));
	}

	@Test
	public void getSqlStringTest() {
		TestClass test = new TestClass("xyz", "mytable", "othertable");

		String str = test.getSqlString();

		//Strip out the comments at the top of the file, since they will look the 'correct answer'.  Comments end with "*/".
		str = str.substring(str.indexOf("*/") + 2);

		//create table ${wqp_schema_name}.${table_name}
		//    (like ${wqp_schema_name}.${like_table_name} including comments) with (fillfactor = 100)
		assertTrue(str.matches("(?si).*create table\\s+xyz\\.mytable\\s+.*"));
		assertTrue(str.matches("(?si).*\\(like xyz\\.othertable including comments\\) with \\(fillfactor = 100\\)\\s*"));

	}

	class TestClass extends SqlTemplateTasklet {

		private final String wqpSchemaName;
		private final String tableName;
		private final String likeTableName;

		public TestClass(String wqpSchemaName, String tableName, String likeTableName) {

			super(null);

			this.wqpSchemaName = wqpSchemaName;
			this.tableName = tableName;
			this.likeTableName = likeTableName;
		}


		@Override
		public String getTemplateClassPath() {
			return "/gov/acwi/wqp/etl/SqlTemplateTest.sql";
		}

		public String getSqlString() {

			HashMap<String, String> params = new HashMap();
			params.put("wqp_schema_name", wqpSchemaName);
			params.put("table_name", tableName);
			params.put("like_table_name", likeTableName);

			return super.getSqlString(params);
		}
	}

}
