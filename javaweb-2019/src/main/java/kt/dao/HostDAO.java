package kt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kt.common.JdbcUtil;
import kt.vo.Host;

public class HostDAO {
	
	public List<Host> selectHostList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer query = new StringBuffer();
		query.append("select hostid, host, status, available, name, flags ");
		query.append("from hosts where status = '0' and flags = '0' ");
		query.append("order by hostid");
		
		ArrayList<Host> result = new ArrayList<>();
		
		try {
			conn = JdbcUtil.getConnection();
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
				result.add(host);
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
