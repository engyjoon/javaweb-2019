<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="kt.vo.*" %>

<%!
	private static final String DB_URL = "jdbc:postgresql://192.168.56.101:5432";
	private static final String DB_NAME = "zabbix";
	private static final String DB_USER = "zabbix";
	private static final String DB_PASS = "zabbix";
	
	ArrayList<Host> listHost = new ArrayList<>();
%>

<%
	Class.forName("org.postgresql.Driver");

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

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
		try{
			rs.close();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
%>

<!DOCTYPE html>
<html>
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Dashboard</title>

  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
	<jsp:include page="include/sidebar.jsp"></jsp:include>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <jsp:include page="include/topbar.jsp"></jsp:include>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">test01</h1>
          </div>

          <!-- Content Row -->
          <div class="row">
<%
	for(Host host : listHost) {
		out.print(host.getHostid() + ", " + host.getHost() + ", " + host.getStatus() + ", " + host.getAvailable() + ", " + host.getName() + ", " + host.getFlags() + "<br>");
	}
%>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <jsp:include page="include/footer.jsp"></jsp:include>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <jsp:include page="include/btn-scroll-top.jsp"></jsp:include>

  <!-- Logout Modal-->
  <jsp:include page="include/logout-modal.jsp"></jsp:include>

  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="vendor/chart.js/Chart.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/chart-area-demo.js"></script>
  <script src="js/demo/chart-pie-demo.js"></script>

</body>
</html>