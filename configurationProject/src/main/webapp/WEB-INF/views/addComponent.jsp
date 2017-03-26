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
<title>Add new component</title>
<style type="text/css">
	div.padding {
		padding: 25px;
	}
</style>
</head>
<body>

	<div class = "padding">
	
	</div>
	<sec:authorize access="hasRole('WAREHOUSECLERK') or hasRole('ADMIN')">
	<div class = "container">
	<h3>Add new component</h3>
		<form:form action="addComponent" commandName="addComponent" method="post" class="form-horizontal">
			<div class="form-group">
				<div class = "col-sm-2">
					<label class="control-label">Code</label>
				</div>
				<div class = "col-sm-2">
					<form:input path = "code" class = "form-control"/>
				</div>
				<div class = "col-sm-2">
					<form:errors path = "code" cssStyle="color: red;"></form:errors>
				</div>
			</div>
			<div class="form-group">
				<div class ="col-sm-2">
					<label class="control-label">Type of component</label>
				</div>
				<div class ="col-sm-2">
					<form:select path="typeOfComponent.id" class = "form-control">
						<form:option value=""></form:option>
						<form:options items = "${typeOfComponent }" itemValue = "id" itemLabel="name" />
					</form:select>
				</div>
				<div class ="col-sm-2">
					<form:errors path = "typeOfComponent" cssStyle="color: red;"></form:errors>
				</div>
			</div>
			<div class="form-group">
				<div class ="col-sm-2">
					<label class="control-label">Name</label>
				</div>
				<div class ="col-sm-2">
					<form:input path = "name" class = "form-control"/>
				</div>
				<div class ="col-sm-2">
					<form:errors path = "name" cssStyle="color: red;"></form:errors>
				</div>
			</div>
			<div class="form-group">
				<div class ="col-sm-2">
					<label class="control-label">Price</label>
				</div>
				<div class ="col-sm-2">
					<form:input path = "price" class = "form-control"/>
				</div>
				<div class ="col-sm-2">
					<form:errors path = "price" cssStyle="color: red;"></form:errors>
				</div>
			</div>
			<div class="form-group">
				<div class = "col-sm-2">
					<label class="control-label">On stock</label>
				</div>
				<div class = "col-sm-2">
					<form:input path = "stock" class = "form-control"/>
				</div>
				<div class = "col-sm-2">
					<form:errors path = "stock" cssStyle="color: red;" />
				</div>
			</div>
			<div>
				<input type = "submit" value = "add" class = "btn"/>
			</div>
			
		</form:form>
	</div>
	</sec:authorize>
</body>
</html>