<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${contextPath}/index.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">SB Admin <sup>2</sup></div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item active">
        <a class="nav-link" href="${contextPath}/index.jsp">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Dashboard</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        Study
      </div>

      <!-- Nav Item - test01 -->
      <li class="nav-item">
        <a class="nav-link" href="test01.jsp">
          <i class="fas fa-fw fa-cog"></i>
          <span>test01</span>
        </a>
      </li>
      
      <!-- Nav Item - test02 -->
      <li class="nav-item">
        <a class="nav-link" href="test02.jsp">
          <i class="fas fa-fw fa-cog"></i>
          <span>test02</span>
        </a>
      </li>
      
      <!-- Nav Item - test03 -->
      <li class="nav-item">
        <a class="nav-link" href="hostList.do">
          <i class="fas fa-fw fa-cog"></i>
          <span>test03</span>
        </a>
      </li>
      
      <!-- Nav Item - test03 -->
      <li class="nav-item">
        <a class="nav-link" href="${contextPath}/zabbix/host/list.do">
          <i class="fas fa-fw fa-cog"></i>
          <span>Host List</span>
        </a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>