<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Configuration project</title>
</head>
<body>
	
<h3>Log in</h3>
	
	<sec:authorize access="hasRole('ADMIN') or hasRole('WAREHOUSECLERK')">
	<a href="componentList">List of components</a>
	<a href="addComponent">Add new component</a>
	</sec:authorize>
	<sec:authorize access="hasRole('ADMIN')">
	<a href="addTypeOfComponent">Add new type of component</a>
	<a href="typeOfComponentList">List of types of components</a>
	</sec:authorize>
	<sec:authorize access="hasRole('SELLER') or hasRole('ADMIN')">
	<a href="makeConfiguration">Make configuration</a>
	<a href="soldConfigurationList">List of sold configurations</a>
	</sec:authorize>
	<a href="login">Log in</a>
	

</body>
</html>