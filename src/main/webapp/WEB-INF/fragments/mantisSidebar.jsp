<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="pc-sidebar">
  <div class="navbar-wrapper">
    <div class="m-header">
      <a href="${pageContext.request.contextPath }" class="b-brand text-primary">
        <!-- ========   Change your logo from here   ============ -->
        <img src="${pageContext.request.contextPath }/resources/dist/assets/images/logo-dark.svg" class="img-fluid logo-lg" alt="logo">
      </a>
    </div>
    <div class="navbar-content">
      <ul class="pc-navbar">
        <li class="pc-item">
          <a href="${pageContext.request.contextPath }" class="pc-link">
            <span class="pc-micon"><i class="ti ti-dashboard"></i></span>
            <span class="pc-mtext">Dashboard</span>
          </a>
        </li>

        <li class="pc-item pc-caption">
          <label>상품관리</label>
          <i class="ti ti-dashboard"></i>
        </li>
        <li class="pc-item">
          <a href="${pageContext.request.contextPath }/prod/prodList.do" class="pc-link">
            <span class="pc-micon"><i class="ti ti-typography"></i></span>
            <span class="pc-mtext">상품목록</span>
          </a>
        </li>
        <li class="pc-item">
          <a href="${pageContext.request.contextPath }/prod/prodInsert.do" class="pc-link">
            <span class="pc-micon"><i class="ti ti-typography"></i></span>
            <span class="pc-mtext">신규 상품 등록</span>
          </a>
        </li>
        
        <li class="pc-item pc-caption">
          <label>제조사관리</label>
          <i class="ti ti-dashboard"></i>
        </li>
        <li class="pc-item">
          <a href="${pageContext.request.contextPath }/buyer/buyerList.do" class="pc-link">
            <span class="pc-micon"><i class="ti ti-typography"></i></span>
            <span class="pc-mtext">제조사목록</span>
          </a>
        </li>
        <li class="pc-item">
          <a href="${pageContext.request.contextPath }/buyer/buyerInsert.do" class="pc-link">
            <span class="pc-micon"><i class="ti ti-typography"></i></span>
            <span class="pc-mtext">신규 제조사 등록</span>
          </a>
        </li>
       
        <li class="pc-item pc-caption">
          <label>Pages</label>
          <i class="ti ti-news"></i>
        </li>
        <li class="pc-item">
          <a href="${pageContext.request.contextPath }/login/loginForm.jsp" class="pc-link">
            <span class="pc-micon"><i class="ti ti-lock"></i></span>
            <span class="pc-mtext">Login</span>
          </a>
        </li>
        <li class="pc-item">
          <a href="../pages/register.html" class="pc-link">
            <span class="pc-micon"><i class="ti ti-user-plus"></i></span>
            <span class="pc-mtext">Register</span>
          </a>
        </li>

    
      </ul>
      
    </div>
  </div>
</nav>