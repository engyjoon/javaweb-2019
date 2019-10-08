package kt.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import kt.vo.Item;

public class TestHistoryDAO {

	HistoryDAO historyDAO = new HistoryDAO();
	
	@Test
	public void testSelectHistoryListByItemFloat() {
		// CPU system time
		Item item = new Item();
		item.setItemid(Long.parseLong("23305"));
		assertNotNull(historyDAO.selectHistoryListByItem(item));
	}
	
	@Test
	public void testSelectHistoryListByItemCharacter() {
		// Host name
		Item item = new Item();
		item.setItemid(Long.parseLong("23307"));
		assertNotNull(historyDAO.selectHistoryListByItem(item));
	}
	
	@Test
	public void testSelectHistoryListByItemInteger() {
		// Number of running processes
		Item item = new Item();
		item.setItemid(Long.parseLong("23291"));
		assertNotNull(historyDAO.selectHistoryListByItem(item));
	}

}
