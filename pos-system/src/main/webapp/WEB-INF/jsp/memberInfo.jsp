<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="./bootstrap.min.css">
		<title>직원 정보</title>
	</head>
	<body>
		<nav class="navbar navbar-expand navbar-dark bg-dark">
			<div class="container">
				<a class="navbar-brand" href="./index.jsp">Home</a>
			</div>
		</nav>
		<div class="jumbotron">
			<div class="container">
				<h1 class="display-4">직원 정보</h1>
			</div>
		</div>
		<div class="container">
			<div class="text-right">
				<a href="./manager.jsp" class="btn btn-sm btn-success pull-right">뒤로가기</a>
			</div>
		</div>

		<div class="container">
			<div class="form-group row" style="padding-top: 30px">
				<h4 class="col-sm-3" >직원 목록</h4>
			</div>
			<div>
				<table class="table table-hover">
					<tr>
						<th>아이디</th>
						<th>비밀번호</th>
						<th>이름</th>
					</tr>
					
					<c:forEach var="staff" items="${staffs}" varStatus="status">
					<c:set var="staffCount" value="${status.index + 1}" />
					
					<tr>
						<td>${staff.id}</td>
						<td>${staff.password}</td>
						<td>${staff.name}</td>
					</tr>
					</c:forEach>
					<tr>
						<th></th>
						<th></th>
						<th>총 ${staffCount}명</th>
					</tr>
				</table>
			</div>
				
			<div class="form-group row" style="padding-top: 50px">
				<h4 class="col-sm-3" >매니저 목록</h4>
			</div>
			<div>
				<table class="table table-hover">
					<tr>
						<th>아이디</th>
						<th>비밀번호</th>
						<th>이름</th>
					</tr>
					
					<c:forEach var="manager" items="${managers}" varStatus="status">
					<c:set var="managerCount" value="${status.index + 1}" />
					
					<tr>
						<td>${manager.id}</td>
						<td>${manager.password}</td>
						<td>${manager.name}</td>
					</tr>
					</c:forEach>
					<tr>
						<th></th>
						<th></th>
						<th>총 ${managerCount}명</th>
					</tr>
				</table>
			</div>
		</div>	
	</body>
</html>