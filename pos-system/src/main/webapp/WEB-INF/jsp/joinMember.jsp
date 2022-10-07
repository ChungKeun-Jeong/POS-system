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
				<h1 class = "display-4">회원 가입</h1>
			</div>
		</div>
				
		<div class="container">
			<div class="text-right">
				<a href="./" class="btn btn-sm btn-success pull-right">뒤로가기</a>
			</div>
			<form class="form-horizontal" action="join" method="post" enctype="multipart/form-data">
				<%
					String idError = request.getParameter("idError");
					String positionError = request.getParameter("positionError");
					if (idError != null) {
						out.println("<div class='form-group row'>");
						out.println("<div class='alert alert-danger'>");
						out.println("이미 존재하는 아이디입니다.");
						out.println("</div>");
						out.println("</div>");
					}
					else if (positionError != null) {
						out.println("<div class='form-group row'>");
						out.println("<div class='alert alert-danger'>");
						out.println("직책을 매니저 / 직원 중에서 입력해주세요.");
						out.println("</div>");
						out.println("</div>");
					}
				%>
				<div class="form-group row">
					<label class="col-sm-2">아이디</label>
					<div class="col-sm-3">
						<input type="text" name="id" class="form-control" >
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2">비밀번호</label>
					<div class="col-sm-3">
						<input type="text" name="password" class="form-control" >
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2">이름</label>
					<div class="col-sm-3">
						<input type="text" name="name" class="form-control" >
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2">직책</label>
					<div class="col-sm-3">
						<input type="text" name="position" class="form-control" >
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