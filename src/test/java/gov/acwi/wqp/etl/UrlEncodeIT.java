package gov.acwi.wqp.etl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class UrlEncodeIT extends BaseFlowIT {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void encodeTest() {
		assertEquals("abcdefg", jdbcTemplate.queryForObject("select urlencode('abcdefg')", String.class));
		assertEquals("abc%20defg", jdbcTemplate.queryForObject("select urlencode('abc defg')", String.class));
		assertEquals("%C3%80bcdefg", jdbcTemplate.queryForObject("select urlencode('Àbcdefg')", String.class));
		assertEquals("abc%E4%B8%8Aefg", jdbcTemplate.queryForObject("select urlencode('abc上efg')", String.class));
		assertEquals("abcdefg", jdbcTemplate.queryForObject("select urlencode('abc/de&f?g')", String.class));
	}
}
