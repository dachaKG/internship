<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/WEB-INF/views/start.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title>Login</title>
</head>
<body>

	<h3>Log in</h3>

	<a href="componentList">List of components</a>
	<a href="addComponent">Add new component</a>
	<a href="addTypeOfComponent">Add new type of component</a>
	<a href="typeOfComponentList">List of types of components</a>
	<a href="makeConfiguration">Make configuration</a>
	<a href="soldConfigurationList">List of sold configurations</a>
	
	<div class = "container">
	<form action="j_spring_security_check" method="post"
		class="form-horizontal">
		<%-- <c:if test = "${not empty error }">
			<script>
				alert("The username or password is incorrect");
			</script>
		</c:if> --%>
		
		<div class="form-group">
			<div class="col-sm-2">
				<label class="control-label">Username</label>
			</div>
			<div class="col-sm-2">
				<input type = "text" name="j_username" class="form-control" />
			</div>
			
		</div>
		<div class="form-group">
			<div class="col-sm-2">
				<label class="control-label">Password</label>
			</div>
			<div class="col-sm-2">
				<input type = "text" name="j_password" class="form-control" />
			</div>
			
		</div>

		<div>
			<input type="submit" value="login" class="btn btn-class" />
		</div>
	</form>
	
	<font color="red">
		<span>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
	</font>
	</div>
</body>
</html>