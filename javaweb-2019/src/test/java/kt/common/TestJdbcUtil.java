package kt.common;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestJdbcUtil {

	@Test
	public void testGetConnection() {
		assertNotNull(JdbcUtil.getConnection());
	}

}
