<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/WEB-INF/views/start.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title>Add user</title>
<style type="text/css">
	div.padding {
		padding: 25px;
	}
</style>
</head>
<body>
<!-- 	<div align = "center">
		<a href="componentList" class = "btn btn-small btn-default"><span class="glyphicon glyphicon-list"></span>List of components</a>
		<a href="addComponent" class = "btn btn-small btn-default"><span class="glyphicon glyphicon-plus"></span>Add new component</a>
		<a href="typeOfComponentList" class = "btn btn-small btn-default"><span class="glyphicon glyphicon-list"></span>List of types of components</a>
		<a href="addTypeOfComponent" class = "btn btn-small btn-default"><span class="glyphicon glyphicon-plus"></span>Add new type of component</a>
		<a href="makeConfiguration" class = "btn btn-small btn-default"><span class="glyphicon glyphicon-plus"></span>Make configuration</a>
		<a href="soldConfigurationList" class = "btn btn-small btn-default"><span class="glyphicon glyphicon-list"></span>List of sold configurations</a>
		<a href="addUser" class = "btn btn-small btn-default"><span class="glyphicon glyphicon-plus"></span>Add user</a>
		<a href="userList" class = "btn btn-small btn-default"><span class="glyphicon glyphicon-list"></span>User list</a>
	</div> -->
	<div class = "padding">
	
	</div>
	<div class = "container">
	<h3>Add new user</h3>
	<form:form action="addUser" method="post" commandName="addUser" class="form-horizontal">
		<div class="form-group">
			<div class="col-sm-2">
				<label class="control-label">Username</label>
			</div>
			<div class="col-sm-2">
				<form:input path="username" class="form-control" />
			</div>
			<div class="col-sm-2">
				<form:errors path="username" cssStyle="color: red;"></form:errors>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-2">
				<label class="control-label">Password</label>
			</div>
			<div class="col-sm-2">
				<form:input path="password" class="form-control" />
			</div>
			<div class="col-sm-2">
				<form:errors path="password" cssStyle="color: red;"></form:errors>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-2">
				<label class="control-label">First name</label>
			</div>
			<div class="col-sm-2">
				<form:input path="firstName" class="form-control" />
			</div>
			<div class="col-sm-2">
				<form:errors path="firstName" cssStyle="color: red;"></form:errors>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-2">
				<label class="control-label">Last name</label>
			</div>
			<div class="col-sm-2">
				<form:input path="lastName" class="form-control" />
			</div>
			<div class="col-sm-2">
				<form:errors path="lastName" cssStyle="color: red;"></form:errors>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-2">
				<label class="control-label">User role</label>
			</div>
			<div class="col-sm-2">
				<form:select path="userRole" class="form-control">
					<form:option value=""></form:option>
					<form:options items="${userRole }" />
				</form:select>
			</div>
			<div class="col-sm-2">
				<form:errors path="userRole" cssStyle="color: red;"></form:errors>
			</div>
			
			
		</div>
		
		<div>
			<input type = "submit" value = "add" class = "btn btn-default"/>
		</div>

	</form:form>
	</div>
</body>
</html>