<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Configuration details</title>
</head>
<body>

	<div align = "center">
	<sec:authorize access="hasRole('ADMIN') or hasRole('WAREHOUSECLERK')">
		<a href="../componentList" class = "btn btn-small btn-default"><span class="glyphicon glyphicon-list"></span>List of components</a>
		<a href="../addComponent" class = "btn btn-small btn-default"><span class="glyphicon glyphicon-plus"></span>Add new component</a>
	</sec:authorize>
	<sec:authorize access="hasRole('ADMIN')">
		<a href="../typeOfComponentList" class = "btn btn-small btn-default"><span class="glyphicon glyphicon-list"></span>List of types of components</a>
		<a href="../addTypeOfComponent" class = "btn btn-small btn-default"><span class="glyphicon glyphicon-plus"></span>Add new type of component</a>
	</sec:authorize>
	<sec:authorize access="hasRole('SELLER') or hasRole('ADMIN')">
		<a href="../makeConfiguration" class = "btn btn-small btn-default"><span class="glyphicon glyphicon-plus"></span>Make configuration</a>
		<a href="../soldConfigurationList" class = "btn btn-small btn-default"><span class="glyphicon glyphicon-list"></span>List of sold configurations</a>
	</sec:authorize>
	<sec:authorize access="hasRole('ADMIN')">
		<a href="../addUser" class = "btn btn-small btn-default"><span class="glyphicon glyphicon-plus"></span>Add user</a>
		<a href="../userList" class = "btn btn-small btn-default"><span class="glyphicon glyphicon-list"></span>User list</a>
	</sec:authorize>
		<a href="../logout" class = "btn btn-small btn-default"><span class="glyphicon glyphicon-list"></span>Log out</a>
	</div>
	
	<div class = "container">
		<h3>Date : ${configurationDetail.date }</h3>
	
		<h3>Total : ${configurationDetail.total }</h3>
		
		<table class="table table-hover">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Type of component</th>
				<th>Price</th>
				
			</tr>
			<c:forEach items="${configurationDetail.component}" var="componentList">
				<tr>
					<td>${componentList.id}</td>
					<td>${componentList.name}</td>
					<td>${componentList.typeOfComponent.name}</td>
					<td>${componentList.price}</td>
									
				</tr>
			</c:forEach>
		</table>
	</div>
	
</body>
</html>