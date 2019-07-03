package gov.acwi.wqp.etl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class EncodeUriComponentIT extends BaseFlowIT {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void encodeTest() {
		assertEquals("abcdefg", jdbcTemplate.queryForObject("select encode_uri_component('abcdefg')", String.class));
		assertEquals("a%C3%80b%20c%2Fde%26f%3Fg%E4%B8%8A", jdbcTemplate.queryForObject("select encode_uri_component('aÀb c/de&f?g上')", String.class));
	}
}
