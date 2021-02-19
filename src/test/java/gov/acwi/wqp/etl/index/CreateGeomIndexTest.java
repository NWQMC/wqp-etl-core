package gov.acwi.wqp.etl.index;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateGeomIndexTest {

	@Test
	public void getSqlStringWithNullProjectionTest() {
		CreateGeomIndex task = new CreateGeomIndex(null, "xyz", "mytable", "myindex", "mycol", null);

		String str = task.getSqlString();

		//Strip out the comments at the top of the file, since they will look the 'correct answer'.  Comments end with "*/".
		str = str.substring(str.indexOf("*/") + 2);

		assertTrue(str.matches("(?si).*create index if not exists myindex on xyz\\.mytable using gist \\(mycol\\) with \\(fillfactor = 100\\)\\s*"));
	}

	@Test
	public void getSqlStringWith2163ProjectionTest() {
		CreateGeomIndex task = new CreateGeomIndex(null, "xyz", "mytable", "myindex", "mycol", 2163);

		String str = task.getSqlString();

		//Strip out the comments at the top of the file, since they will look the 'correct answer'.  Comments end with "*/".
		str = str.substring(str.indexOf("*/") + 2);

		assertTrue(str.matches("(?si).*create index if not exists myindex on xyz\\.mytable using gist \\(st_transform\\(mycol, 2163\\)\\) with \\(fillfactor = 100\\)\\s*"));
	}

	@Test
	public void getSqlStringWith2163ProjectionAndPartialFillTest() {
		CreateGeomIndex task = new CreateGeomIndex(null, "xyz", "mytable", "myindex", "mycol", 2163, 99);

		String str = task.getSqlString();

		//Strip out the comments at the top of the file, since they will look the 'correct answer'.  Comments end with "*/".
		str = str.substring(str.indexOf("*/") + 2);

		assertTrue(str.matches("(?si).*create index if not exists myindex on xyz\\.mytable using gist \\(st_transform\\(mycol, 2163\\)\\) with \\(fillfactor = 99\\)\\s*"));
	}
}
