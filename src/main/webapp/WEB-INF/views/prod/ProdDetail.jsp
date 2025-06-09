<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>상품상세</title>
<script type="text/javascript">
	// 더미 주석
</script>
</head>
<body>

<main class="container mt-5">
	<h4 class="">상품 상세 조회</h4>
	<table class="table table-bordered">
		<tr>
			<td colspan="2">
				<c:url value="/prod/prodUpdate.do" var="prodURL">
					<c:param name="what" value="${prod.prodId }" />
				</c:url>
				<a href="${prodURL }" class="btn btn-primary mb-4">상품 수정</a>					
			</td>
		</tr>
		
		<tr>
			<th>상품코드</th>
			<td>${prod.prodId}</td>
		</tr>
		<tr>
			<th>상품명</th>
			<td>${prod.prodName}</td>
		</tr>
		<tr>
			<th>상품분류</th>
			<td>${prod.lprodGu}</td>
		</tr>
		<tr>
			<th>제조사</th>
			<td>
				<table>
					<tbody>
					<c:url value="/buyer/buyerDetail.do" var="buyer" >
						<c:param name="what" value="${prod.buyer.buyerId }"/>
					</c:url>
						<tr>
							<th>제조사명</th>
							<td>
								<a href="${buyer }">${prod.buyer.buyerName}</a>
							</td>
						</tr>
						<tr>
							<th>소재지</th>
							<td>${prod.buyer.buyerAdd1}</td>
						<tr>
						</tr>
						<tr>
							<th>전화번호</th>
							<td>${prod.buyer.buyerComtel}</td>
						</tr>
						<tr>
							<th>담당자</th>
							<td>${prod.buyer.buyerCharger}</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<th>매입단가</th>
			<td>${prod.prodCost}</td>
		</tr>
		<tr>
			<th>매출단가</th>
			<td>${prod.prodPrice}</td>
		</tr>
		<tr>
			<th>할인판매단가</th>
			<td>${prod.prodSale}</td>
		</tr>
		<tr>
			<th>매충 설명</th>
			<td>${prod.prodOutline}</td>
		</tr>
		<tr>
			<th>자세한설명</th>
			<td>${prod.prodDetail}</td>
		</tr>
		<tr>
			<th>상품이미지</th>
			<td>
			<c:if test="${not empty prod.prodImg }">
				<img src="/images/${prod.prodImg}" style="width: 500px"/>
			</c:if>
			</td>
		</tr>
		<tr>
			<th>전재고량</th>
			<td>${prod.prodTotalstock}</td>
		</tr>
		<tr>
			<th>입고일자</th>
			<td>${prod.prodInsdate}</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>${prod.prodProperstock}</td>
		</tr>
		<tr>
			<th>크기</th>
			<td>${prod.prodSize}</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>${prod.prodColor}</td>
		</tr>
		<tr>
			<th>배송주의사항</th>
			<td>${prod.prodDelivery}</td>
		</tr>
		<tr>
			<th>판매단위</th>
			<td>${prod.prodUnit}</td>
		</tr>
		<tr>
			<th>포장수량</th>
			<td>${prod.prodQtyin}</td>
		</tr>
		<tr>
			<th>판매단위수량</th>
			<td>${prod.prodQtysale}</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${prod.prodMileage}</td>
		</tr>
	</table>
</main>

</body>
</html>