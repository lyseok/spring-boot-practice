<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table">
		<tr><th>회원번호</th><td>${member.memId}</td></tr>
		<tr><th>비밀번호</th><td>${member.memPassword}</td></tr>
		<tr><th>회원이름</th><td>${member.memName}</td></tr>
		<tr><th>민증 앞자리</th><td>${member.memRegno1}</td></tr>
		<tr><th>민증 뒷자리</th><td>${member.memRegno2}</td></tr>
		<tr><th>생년월일</th><td>${member.memBir}</td></tr>
		<tr><th>우편번호</th><td>${member.memZip}</td></tr>
		<tr><th>기본주소</th><td>${member.memAdd1}</td></tr>
		<tr><th>상세주소</th><td>${member.memAdd2}</td></tr>
		<tr><th>유선전화</th><td>${member.memHometel}</td></tr>
		<tr><th>회사번호</th><td>${member.memComtel}</td></tr>
		<tr><th>무선전화</th><td>${member.memHp}</td></tr>
		<tr><th>메일주소</th><td>${member.memMail}</td></tr>
		<tr><th>직업</th><td>${member.memJob}</td></tr>
		<tr><th>취미</th><td>${member.memHobby}</td></tr>
		<tr><th>기념일종류</th><td>${member.memMemorial}</td></tr>
		<tr><th>기념일</th><td>${member.memMemorialday}</td></tr>
		<tr><th>마일리지</th><td>${member.memMileage}</td></tr>
		<tr><th>회원탈퇴</th><td>${member.memDelete}</td></tr>
	</table>
	<a href="member/memberUpdate.do" class="btn btn-primary">정보수정</a>
	<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
	  회원탈퇴
	</button>
	
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h1 class="modal-title fs-5" id="exampleModalLabel">회원탈퇴</h1>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	         <form action="/member/memberDelete.do" method="post">
	         	<security:csrfInput/>
	      <div class="modal-body">
	         	password : <input type="password" name="password">
	      </div>
	      <div class="modal-footer">
	        <button type="submit" class="btn btn-primary">회원탈퇴</button>
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	      </div>
	         </form>
	    </div>
	  </div>
	</div>
</body>
</html>