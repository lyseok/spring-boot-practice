<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script src=<c:url value='/js/app/buyer/buyerForm.js'/>></script>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<h5>Form controls</h5>
		</div>
		<div class="card-body">
			<div class="row">
				<c:url value="/buyer/buyerInsert.do" var="url" />
				<form:form modelAttribute="buyer" action="${url }">
					<div class="form-group">
						<label class="form-label" for="buyerName">거래처이름(*)</label>
						<form:input type="text" path="buyerName"
							class="form-control" placeholder="거래처이름(*)"/>
							<form:errors path="buyerName" />
					</div>
					<div class="form-group">
						<label class="form-label" for="lprodGu">분류코드(*)</label>
							<select name="lprodGu" id="lprodGu" path="lprodGu" class="form-select"
								data-init-val="${buyer.lprodGu }">
								<option value="">선택</option>
							</select>
							<form:errors path="lprodGu" />
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerId">거래처 코드</label>
						<form:input type="text" path="buyerId"
							class="form-control" placeholder="거래처 코드" />
							<form:errors path="buyerId" />
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerBank">주거래은행</label>
						<form:input type="text" path="buyerBank"
							class="form-control" placeholder="주거래은행" />
							<form:errors path="buyerBank" />
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerBankno">계좌번호</label>
						<form:input type="text" path="buyerBankno"
							class="form-control" placeholder="계좌번호" />
							<form:errors path="buyerBankno" />
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerBankname">계좌주</label>
						<form:input type="text" path="buyerBankname"
							class="form-control" placeholder="계좌주" />
							<form:errors path="buyerBankname" />
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerZip">우편번호</label>
						<form:input type="text" path="buyerZip"
							class="form-control" placeholder="우편번호" />
							<form:errors path="buyerZip" />
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerAdd1">기본주소</label>
						<form:input type="text" path="buyerAdd1"
							class="form-control" placeholder="기본주소" />
							<form:errors path="buyerAdd1" />
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerAdd2">상세주소</label>
						<form:input type="text" path="buyerAdd2"
							class="form-control" placeholder="상세주소" />
							<form:errors path="buyerAdd2" />
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerComtel">회사전화번호</label>
						<form:input type="text" path="buyerComtel"
							class="form-control" placeholder="회사전화번호" />
							<form:errors path="buyerComtel" />
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerFax">팩스번호</label>
						<form:input type="text" path="buyerFax"
							class="form-control" placeholder="팩스번호" />
							<form:errors path="buyerFax" />
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerMail">메일주소</label>
						<form:input type="email" path="buyerMail"
							class="form-control" placeholder="메일주소" />
							<form:errors path="buyerMail" />
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerCharger">담당자</label>
						<form:input type="text" path="buyerCharger"
							class="form-control" placeholder="담당자" />
							<form:errors path="buyerCharger" />
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerTelext">내선번호</label>
						<form:input type="text" path="buyerTelext"
							maxlength="2" class="form-control" placeholder="내선번호" />
							<form:errors path="buyerTelext" />
					</div>

					<div>
						<button type="submit" class="btn btn-primary mb-4">Submit</button>
						<button type="reset" class="btn btn-danger mb-4">Reset</button>
					</div>

					</form:form>
			</div>
		</div>
	</div>
</body>
</html>