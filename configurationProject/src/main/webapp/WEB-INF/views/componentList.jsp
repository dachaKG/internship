<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ include file="/WEB-INF/views/start.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Component list</title>
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
		<h3>Component list</h3>
		<form:form action = "componentList"  method="get" commandName = "filterComponent" class="form-horizontal">
			<div class="form-group">
				<div class = "col-sm-2">
					<label class="control-label">Type of component</label>
				</div>
				<div class = "col-sm-2">
					<form:select path="id" class = "form-control">
						<form:option value="0"> --SELECT-- </form:option>
	   					<form:options items="${typeOfComponent}" itemValue = "id" itemLabel="name" ></form:options>
					</form:select>
				</div>
				
				<div class = "col-sm-2">
					<input type="submit" value="filter" class = "btn btn-class"/>
				</div>
			</div>
		</form:form>
			
	
		<table class="table table-hover">
			<tr>
				<th><c:url value="componentList" var="url">
		                    <c:param name="sorter" value="id/asc"/>
		                    <c:if test = "${not empty typeFilter }">
		                    	<c:param name="id" value="${typeFilter }"/>
		                    </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-down"></span></a>
		            Id
		            <c:url value="componentList" var="url">
		                    <c:param name="sorter" value="id/desc"/>
		                    <c:if test = "${not empty typeFilter }">
		                    	<c:param name="id" value="${typeFilter }"/>
		                    </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-up"></span></a>
		            </th>
				<th><c:url value="componentList" var="url">
		                    <c:param name="sorter" value="code/asc"/>
		                    <c:if test = "${not empty typeFilter }">
		                    	<c:param name="id" value="${typeFilter }"/>
		                    </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-down"></span></a>
		            Code
		            <c:url value="componentList" var="url">
		                   <c:param name="sorter" value="code/desc"/>
		                    <c:if test = "${not empty typeFilter }">
		                    	<c:param name="id" value="${typeFilter }"/>
		                    </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-up"></span></a>
				</th>
				<th>Type of component</th>
				<th><c:url value="componentList" var="url">
		                    <c:param name="sorter" value="name/asc"/>
		                    <c:if test = "${not empty typeFilter }">
		                    	<c:param name="id" value="${typeFilter }"/>
		                    </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-down"></span></a>
		            Name
		            <c:url value="componentList" var="url">
		                    <c:param name="sorter" value="name/desc"/>
		                    <c:if test = "${not empty typeFilter }">
		                    	<c:param name="id" value="${typeFilter }"/>
		                    </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-up"></span></a></th>
				<th><c:url value="componentList" var="url">
		                    <c:param name="sorter" value="price/asc"/>
		                    <c:if test = "${not empty typeFilter }">
		                    	<c:param name="id" value="${typeFilter }"/>
		                    </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-down"></span></a>
		            Price
		            <c:url value="componentList" var="url">
		                    <c:param name="sorter" value="price/desc"/>
		                    <c:if test = "${not empty typeFilter }">
		                    	<c:param name="id" value="${typeFilter }"/>
		                    </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-up"></span></a>
		        </th>
				<th><c:url value="componentList" var="url">
		                    <c:param name="sorter" value="stock/asc"/>
		                    <c:if test = "${not empty typeFilter }">
		                    	<c:param name="id" value="${typeFilter }"/>
		                    </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-down"></span></a>
		            On stock
		            <c:url value="componentList" var="url">
		                    <c:param name="sorter" value="stock/desc"/>
		                    <c:if test = "${not empty typeFilter }">
		                    	<c:param name="id" value="${typeFilter }"/>
		                    </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-up"></span></a>
		        </th>
				<th></th>
				<th></th>
				
			</tr>
			<c:forEach items="${pageListHolder.pageList}" var="component">
				<tr>
					<td>${component.id}</td>
					<td>${component.code}</td>
					<td>${component.typeOfComponent.name}</td>
					<td>${component.name}</td>
					<td>${component.price}</td>
					<td>${component.stock}</td>
					<form:form action = "updateComponent/${component.id}" commandName = "updateComponent" method="get">
					<td><input type="submit" value="update" class = "btn btn-info" /></td>
					</form:form>
					<%-- <form:form action = "componentList" commandName = "removeComponent" method="get">
					<td><input type="submit" value="remove" class = "btn btn-danger" /></td>
					</form:form> --%>
					<td>
						<c:url value="componentList" var="url">
		                    <c:param name="delete" value="${component.id}"/>
		                    <c:if test = "${not empty page }">
					           	<c:param name="page" value="${page+1 }"/>
					        </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />' class="btn btn-danger">Remove</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div>
		
			<c:url value="componentList" var="prev">
		        <c:param name="page" value="1"/>
		        <c:if test = "${not empty sorter }">
		       		<c:param name="sorter" value="${sorter}"/>
		       	</c:if>
		       	<c:if test = "${not empty typeFilter }">
		           	<c:param name="id" value="${typeFilter }"/>
		        </c:if>
		    </c:url>
		     <c:if test="${page > 1}">
		        <a class = "btn btn-small btn-default" href="<c:out value="${prev}" />" class="pn prev"><span class="glyphicon glyphicon-fast-backward"></span></a>
		    </c:if>
		    
		    
			<c:url value="componentList" var="prev">
		        <c:param name="page" value="${page}"/>
		        <c:if test = "${not empty sorter }">
		       		<c:param name="sorter" value="${sorter}"/>
		       	</c:if>
		       	<c:if test = "${not empty typeFilter }">
		           	<c:param name="id" value="${typeFilter }"/>
		        </c:if>
		    </c:url>
		    <c:if test="${page > 0}">
		        <a class = "btn btn-small btn-default" href="<c:out value="${prev}" />" class="pn prev"><span class="glyphicon glyphicon-step-backward"></span></a>
		    </c:if>
		    
		    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
		        <c:choose>
		            <c:when test="${page+1 == i.index}">
		                <span>${i.index}</span>
		            </c:when>
		            <c:otherwise>
		                <c:url value="componentList" var="url">
		                    <c:param name="page" value="${i.index}"/>
		                    <c:if test = "${not empty sorter }">
					       		<c:param name="sorter" value="${sorter}"/>
					       	</c:if>
					       	<c:if test = "${not empty typeFilter }">
					           	<c:param name="id" value="${typeFilter }"/>
					        </c:if>
		                </c:url>
		                <a href='<c:out value="${url}" />'>${i.index}</a>
		            </c:otherwise>
		        </c:choose>
		    </c:forEach>
		    
		    
		    <c:url value="componentList" var="next">
		        <c:param name="page" value="${page + 2}"/>
		        <c:if test = "${not empty sorter }">
		       		<c:param name="sorter" value="${sorter}"/>
		       	</c:if>
		       	<c:if test = "${not empty typeFilter }">
		           	<c:param name="id" value="${typeFilter }"/>
		        </c:if>
		    </c:url>
		    <c:if test="${page + 1 < maxPages}">
		        <a class = "btn btn-small btn-default" href='<c:out value="${next}" />' ><span class="glyphicon glyphicon-step-forward"></span></a>
		    </c:if>
		    
		    <c:url value="componentList" var="next">
		        <c:param name="page" value="${maxPages}"/>
		        <c:if test = "${not empty sorter }">
		       		<c:param name="sorter" value="${sorter}"/>
		       	</c:if>
		       	<c:if test = "${not empty typeFilter }">
		           	<c:param name="id" value="${typeFilter }"/>
		        </c:if>
		    </c:url>
		    <c:if test="${page + 2 < maxPages}">
		        <a class = "btn btn-small btn-default" href='<c:out value="${next}" />'><span class="glyphicon glyphicon-fast-forward"></span></a>
		    </c:if>
		
		
		</div>
	</div>
	</sec:authorize>
	
	
	
		<c:if test = "${not empty checkStock }">
			<script>
				alert("you can't remove component");
			</script>
		
		</c:if>
		
	
</body>
</html>