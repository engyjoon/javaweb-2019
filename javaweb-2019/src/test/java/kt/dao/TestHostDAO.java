package kt.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestHostDAO {

	public static HostDAO hostDAO = null;
	
	@BeforeClass
	public static void initialize() {
		hostDAO = new HostDAO();
	}
	
	@Test
	public void testSelectHostList() {
		assertNotNull(hostDAO);
	}

}
