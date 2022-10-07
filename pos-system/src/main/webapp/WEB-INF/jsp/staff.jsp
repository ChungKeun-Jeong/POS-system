<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="./bootstrap.min.css">
		<title>staff</title>
	</head>
	<body>
		<nav class="navbar navbar-expand navbar-dark bg-dark">
			<div class="container">
				<a class="navbar-brand" href="./index.jsp">Home</a>
			</div>
		</nav>
		<div class="jumbotron">
			<div class="container">
				<h1 class="display-4">직원 페이지</h1>
			</div>
		</div>
		<div class="container">
			<div class="text-right">
				<form action="staff" method="post">
					<button name="logout" class="btn btn-sm btn-success pull-right" value="logout">logout</button>
			</div>
		</div>
     			
		<div class="container">
			<%
				String cash = request.getParameter("cash");
				String card = request.getParameter("card");
				if (cash!= null) {
			%>
			<script>
				alert("현금 결제가 완료되었습니다.");
			</script>	
			<% 
				}
				else if (card != null) {		
			%>
			<script>
				alert("카드 결제가 완료되었습니다.");
			</script>	
			<% 
				}
			%>
			
			<div class="row">
				<div class="col">
					<div class="row">	
						<c:forEach var="product" items="${products}" varStatus="status">		            
							<div class="col-6 col-sm-4">								
								<button name="code" class="btn btn-info" value = "${product.code}">
									<h5><b>${product.name}</b></h5><br><br>${product.price}원 | 재고 : ${product.quantity}
								</button>
								<hr>								
							</div>
							<c:if test="${status.index % 3 == 2}"><div class='w-100 d-none d-md-block'></div></c:if>
						</c:forEach>
					</div>
				</div>
				<div class="col-1"></div>
				<div class="col">
					<div class="form-group row" style="padding-top: 30px" style="padding-left: 20px">
						<label class="col-sm-3">제품 검색</label>
						<div class="col-sm-4">
							<input type="text" name="searchProduct" class="form-control" >
						</div>
						<button type="submit" class="btn btn-primary">등록</button>
					</div>
					
					<div style="padding-top: 30px">
						<table class="table table-hover">
							<tr>
								<th>상품</th>
								<th>가격</th>
								<th>수량</th>
								<th>합계</th>
								<th>비고</th>
							</tr>
																		
							<c:forEach var="cart" items="${carts}" varStatus="status">		   
							<c:set var="sum" value="${sum + cart.quantity * cart.price}" />

							<tr>
								<td>${cart.name}</td>
								<td>${cart.price}</td>
								<td>
									<button name="minus" class="badge badge-danger" value="${cart.code}">-</button>
									${cart.quantity}
									<button name="plus" class="badge badge-success" value="${cart.code}">+</button>
								</td>
								<td>${cart.price * cart.quantity}</td>
								<td><button name="delete" class="badge badge-danger" value="${cart.code}">삭제</button></td>							
							</tr>
							</c:forEach>
							<tr>
								<th></th>
								<th></th>
								<th></th>
								<th>총액</th>
								<th>${sum}</th>							
							</tr>										
						</table>
						<div class="row">
							<table width="100%">
								<tr>
									<td align="left"><button name="deleteAll" class="btn btn-danger" value="deleteAll">전체 삭제</button></td>							
									<td align="right"><button name="payCash" class="btn btn-success" value="payCash">현금 결제</button></td>									
									<td align="right"><button name="payCard" class="btn btn-success" value="payCard">카드 결제</button></td>									
								</tr>
							</table>
						</div>
						</form>
					</div>
					
				</div>
			</div>
		</div>
    </body>
</html> 