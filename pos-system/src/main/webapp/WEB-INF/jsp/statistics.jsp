<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="./bootstrap.min.css">
		<title>통계 / 분석</title>
	</head>
	<body>
		<nav class="navbar navbar-expand navbar-dark bg-dark">
			<div class="container">
				<a class="navbar-brand" href="./index.jsp">Home</a>
			</div>
		</nav>
		<div class="jumbotron">
			<div class="container">
				<h1 class="display-4">통계 / 분석</h1>
			</div>
		</div>
		<div class="container">
			<div class="text-right">
				<a href="./manager.jsp" class="btn btn-sm btn-success pull-right">뒤로가기</a>
			</div>
		</div>
		<div class="container">
			<div class="form-group row" style="padding-top: 30px">
				<h4 class="col-sm-3" >오늘 매출 통계</h4>
			</div>
			<div>
				<table class="table table-hover">
					<tr>
						<th>제품</th>
						<th>수량</th>
						<th>합계</th>
					</tr>
						
					<c:forEach var="date" items="${date}" varStatus="status">
					<c:if test="${date == today}">
					
					<tr>
						<td>${name[status.index]}</td>
						<td>${quantity[status.index]}</td>
						<td>${sum[status.index]}</td>
					</tr>
					</c:if>
					</c:forEach>
					<tr>
						<th>${today}</th>
						<th>총 매출 : ${todayTotalSales}</th>
						<th>최다 판매 제품 : ${topSellingName}, ${topSellingQuantity}개 판매</th>
					</tr>
				</table>
			</div>
				
			<div class="form-group row" style="padding-top: 50px">
				<h4 class="col-sm-3" >이번 달 매출 통계</h4>
			</div>
			<div>
				<table class="table table-hover">
					<tr>
						<th>제품</th>
						<th>수량</th>
						<th>합계</th>
					</tr>
					
					<c:forEach var="monthName" items="${monthName}" varStatus="status">
					
					<tr>
						<td>${monthName}</td>
						<td>${monthQuantity[status.index]}</td>
						<td>${monthSum[status.index]}</td>
					</tr>
					</c:forEach>
					<tr>
						<th>${month}월</th>
						<th>총 매출 : ${monthTotalSales}</th>
						<th>최다 판매 제품 : ${monthTopSellingName}, ${monthTopSellingQuantity}개 판매</th>
					</tr>
				</table>
			</div>
		</div>	
	</body>
</html>