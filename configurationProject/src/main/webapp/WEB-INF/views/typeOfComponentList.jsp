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
<title>Type of component list</title>
<style type="text/css">
	div.padding {
		padding: 25px;
	}
</style>
</head>
<body>

	<div class = "padding">
	
	</div>
	<sec:authorize access="hasRole('ADMIN')">
	<div class = "container">
	<h3>Type of component list</h3>
		<table class="table table-hover">
			<tr>
				<th>Id</th>
				<th><c:url value="typeOfComponentList" var="url">
		                    <c:param name="sorter" value="name/asc"/>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-down"></span></a>
		            Name
		            <c:url value="typeOfComponentList" var="url">
		                    <c:param name="sorter" value="name/desc"/>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-up"></span></a></th>
				<th><c:url value="typeOfComponentList" var="url">
		                    <c:param name="sorter" value="valueOfComponent/asc"/>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-down"></span></a>
		            Value
		            <c:url value="typeOfComponentList" var="url">
		                    <c:param name="sorter" value="valueOfComponent/desc"/>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-up"></span></a></th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items="${pageListHolder.pageList}" var="typeList">
				<tr>
					<td>${typeList.id }</td>
					<td>${typeList.name }</td>
					<td>${typeList.valueOfComponent }</td>
					<form:form action="updateType/${typeList.id}" commandName="updateType" method="get">
						<td><input type="submit" value="update" class = "btn btn-info"/></td>
					</form:form>
					<form:form action="removeType/${typeList.id}" commandName="removeType" method="post">
						<td><input type="submit" value="remove" class = "btn btn-danger"/></td>
					</form:form>
				</tr>
			</c:forEach>
		</table>
	
	
	
		<div>
		
			<c:url value="typeOfComponentList" var="prev">
		        <c:param name="page" value="1"/>
		        <c:if test = "${not empty sorter }">
		       		<c:param name="sorter" value="${sorter}"/>
		       	</c:if>
		    </c:url>
		     <c:if test="${page > 1}">
		        <a class = "btn btn-small btn-default" href="<c:out value="${prev}" />" class="pn prev"><span class="glyphicon glyphicon-fast-backward"></span></a>
		    </c:if>
		    
		    
			<c:url value="typeOfComponentList" var="prev">
		        <c:param name="page" value="${page}"/>
		        <c:if test = "${not empty sorter }">
		       		<c:param name="sorter" value="${sorter}"/>
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
		                <c:url value="typeOfComponentList" var="url">
		                    <c:param name="page" value="${i.index}"/>
					        <c:if test = "${not empty sorter }">
					       		<c:param name="sorter" value="${sorter}"/>
					       	</c:if>
		                </c:url>
		                <a href='<c:out value="${url}" />'>${i.index}</a>
		            </c:otherwise>
		        </c:choose>
		    </c:forEach>
		    
		    
		    <c:url value="typeOfComponentList" var="next">
		        <c:param name="page" value="${page + 2}"/>
		        <c:if test = "${not empty sorter }">
		       		<c:param name="sorter" value="${sorter}"/>
		       	</c:if>
		    </c:url>
		    <c:if test="${page + 1 < maxPages}">
		        <a class = "btn btn-small btn-default" href='<c:out value="${next}" />' ><span class="glyphicon glyphicon-step-forward"></span></a>
		    </c:if>
		    
		    <c:url value="typeOfComponentList" var="next">
		        <c:param name="page" value="${maxPages}"/>
		        <c:if test = "${not empty sorter }">
		       		<c:param name="sorter" value="${sorter}"/>
		       	</c:if>
		    </c:url>
		    <c:if test="${page + 2 < maxPages}">
		        <a class = "btn btn-small btn-default" href='<c:out value="${next}" />'><span class="glyphicon glyphicon-fast-forward"></span></a>
		    </c:if>
		
		
		</div>
		</div>
		</sec:authorize>
</body>
</html>