<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Buyer Edit</title>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src=<c:url value='/js/app/buyer/buyerForm.js'/>></script>
<script type="text/javascript">
	const CPATH = '${request.contextPath }';
	console.log(CPATH)
</script>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<h5>Form controls</h5>
		</div>
		<div class="card-body">
			<div class="row">

				<form method="post" enctype="multipart/form-data">
					<div class="form-group">
						<input type="text" name="buyerId" value="${buyer.buyerId }" readonly>
					</div>
					<div>
						<label class="form-label" for="buyerImage">제조사전경</label>
						<input type="file" name="buyerImage" id="buyerImage" accept="image/*"/>
						<span class="text-danger">${errors.buyerImage }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerName">거래처이름(*)</label>
						<input 
							type="text" id="buyerName" name="buyerName" class="form-control"
							placeholder="거래처이름(*)"
							value="${buyer.buyerName }"
						><span class="text-danger">${errors.buyerName }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="lprodGu">분류코드(*)</label>
						<select name="lprodGu" id="lprodGu" class="form-select"
							data-init-val="${buyer.lprodGu }"
						>
							<option>선택</option>
						</select>
						<span class="text-danger">${errors.lprodGu }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerBank">주거래은행</label>
						<input 
							type="text" id="buyerBank" name="buyerBank" class="form-control"
							placeholder="주거래은행"
							value="${buyer.buyerBank }"
						><span class="text-danger">${errors.buyerBank }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerBankno">계좌번호</label>
						<input 
							type="text" id="buyerBankno" name="buyerBankno"
							class="form-control" placeholder="계좌번호"
							value="${buyer.buyerBankno }"
						><span class="text-danger">${errors.buyerBankno }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerBankname">계좌주</label>
						<input 
							type="text" id="buyerBankname" name="buyerBankname"
							class="form-control" placeholder="계좌주"
							value="${buyer.buyerBankname }"
						><span class="text-danger">${errors.buyerBankname }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerZip">우편번호</label><input
							type="text" id="buyerZip" name="buyerZip" class="form-control"
							placeholder="우편번호"
							value="${buyer.buyerZip }"
						><span class="text-danger">${errors.buyerZip }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerAdd1">기본주소</label><input
							type="text" id="buyerAdd1" name="buyerAdd1" class="form-control"
							placeholder="기본주소"
							value="${buyer.buyerAdd1 }"
						><span class="text-danger">${errors.buyerAdd1 }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerAdd2">상세주소</label><input
							type="text" id="buyerAdd2" name="buyerAdd2" class="form-control"
							placeholder="상세주소"
							value="${buyer.buyerAdd2 }"
						><span class="text-danger">${errors.buyerAdd2 }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerComtel">회사전화번호</label>
						<input 
							type="text" id="buyerComtel" name="buyerComtel"
							class="form-control" placeholder="회사전화번호"
							value="${buyer.buyerComtel }"
						><span class="text-danger">${errors.buyerComtel }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerFax">팩스번호</label><input
							type="text" id="buyerFax" name="buyerFax" class="form-control"
							placeholder="팩스번호"
							value="${buyer.buyerFax }"
						><span class="text-danger">${errors.buyerFax }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerMail">메일주소</label>
						<input 
							type="email" id="buyerMail" name="buyerMail" class="form-control"
							placeholder="메일주소"
							value="${buyer.buyerMail }"
						><span class="text-danger">${errors.buyerMail }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerCharger">담당자</label>
						<input 
							type="text" id="buyerCharger" name="buyerCharger"
							class="form-control" placeholder="담당자"
							value="${buyer.buyerCharger }"
						><span class="text-danger">${errors.buyerCharger }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerTelext">내선번호</label><input
							type="text" id="buyerTelext" name="buyerTelext"
							maxlength="2"
							class="form-control" placeholder="내선번호"
							value="${buyer.buyerTelext }"
						><span class="text-danger">${errors.buyerTelext }</span>
					</div>
					<div>
						<button type="submit" class="btn btn-primary mb-4">Submit</button>
						<button type="reset" class="btn btn-danger mb-4">Reset</button>
					</div>

					<div class="form-group">
						<label class="form-label" for="exampleFormControlSelect1">Example
							select</label> <select class="form-select" id="exampleFormControlSelect1">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
						</select>
					</div>
					<div class="form-group">
						<label class="form-label" for="exampleFormControlTextarea1">Example
							textarea</label>
						<textarea class="form-control" id="exampleFormControlTextarea1"
							rows="3"></textarea>
					</div>
				</form>

			</div>
		</div>
	</div>
</body>
</html>