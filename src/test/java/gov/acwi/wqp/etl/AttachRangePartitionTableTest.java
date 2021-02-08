package gov.acwi.wqp.etl;

import gov.acwi.wqp.etl.partition.AttachRangePartitionTable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AttachRangePartitionTableTest {

	@Test
	public void getSqlStringTest() {
		AttachRangePartitionTable task = new AttachRangePartitionTable(null, "xyz",
				"mytable", "parenttable", "'1999-01-01'", "'2000-7-01'");

		String str = task.getSqlString();

		//Strip out the comments at the top of the file, since they will look the 'correct answer'.  Comments end with "*/".
		str = str.substring(str.indexOf("*/") + 2);

		assertTrue(str.matches("(?si).*alter table\\s+xyz\\.parenttable.*"));
		assertTrue(str.matches("(?si).*for values from\\s+\\('1999-01-01'\\)\\s+to\\s+\\('2000-7-01'\\)\\s*"));

	}

}
