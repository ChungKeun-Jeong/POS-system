<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="./bootstrap.min.css">
		<title>index</title>
	</head>
	<body>
		<nav class="navbar navbar-expand navbar-dark bg-dark">
			<div class="container">
				<a class="navbar-brand" href="./index.jsp">Home</a>
			</div>
		</nav>
		
		<div class="jumbotron">
			<div class="container" align="left">
				<h1 class="display-4">편의점 POS 시스템</h1>
			</div>
		</div>
		<div class="container">
			<%
				response.setIntHeader("Refresh", 60);
				Date day = new java.util.Date();
				String am_pm;
				int hour = day.getHours();
				int minute = day.getMinutes();
				if (hour / 12 == 0) {
					am_pm = "AM";
				} else {
					am_pm = "PM";
					hour = hour - 12;
				}
				String CT = hour + ":" + minute + " " + am_pm;
				out.println("현재 시간 : " + CT + "<br>");
			%>
			
			<%
				String complete = request.getParameter("complete");
				if (complete!= null) {
			%>
			<script>
				alert("회원가입이 완료되었습니다.");
			</script>	
			<% 
				}
			%>
			
			<div class="container" align="right" >
				<form action="joinMember" method="post">					
					<button type="submit" class="btn btn-secondary" name="joinMember" value="true"> 회원가입 &raquo;</button> 
				</form>
				<div style="height: 20px"></div>
				<form action="login" method="post">					
					<button type="submit" class="btn btn-secondary" name="login" value="true"> 로그인 &raquo;</button>
				</form>				
			</div>
		</div>
	</body>
</html>