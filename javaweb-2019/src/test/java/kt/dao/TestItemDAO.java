package kt.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import kt.vo.Host;

public class TestItemDAO {

	@Test
	public void testSelectItemListByHost() {
		ItemDAO itemDAO = new ItemDAO();
		Host host = new Host();
		host.setHostid(Long.parseLong("10084"));
		assertNotNull(itemDAO.selectItemListByHost(host));
	}

}
