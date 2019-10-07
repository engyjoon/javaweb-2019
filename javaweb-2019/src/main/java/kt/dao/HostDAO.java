package kt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kt.common.JdbcUtil;
import kt.vo.Host;

public class HostDAO {

	private static final String DB_URL = "jdbc:postgresql://192.168.56.101:5432";
	private static final String DB_NAME = "zabbix";
	private static final String DB_USER = "zabbix";
	private static final String DB_PASS = "zabbix";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public List<Host> selectHostList() {
		ArrayList<Host> listHost = new ArrayList<>();
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(DB_URL + "/" + DB_NAME, DB_USER, DB_PASS);
		
			StringBuffer query = new StringBuffer();
			query.append("select hostid, host, status, available, name, flags ");
			query.append("from hosts where status = '0' and flags = '0' ");
			query.append("order by hostid");
			
			pstmt = conn.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Host host = new Host();
				host.setHostid(rs.getLong("HOSTID"));
				host.setHost(rs.getString("HOST"));
				host.setStatus(rs.getInt("STATUS"));
				host.setAvailable(rs.getInt("AVAILABLE"));
				host.setName(rs.getString("NAME"));
				host.setFlags(rs.getInt("FLAGS"));
				listHost.add(host);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return listHost;
	}
}
