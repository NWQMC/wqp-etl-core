package gov.acwi.wqp.etl;

import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class DropConstraintTest {


	@Test
	public void getSqlStringTest() {
		DropConstraint task = new DropConstraint(null, "xyz", "mytable", "myconstraint");

		String str = task.getSqlString();
		assertThat(str, MatchesPattern.matchesPattern("(?si)\\s*alter table if exists xyz\\.mytable drop constraint if exists myconstraint\\s*"));
	}

}
