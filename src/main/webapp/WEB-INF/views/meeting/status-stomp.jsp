<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/@stomp/stompjs@5.0.0/bundles/stomp.umd.min.js"></script>
<script src="/js/app/meeting/status-stomp.js"></script>
</head>
<body>
<h4>Stomp 예제</h4>
<button id="sendbtn">전송</button>
<input type="text" id="sender" placeholder="발행자">
<div id="contentarea">
	${tableContent }
</div>

</body>
</html>