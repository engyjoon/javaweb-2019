package kt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kt.common.JdbcUtil;
import kt.vo.Host;
import kt.vo.Item;

public class ItemDAO {

	public List<Item> selectItemListByHost(Host host) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer query = new StringBuffer();
		query.append("select itemid, type, hostid, name, key_, status, value_type from items ");
		query.append("where flags not in('1','2') and hostid = ?");
		
		ArrayList<Item> result = new ArrayList<>();
		
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setLong(1, host.getHostid());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Item item = new Item();
				item.setItemid(rs.getLong("ITEMID"));
				item.setType(rs.getInt("TYPE"));
				item.setHostid(rs.getLong("HOSTID"));
				item.setName(rs.getString("NAME"));
				item.setKey(rs.getString("KEY_"));
				item.setStatus(rs.getInt("STATUS"));
				item.setValueType(rs.getInt("VALUE_TYPE"));
				result.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return result;
	}
}
