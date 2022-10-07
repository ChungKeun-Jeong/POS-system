<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="./bootstrap.min.css">
		<title>제품 수정</title>
	</head>
	<body>
		<nav class="navbar navbar-expand navbar-dark bg-dark">
			<div class="container">
				<a class="navbar-brand" href="./index.jsp">Home</a>
			</div>
		</nav>
		<div class="jumbotron">
			<div class="container">
				<h1 class="display-4">제품 수정</h1>
			</div>
		</div>
		<div class="container">
			<div class="text-right">
				<a href="./productInfo.jsp" class="btn btn-sm btn-success pull-right">뒤로가기</a>
			</div>
			
			<form action="updateProductInfo" class="form-horizontal" method="post" enctype="multipart/form-data">
				<div class="form-group row">
					<label class="col-sm-2">제품 코드</label>
					<div class="col-sm-3">
						<input type="text" name="code" value="${product.code}" readonly class="form-control" >
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2">제품명</label>
					<div class="col-sm-3">
						<input type="text" name="name" value="${product.name}" class="form-control" >
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2">가격</label>
					<div class="col-sm-3">
						<input type="text" name="price" value="${product.price}" class="form-control" >
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2">재고 수</label>
					<div class="col-sm-3">
						<input type="text" name="quantity" value="${product.quantity}" readonly class="form-control" >
					</div>
				</div>
				<div class="form-group row">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">수정</button>
					</div>
				</div>
			</form>
		</div>
	</body> 
</html>