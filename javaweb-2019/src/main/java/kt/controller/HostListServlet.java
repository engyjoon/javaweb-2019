package kt.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kt.vo.Host;

/**
 * Servlet implementation class HostListServlet
 */
@WebServlet("/hostList")
public class HostListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String DB_URL = "jdbc:postgresql://192.168.56.101:5432";
	private static final String DB_NAME = "zabbix";
	private static final String DB_USER = "zabbix";
	private static final String DB_PASS = "zabbix";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HostListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
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
			
			request.setAttribute("listHost", listHost);
			
			RequestDispatcher rd = request.getRequestDispatcher("/test03.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
