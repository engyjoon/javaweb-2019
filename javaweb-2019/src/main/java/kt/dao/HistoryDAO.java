package kt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import kt.common.JdbcUtil;
import kt.vo.History;
import kt.vo.Item;

public class HistoryDAO {

	public static final int INTEGER = 3;
	public static final int FLOAT = 0;
	public static final int CHARACTER = 1;
	
	public List<History> selectHistoryListByItem(Item item) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer query = new StringBuffer();
		query.append("select t2.clock, t2.value ");
		query.append("from items t1, " + getHistoryTable(item.getValueType()) + " t2 ");
		query.append("where t1.itemid = t2.itemid ");
		query.append("  and t2.clock > extract(epoch from date_trunc('second', now() - interval '1 hour')) ");
		query.append("  and t1.itemid = ? ");
		query.append("order by t2.clock");
		
		ArrayList<History> result = new ArrayList<>();
		
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setLong(1, item.getItemid());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				History historyVO = new History();
				
				Instant instant = Instant.ofEpochSecond(rs.getInt("CLOCK"));
				ZonedDateTime zdt = instant.atZone(ZoneId.of("Asia/Seoul"));
				
				historyVO.setClock(zdt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
				historyVO.setValue(getHistoryValue(rs, item.getValueType()).toString());
				
				result.add(historyVO);
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
	
	private String getHistoryTable(int valueType) {
		String result = null;
		
		if(valueType == INTEGER) result = "history_uint";
		else if(valueType == FLOAT) result = "history";
		else if(valueType == CHARACTER) result = "history_str";
		
		return result;
	}
	
	private Object getHistoryValue(ResultSet rs, int valueType) throws SQLException {
		Object result = null;
		
		if(valueType == INTEGER) result = rs.getInt("VALUE");
		else if(valueType == FLOAT) result = rs.getDouble("VALUE");
		else if(valueType == CHARACTER) result = rs.getString("VALUE");
		
		return result;
	}
}
