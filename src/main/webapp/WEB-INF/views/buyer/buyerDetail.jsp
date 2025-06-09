<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Buyer Detail</title>
</head>
<body>
	<main class="container mt-5">
		<h4>제조사 상세 조회</h4>
		<table class="table">
			<tr>
			<th>상품</th>
			<td>
				<table class="table table-bordered">
					<tbody>
					<c:if test="${not empty buyer.prodList }">
						<c:forEach items="${buyer.prodList }" var="prod">
							<tr>
								<th>상품명</th>
								<td>${prod.prodName}</td>
							</tr>
							<tr>
								<th>매입단가</th>
								<td>${prod.prodCost}</td>
							</tr>
							<tr>
								<th>매출단가</th>
								<td>${prod.prodPrice}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty buyer.prodList }">
						<tr>
							<td colspan="3">거래 품목 없음</td>
						</tr>
					</c:if>
					</tbody>
				</table>
			</td>
			</tr>
			<tr>
				<td colspan="2">
				<c:url value="/buyer/buyerUpdate.do" var="updateURL">
					<c:param name="what" value="${buyer.buyerId }"/>
				</c:url>
					<a class="btn btn-primary" href="${updateURL }">수정</a>
				</td>
			</tr>
			<tr>
				<th>기본주소</th>
				<td>${buyer.buyerAdd1}</td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td>${buyer.buyerAdd2}</td>
			</tr>
			<tr>
				<th>회사전화번호</th>
				<td>${buyer.buyerComtel}</td>
			</tr>
			<tr>
				<th>팩스번호</th>
				<td>${buyer.buyerFax}</td>
			</tr>
			<tr>
				<th>메일주소</th>
				<td>${buyer.buyerMail}</td>
			</tr>
			<tr>
				<th>담당자</th>
				<td>${buyer.buyerCharger}</td>
			</tr>
			<tr>
				<th>BUYER_TELEXT</th>
				<td>${buyer.buyerTelext}</td>
			</tr>
			<tr>
				<th>거래처이름(*)</th>
				<td>${buyer.buyerName}</td>
			</tr>
			<tr>
				<th>분류코드(*)</th>
				<td>${buyer.lprodGu}</td>
			</tr>
			<tr>
				<th>주거래은행</th>
				<td>${buyer.buyerBank}</td>
			</tr>
			<tr>
				<th>계좌번호</th>
				<td>${buyer.buyerBankno}</td>
			</tr>
			<tr>
				<th>계좌주</th>
				<td>${buyer.buyerBankname}</td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td>${buyer.buyerZip}</td>
			</tr>

		</table>
	</main>
</body>
</html>