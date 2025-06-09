<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<main class="container">
	<h4>상품 목록 조회</h4>
	<table class="table">
		<thead>
			<tr>
				<th>상품명</th>
				<th>상품분류</th>
				<th>제조사</th>
				<th>구매가</th>
				<th>판매가</th>
				<th>요약정보</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty prodList }">
				<tr>
					<td colspan="6">제품이 존재하지 않습니다</td>
				</tr>		
			</c:if>
			<c:if test="${not empty prodList }">
				<c:forEach items="${prodList }" var="prod">
				<c:url value="/prod/prodDetail.do" var="detailURL">
					<c:param name="what" value="${prod.prodId }"></c:param>
				</c:url>
					<tr>
						<td>
							<a href="${detailURL }">${prod.prodName }</a>
						</td>
						<td>${prod.lprodGu }</td>
						<td>${prod.buyerId }</td>
						<td>${prod.prodCost }</td>
						<td>${prod.prodPrice }</td>
						<td>${prod.prodOutline }</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</main>

</body>
</html>