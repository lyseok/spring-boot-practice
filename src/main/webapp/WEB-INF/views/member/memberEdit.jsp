<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="card my-5">
   <div class="card-body">
      <form:form method="post" modelAttribute="member">
         <div class="form-group">
            <label class="form-label" for="memId">회원번호</label>
            <form:input path="memId" class="form-control" placeholder="회원번호" />
            <form:errors path="memId" />
         </div>
         <div class="form-group">
            <label class="form-label" for="memPassword">비밀번호</label>
            <form:input path="memPassword" class="form-control" placeholder="비밀번호" />
            <form:errors path="memPassword" />
         </div>
         <div class="form-group">
            <label class="form-label" for="memName">회원이름</label>
            <form:input path="memName" class="form-control" placeholder="회원이름" />
            <form:errors path="memName" />
         </div>
         <div class="form-group">
            <label class="form-label" for="memRegno1">주민등록번호 앞 6자리</label>
            <form:input path="memRegno1" class="form-control" placeholder="주민등록번호 앞 6자리" />
            <form:errors path="memRegno1" />
         </div>
         <div class="form-group">
            <label class="form-label" for="memRegno2">주민등록번호 뒤 6자리</label>
            <form:input path="memRegno2" class="form-control" placeholder="주민등록번호 뒤 6자리" />
            <form:errors path="memRegno2" />
         </div>
         <div class="form-group">
            <label class="form-label" for="memBir">생년월일</label>
            <form:input type="localtime-date" path="memBir" class="form-control" placeholder="생년월일" />
            <form:errors path="memBir" />
         </div>
         <div class="form-group">
            <label class="form-label" for="memZip">우편번호</label>
            <form:input path="memZip" class="form-control" placeholder="우편번호" />
            <form:errors path="memZip" />
         </div>
         <div class="form-group">
            <label class="form-label" for="memAdd1">기본주소</label>
            <form:input path="memAdd1" class="form-control" placeholder="기본주소" />
            <form:errors path="memAdd1" />
         </div>
         <div class="form-group">
            <label class="form-label" for="memAdd2">상세주소</label>
            <form:input path="memAdd2" class="form-control" placeholder="상세주소" />
            <form:errors path="memAdd2" />
         </div>
         <div class="form-group">
            <label class="form-label" for="memHometel">집 전화번호</label>
            <form:input path="memHometel" class="form-control" placeholder="집 전화번호" />
            <form:errors path="memHometel" />
         </div>
         <div class="form-group">
            <label class="form-label" for="memComtel">회사 전화번호</label>
            <form:input path="memComtel" class="form-control" placeholder="회사 전화번호" />
            <form:errors path="memComtel" />
         </div>
         <div class="form-group">
            <label class="form-label" for="memHp">핸드폰 번호</label>
            <form:input path="memHp" class="form-control" placeholder="핸드폰 번호" />
            <form:errors path="memHp" />
         </div>
         <div class="form-group">
            <label class="form-label" for="memMail">메일주소</label>
            <form:input type="email" path="memMail" class="form-control" placeholder="메일주소" />
            <form:errors path="memMail" />
         </div>
         <div class="form-group">
            <label class="form-label" for="memJob">직업</label>
            <form:input path="memJob" class="form-control" placeholder="직업" />
            <form:errors path="memJob" />
         </div>
         <div class="form-group">
            <label class="form-label" for="memHobby">취미</label>
            <form:input path="memHobby" class="form-control" placeholder="취미" />
            <form:errors path="memHobby" />
         </div>
         <div class="form-group">
            <label class="form-label" for="memMemorial">기념일종류</label>
            <form:input path="memMemorial" class="form-control" placeholder="기념일종류" />
            <form:errors path="memMemorial" />
         </div>
         <div class="form-group">
            <label class="form-label" for="memMemorialday">해당기념일</label>
            <form:input path="memMemorialday" class="form-control" placeholder="해당기념일" />
            <form:errors path="memMemorialday" />
         </div>
         <div class="d-grid mt-3">
              <button type="submit" class="btn btn-primary">수정</button>
            </div>
      </form:form>
   </div>
</div>
</body>
</html>