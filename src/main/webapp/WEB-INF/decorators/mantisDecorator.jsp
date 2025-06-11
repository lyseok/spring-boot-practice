<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<!-- [Head] start -->

<head>
  <title>Home | <sitemesh:write property="title" /></title>
  <!-- [Meta] -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="Mantis is made using Bootstrap 5 design framework. Download the free admin template & use it for your project.">
  <meta name="keywords" content="Mantis, Dashboard UI Kit, Bootstrap 5, Admin Template, Admin Dashboard, CRM, CMS, Bootstrap Admin Template">
  <meta name="author" content="CodedThemes">
<%@include file="/WEB-INF/fragments/mantisPreStyle.jsp" %>

<sitemesh:write property="head" />

</head>
<!-- [Head] end -->
<!-- [Body] Start -->

<body data-context-path="${pageContext.request.contextPath }" data-pc-preset="preset-1" data-pc-direction="ltr" data-pc-theme="light">
  <!-- [ Pre-loader ] start -->
<div class="loader-bg">
  <div class="loader-track">
    <div class="loader-fill"></div>
  </div>
</div>
<!-- [ Pre-loader ] End -->
 <!-- [ Sidebar Menu ] start -->
<%@ include file="/WEB-INF/fragments/mantisSidebar.jsp" %>
<!-- [ Sidebar Menu ] end --> 
<!-- [ Header Topbar ] start -->
<%@ include file="/WEB-INF/fragments/mantisHeader.jsp" %>
<!-- [ Header ] end -->



  <!-- [ Main Content ] start -->
  <div class="pc-container">
    <div class="pc-content">
      <sitemesh:write property="body"/>
    </div>
  </div>
  <!-- [ Main Content ] end -->
  <footer class="pc-footer">
    <%@ include file="/WEB-INF/fragments/mantisFooter.jsp" %>
  </footer>

  <%@ include file="/WEB-INF/fragments/mantisPostScript.jsp" %>
  
    

</body>
<!-- [Body] end -->

</html>