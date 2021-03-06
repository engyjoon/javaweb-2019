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
import kt.vo.Graph;
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
				History history = new History();
				
				Instant instant = Instant.ofEpochSecond(rs.getInt("CLOCK"));
				ZonedDateTime zdt = instant.atZone(ZoneId.of("Asia/Seoul"));
				
				history.setClock(zdt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
				history.setValue(getHistoryValue(rs, item.getValueType()).toString());
				
				result.add(history);
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
	
	public List<Graph> selectJsonHistoryListByItem(Item item) {
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
		
		ArrayList<Graph> result = new ArrayList<>();
		
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setLong(1, item.getItemid());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Graph graph = new Graph();
				
				Instant instant = Instant.ofEpochSecond(rs.getInt("CLOCK"));
				ZonedDateTime zdt = instant.atZone(ZoneId.of("Asia/Seoul"));
				
				graph.setX(zdt.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
				graph.setY(getHistoryValue(rs, item.getValueType()).toString());
				
				result.add(graph);
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
		
		if(valueType == INTEGER) result = rs.getBigDecimal("VALUE");
		else if(valueType == FLOAT) result = rs.getDouble("VALUE");
		else if(valueType == CHARACTER) result = rs.getString("VALUE");
		
		return result;
	}
}
