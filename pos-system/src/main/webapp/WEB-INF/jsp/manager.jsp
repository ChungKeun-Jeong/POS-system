<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="./bootstrap.min.css">
		<title>manager</title>
	</head>
	<body>
		<nav class="navbar navbar-expand navbar-dark bg-dark">
			<div class="container">
				<a class="navbar-brand" href="./index.jsp">Home</a>
			</div>
		</nav>
	
		<div class = "jumbotron">
			<div class = "container">
				<h1 class = "display-4">매니저 페이지</h1>
			</div>
		</div>
		<%
			String receive= request.getParameter("receive");
			String newProduct = request.getParameter("new");
			if (receive!= null) {
		%>
		<script>
			alert("제품 입고가 완료되었습니다.");
		</script>	
		<% 
			} 
			else if (newProduct != null) {
		%>
		<script>
			alert("신제품 등록이 완료되었습니다.");
		</script>
		<%
			}
		%>
		<div class="container">
			<div class="text-right">
				<form action="manager" method="post">
					<button name="logout" class="btn btn-sm btn-success pull-right" value="logout">logout</button>
				</form>
			</div>
			<form action="memberInfo" method="post">
				<button class="btn btn-secondary btn-md" > 직원 정보 &raquo;</button>
			</form><p>
			<form action="productInfo" method="post">
				<button class="btn btn-secondary btn-md" > 제품 정보 / 재고 &raquo;</button>
			</form><p>	
			<form action="receiveProduct" method="post">
				<button class="btn btn-secondary btn-md" > 제품 입고 &raquo;</button>
			</form><p>
			<form action="receiveProductInfo" method="post">
				<button class="btn btn-secondary btn-md" > 제품 입고 조회 &raquo;</button>
			</form><p>
			<form action="newProduct" method="post">
				<button class="btn btn-secondary btn-md" > 신제품 등록 &raquo;</button>
			</form><p>
			<form action="statistics" method="post">
				<button class="btn btn-secondary btn-md" > 통계 / 분석 &raquo;</button>
			</form><p>
		</div>
	</body>
</html>