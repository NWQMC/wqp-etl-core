package gov.acwi.wqp.etl.partition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateRangePartitionedTableLikeTest {


	@Test
	public void getSqlStringTest() {
		CreateRangePartitionedTableLike task = new CreateRangePartitionedTableLike(null, "xyz",
				"mytable", "othertable", "partCol");

		String str = task.getSqlString();

		//Strip out the comments at the top of the file, since they will look the 'correct answer'.  Comments end with "*/".
		str = str.substring(str.indexOf("*/") + 2);

		//create table ${schema_name}.${table_name} (
		//		like ${schema_name}.${like_table_name} including comments including defaults including identity including constraints
		//) PARTITION BY RANGE (${partition_column});

		assertTrue(str.matches("(?si).*create table xyz\\.mytable.*"));
		assertTrue(str.matches("(?si).*\\(\\s*like xyz\\.othertable including comments including defaults including identity including constraints\\s*\\).*"));
		assertTrue(str.matches("(?si).*PARTITION BY RANGE \\(partCol\\)\\s*"));
	}

}
