<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Update type of component</title>
<style type="text/css">
	div.padding {
		padding: 25px;
	}
</style>
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
	<div class = "padding">
	
	</div>
	<sec:authorize access="hasRole('ADMIN')">
	<div class = "container">
		<h3>Update type of component</h3>
		<form:form action = "" commandName = "updateType" method = "post" class="form-horizontal">
			<div class="form-group">
				<div class = "col-sm-2">
					<label class="control-label">Name</label>
				</div>
				<div class = "col-sm-2">
					<form:input path="name" class = "form-control"/>
				</div>
				<div class = "col-sm-2">
					<form:errors path = "name" />
				</div>
			</div>
			
			<div class="form-group">
				<div class = "col-sm-2">
					<label class="control-label">Value of component</label>
				</div>
				<div class = "col-sm-2">
					<form:select path="valueOfComponent" class = "form-control">
						<form:option value=""></form:option>
						<form:options items = "${valueOfComponent }"/>
					</form:select>
				</div>
				<div class = "col-sm-2">
					<form:errors path="valueOfComponent">Value of component is mandatory</form:errors>
				</div>
			</div>
			<div>
				<input type="submit" value="update" class = "btn btn-class" />
			</div>
		
		
		</form:form>
	</div>
	</sec:authorize>
</body>
</html>