package gov.acwi.wqp.etl.partition;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PgDateRangeTablePartTest {

	private static final String BASE = "baseName_";

	@Test
	public void RangeTest() {

		LocalDate date1999 = LocalDate.of(1999, 1, 1);
		LocalDate date2000 = LocalDate.of(2000, 12, 17);


		PgDateRangePart r = new PgDateRangePart(BASE, null, null);

		assertNull(r.getRangeEnd());
		assertNull(r.getRangeStart());
		assertEquals(r.getSqlFormatRangeStart(), PgDateRangePart.MIN_VALUE);
		assertEquals(r.getSqlFormatRangeEnd(), PgDateRangePart.MAX_VALUE);
		assertEquals(BASE + PgDateRangePart.MIN_VALUE + "_to_" + PgDateRangePart.MAX_VALUE, r.getTableName());

		r.setRangeStart(date1999);
		assertEquals(date1999, r.getRangeStart());
		assertEquals("'1999-01-01'", r.getSqlFormatRangeStart()); //formatted for the db
		assertEquals(r.getSqlFormatRangeEnd(), PgDateRangePart.MAX_VALUE);    //This should still be true
		assertEquals(BASE + "1999_01_to_" + PgDateRangePart.MAX_VALUE, r.getTableName());

		r.setRangeEnd(date2000);
		assertEquals(date2000, r.getRangeEnd());
		assertEquals("'2000-12-17'", r.getSqlFormatRangeEnd()); //formatted for the db
		assertEquals(BASE + "1999_01_to_2000_12", r.getTableName());

		r.setRangeStart(null);
		assertEquals(BASE + PgDateRangePart.MIN_VALUE + "_to_2000_12", r.getTableName());
	}
}
