<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core"  prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>제조사목록</title>
<script type="text/javascript">
document.addEventListener('DOMContentLoaded', () => {
	
});
</script>
</head>
<body>
<table class="table">
	<thead>	
		<tr>
			<th>제조사명</th>
			<th>분류명</th>
			<th>소재지</th>
			<th>전화번호</th>
			<th>담당사</th>
			<th>거래은행</th>
			<th>거래품목수</th>
		</tr>			
	</thead>
	<tbody>
	 	<c:if test="${not empty buyerList }">
	 		<c:forEach items="${buyerList }" var="buyer">
	 			<tr>
	 				<c:url value="/buyer/buyerDetail.do" var="detailURL">
	 					<c:param name="what" value="${buyer.buyerId }" />
	 				</c:url>
	 				<td>
	 					<a href="${detailURL }">${buyer.buyerName }</a>
	 				</td>
	 				<td>${buyer.lprod.lprodName }</td>
	 				<td>${buyer.buyerAdd1 }</td>
	 				<td>${buyer.buyerComtel }</td>
	 				<td>${buyer.buyerCharger }</td>
	 				<td>${buyer.buyerBank }</td>
	 				<td>${buyer.prodList.size() }</td>
	 			</tr>
	 		</c:forEach>
	 	</c:if>
	 	<c:if test="${empty buyerList }">
	 		<tr>
	 			<td colspan="7">제조사 없음</td>
	 		</tr>	
	 	</c:if>
	</tbody>
</table>
</body>
</html>