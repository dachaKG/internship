<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/start.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title>User list</title>
</head>
<body>

	<div class = "padding">
	
	</div>
	<sec:authorize access="hasRole('ADMIN')">
	<div class ="container">
		<h3>User list</h3>
		<table class="table table-hover">
			<tr>
				<th>Id</th>
				<th>Username</th>
				<th>First name</th>
				<th>Last name</th>
				
			</tr>
		<c:forEach items = "${userList }" var = "user">
			<tr>
				<td>${user.id }</td>
				<td>${user.username }</td>
				<td>${user.firstName }</td>
				<td>${user.lastName }</td>
								
			</tr>
		
		</c:forEach>
		</table>
	</div>
	</sec:authorize>
</body>
</html>