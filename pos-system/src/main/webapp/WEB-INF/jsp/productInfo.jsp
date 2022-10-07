<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="./bootstrap.min.css">
		<title>제품 정보 / 재고</title>
	</head>
	<body>
		<nav class="navbar navbar-expand navbar-dark bg-dark">
			<div class="container">
				<a class="navbar-brand" href="./index.jsp">Home</a>
			</div>
		</nav>
		<div class="jumbotron">
			<div class="container">
				<h1 class="display-4">제품 정보 / 재고</h1>
			</div>
		</div>
		<div class="container">
			<div class="text-right">
				<a href="./manager.jsp" class="btn btn-sm btn-success pull-right">뒤로가기</a>
			</div>
		</div>
		
		<%
			String productDelete = request.getParameter("productDelete");
			String productUpdate = request.getParameter("productUpdate");
			if (productDelete != null) {
		%>
		<script>
			alert("삭제되었습니다.");
		</script>	
		<% 
			}
			else if (productUpdate != null) {	
		%>
		<script>
			alert("수정되었습니다.");
		</script>
		<% 
			}
		%>
		<div class="container">
			<div class="row" align="left">

				<c:forEach var="product" items="${products}" varStatus="status">
				
				<div class="col-md-6">
					<h5><b>${product.name}</b> (${product.code})</h5>
					<br>
					<p>${product.price}원 | 재고 수 : ${product.quantity} 
					<hr>
				</div>
				<div class="col-md-4">
					<form action="updateProductInfo" method="post">
						<button name="update" class="btn btn-secondary" value = "${product.code}"> 정보 수정 &raquo;</button>
					</form>
					<form action="productInfo" method="post">
						<button name="delete" class="btn btn-danger" value = "${product.code}"> 제품 삭제 &raquo;</button>
					</form>					
				</div>
				</c:forEach>
			</div>
		</div>
	</body>
</html>