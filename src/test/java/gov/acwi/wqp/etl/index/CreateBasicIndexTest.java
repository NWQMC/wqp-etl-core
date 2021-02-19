package gov.acwi.wqp.etl.index;

import gov.acwi.wqp.etl.CreateTableLike;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateBasicIndexTest {

	@Test
	public void getSqlStringTest() {
		CreateBasicIndex task = new CreateBasicIndex(null, "xyz", "mytable", "myindex", "mycol", 99);

		String str = task.getSqlString();

		//Strip out the comments at the top of the file, since they will look the 'correct answer'.  Comments end with "*/".
		str = str.substring(str.indexOf("*/") + 2);

		assertTrue(str.matches("(?si).*create index if not exists myindex on xyz\\.mytable\\(mycol\\) with \\(fillfactor = 99\\)\\s*"));
	}
}
