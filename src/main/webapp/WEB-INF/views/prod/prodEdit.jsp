<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script src=<c:url value='/js/app/prod/prodForm.js'/>></script>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<h5>Form controls</h5>
		</div>
		<div class="card-body">
			<div class="row">

				<form method="post" enctype="application/x-www-form-urlencoded">

					<div class="form-group">
						<label class="form-label" for="prodId">상품코드</label><input
							type="text" id="prodId" name="prodId" class="form-control"
							placeholder="상품코드" readonly value="${prod.prodId }">
					</div>
					<div class="form-group">
						<label class="form-label" for="prodName">상품명</label><input
							type="text" id="prodName" name="prodName" class="form-control"
							placeholder="상품명" value="${prod.prodName }">
					</div>
					<span class="text-danger">${errors.prodName }</span>
					<div class="form-group">
						<label class="form-label" for="lprodGu">분류코드</label> <select
							id="lprodGu" name="lprodGu" class="form-select"
							data-init-val="${prod.lprodGu }">
							<option value="">선택</option>
						</select>
					</div>
					<span class="text-danger">${errors.lprodGu }</span>
					<div class="form-group">
						<label class="form-label" for="buyerId">거래처 코드</label> <select
							id="buyerId" name="buyerId" class="form-select"
							data-init-val="${prod.buyerId }">
							<option value="">선택</option>
						</select>
					</div>
					<span class="text-danger">${errors.buyerId }</span>
					<div class="form-group">
						<label class="form-label" for="prodCost">매입단가</label><input
							type="number" id="prodCost" name="prodCost" class="form-control"
							placeholder="매입단가" value="${prod.prodCost }">
					</div>
					<div class="form-group">
						<label class="form-label" for="prodPrice">매출단가</label><input
							type="number" id="prodPrice" name="prodPrice"
							class="form-control" placeholder="매출단가"
							value="${prod.prodPrice }">
					</div>
					<div class="form-group">
						<label class="form-label" for="prodSale">할인판매단가</label><input
							type="number" id="prodSale" name="prodSale" class="form-control"
							placeholder="할인판매단가" value="${prod.prodSale }">
					</div>
					<div class="form-group">
						<label class="form-label" for="prodOutline">대략적설명</label><input
							type="text" id="prodOutline" name="prodOutline"
							class="form-control" placeholder="대략적설명"
							value="${prod.prodOutline }">
					</div>
					<div class="form-group">
						<label class="form-label" for="prodDetail">자세한설명</label><input
							type="text" id="prodDetail" name="prodDetail"
							class="form-control" placeholder="자세한설명"
							value="${prod.prodDetail }">
					</div>
					<div class="form-group">
						<label class="form-label" for="prodImg">상품사진</label><input
							type="text" id="prodImg" name="prodImg" class="form-control"
							placeholder="상품사진" value="${prod.prodImg }">
					</div>
					<div class="form-group">
						<label class="form-label" for="prodTotalstock">전재고량</label><input
							type="number" id="prodTotalstock" name="prodTotalstock"
							class="form-control" placeholder="전재고량"
							value="${prod.prodTotalstock }">
					</div>
					<!-- 					<div class="form-group"> -->
					<!-- 						<label class="form-label" for="prodInsdate">입고일자</label><input -->
					<!-- 							type="date" id="prodInsdate" name="prodInsdate" -->
					<!-- 							class="form-control" placeholder="입고일자"> -->
					<!-- 					</div> -->
					<div class="form-group">
						<label class="form-label" for="prodProperstock">적정재고</label><input
							type="number" id="prodProperstock" name="prodProperstock"
							class="form-control" placeholder="적정재고"
							value="${prod.prodProperstock }">
					</div>
					<div class="form-group">
						<label class="form-label" for="prodSize">크기</label><input
							type="text" id="prodSize" name="prodSize" class="form-control"
							placeholder="크기" value="${prod.prodSize }">
					</div>
					<div class="form-group">
						<label class="form-label" for="prodColor">색상</label><input
							type="text" id="prodColor" name="prodColor" class="form-control"
							placeholder="색상" value="${prod.prodColor }">
					</div>
					<div class="form-group">
						<label class="form-label" for="prodDelivery">배달사항</label><input
							type="text" id="prodDelivery" name="prodDelivery"
							class="form-control" placeholder="배달사항"
							value="${prod.prodDelivery }">
					</div>
					<div class="form-group">
						<label class="form-label" for="prodUnit">판매단위</label><input
							type="text" id="prodUnit" name="prodUnit" class="form-control"
							placeholder="판매단위" value="${prod.prodUnit }">
					</div>
					<div class="form-group">
						<label class="form-label" for="prodQtyin">포장수량</label><input
							type="number" id="prodQtyin" name="prodQtyin"
							class="form-control" placeholder="포장수량"
							value="${prod.prodQtyin }">
					</div>
					<div class="form-group">
						<label class="form-label" for="prodQtysale">판매단위(수량)</label><input
							type="number" id="prodQtysale" name="prodQtysale"
							class="form-control" placeholder="판매단위(수량)"
							value="${prod.prodQtysale }">
					</div>
					<div class="form-group">
						<label class="form-label" for="prodMileage">마일리지</label><input
							type="number" id="prodMileage" name="prodMileage"
							class="form-control" placeholder="마일리지"
							value="${prod.prodMileage }">
					</div>
					<div>
						<button type="submit" class="btn btn-primary mb-4">Submit</button>
						<button type="reset" class="btn btn-danger mb-4">Reset</button>
					</div>

				</form>

			</div>
		</div>
	</div>
</body>
</html>