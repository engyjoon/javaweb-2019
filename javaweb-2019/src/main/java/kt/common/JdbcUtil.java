package kt.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcUtil {

	private static final String DB_URL = "jdbc:postgresql://192.168.56.101:5432";
	private static final String DB_NAME = "zabbix";
	private static final String DB_USER = "zabbix";
	private static final String DB_PASS = "zabbix";
	
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(DB_URL+"/"+DB_NAME, DB_USER, DB_PASS);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(Connection conn) {
		try { if(conn != null) conn.close(); } catch(Exception e) { e.printStackTrace(); }
	}
	
	public static void close(PreparedStatement pstmt) {
		try { if(pstmt != null) pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
	}
	
	public static void close(ResultSet rs) {
		try { if(rs != null) rs.close(); } catch(Exception e) { e.printStackTrace(); }
	}
}
