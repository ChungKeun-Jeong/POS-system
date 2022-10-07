<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="./bootstrap.min.css">
		<title>제품 입고 조회</title>
	</head>
	<body>
		<nav class="navbar navbar-expand navbar-dark bg-dark">
			<div class="container">
				<a class="navbar-brand" href="./index.jsp">Home</a>
			</div>
		</nav>
		<div class="jumbotron">
			<div class="container">
				<h1 class="display-4">제품 입고 조회</h1>
			</div>
		</div>
		<div class="container">
			<div class="text-right">
				<a href="./manager.jsp" class="btn btn-sm btn-success pull-right">뒤로가기</a>
			</div>
		</div>
		
		<div class="container" style="padding-top: 30px">
			<div>
				<table class="table table-hover">
					<tr>
						<th>날짜</th>
						<th>제품명</th>
						<th>제품 코드</th>
						<th>입고 수량</th>
					</tr>
					
					<c:forEach var="receive" items="${receiveInfo}" varStatus="status">
					<c:set var="name" value="${names[status.index]}" />
					
					<tr>
						<td>${receive.date}</td>
						<td>${name}</td>
						<td>${receive.code}</td>
						<td>${receive.plusQuantity}</td>
					</tr>
					</c:forEach>
					<tr>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</table>
			</div>					
		</div>	
	</body>
</html>