<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="kt.vo.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
  <link href="${contextPath}/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="${contextPath}/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
	<jsp:include page="/include/sidebar.jsp"></jsp:include>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <jsp:include page="/include/topbar.jsp"></jsp:include>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Host List</h1>
          </div>

          <!-- Content Row -->
          <div class="row">
			<table class="table table-bordered">
			<thead>
			<tr>
			  <th>Hostid</th>
			  <th>Host</th>
			  <th>Status</th>
			  <th>Available</th>
			  <th>Name</th>
			  <th>Flags</th>
			</tr>
			</thead>
			<tbody>
		    <c:forEach items="${listHost}" var="host">
		    <tr>
		      <td><a href="${contextPath}/zabbix/item/list.do?hostid=${host.hostid}">${host.hostid}</a></td>
		      <td>${host.host}</td>
		      <td>${host.status}</td>
		      <td>${host.available}</td>
		      <td>${host.name}</td>
		      <td>${host.flags}</td>
		    </tr>
		    </c:forEach>
		    </tbody>
		    </table>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <jsp:include page="/include/footer.jsp"></jsp:include>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <jsp:include page="/include/btn-scroll-top.jsp"></jsp:include>

  <!-- Logout Modal-->
  <jsp:include page="/include/logout-modal.jsp"></jsp:include>

  <!-- Bootstrap core JavaScript-->
  <script src="${contextPath}/vendor/jquery/jquery.min.js"></script>
  <script src="${contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="${contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="${contextPath}/js/sb-admin-2.min.js"></script>
</body>
</html>