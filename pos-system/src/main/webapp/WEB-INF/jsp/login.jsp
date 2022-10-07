<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="./bootstrap.min.css">
		<title>login</title>
	</head>
	<body>
		<nav class="navbar navbar-expand navbar-dark bg-dark">
			<div class="container">
				<a class="navbar-brand" href="./index.jsp">Home</a>
			</div>
		</nav>
		<div class="jumbotron">
			<div class="container">
				<h1 class="display-4">로그인</h1>
			</div>
		</div>
		<div class="container" align="center">
			<div class="col-md-5 col-md-offset-4">
				<h3 class="form-signin-heading">아이디, 비밀번호를 입력하세요</h3>
				
				<%
					String error = request.getParameter("error");
					if (error != null) {
						out.println("<div class='alert alert-danger'>");
						out.println("아이디와 비밀번호를 확인해주세요");
						out.println("</div>");
					}
				%>
			
				<form class="form-signin" action="log" method="post">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="ID" name='id' required autofocus>
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="Password" name="password" required>
					</div>
					<button type="submit" class="btn btn btn-lg btn-success btn-block">로그인</button>
				</form>
			</div>
		</div>
	</body>
</html>