<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="./bootstrap.min.css">
		<title>joinMember</title>
	</head>
	<body>
		<nav class="navbar navbar-expand navbar-dark bg-dark">
			<div class="container">
				<a class="navbar-brand" href="./index.jsp">Home</a>
			</div>
		</nav>
		<div class = "jumbotron">
			<div class = "container">
				<h1 class = "display-4">제품 입고</h1>
			</div>
		</div>
		<div class="container">
			<div class="text-right">
				<a href="manager.jsp" class="btn btn-sm btn-success pull-right">뒤로가기</a>
			</div>
			
			<form action="receiveProduct" class="form-horizontal" method="post" enctype="multipart/form-data">
				<%
					String error = request.getParameter("error");
					if (error != null) {
						out.println("<div class='alert alert-danger'>");
						out.println("존재하지 않는 제품 코드입니다.");
						out.println("</div>");
					}
				%>
				<div class="form-group row">
					<label class="col-sm-2">제품 코드</label>
					<div class="col-sm-3">
						<input type="text" name="code" class="form-control" >
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2">입고 수량</label>
					<div class="col-sm-3">
						<input type="text" name="quantity" class="form-control" >
					</div>
				</div>
				<div class="form-group row">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">등록</button>	
					</div>
				</div>
			</form>
		</div>
	</body> 
</html>