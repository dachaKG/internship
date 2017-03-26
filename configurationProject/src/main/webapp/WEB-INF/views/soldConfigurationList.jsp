<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/start.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of sold configuration</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<style type="text/css">
	div.padding {
		padding: 25px;
	}
</style>
</head>
<body>

	<div class = "padding">
	
	</div>
	<sec:authorize access="hasRole('SELLER') or hasRole('ADMIN')">
	<div class = "container">
	<h3>Sold configuration list</h3>
	<table class="table table-hover">
		<tr>
			<th>Id</th>
			<th>Date</th>
			<th>Total</th>
		</tr>
		
		<c:forEach items="${pageListHolder.pageList }" var="configuration">
			<tr>
				<td>${configuration.id}</td>
				<td>${configuration.date }</td>
				<td>${configuration.total }</td>
				<form:form action="configurationDetails/${configuration.id}" commandName="configurationDetails" method="get">
					<td><input type="submit" value="details" class = "btn btn-info"/></td>
				</form:form>
			</tr>
		</c:forEach>

	</table>
	
		<div>
		
			<c:url value="soldConfigurationList" var="prev">
		        <c:param name="page" value="1"/>
		        
		    </c:url>
		     <c:if test="${page > 1}">
		        <a class = "btn btn-small btn-default" href="<c:out value="${prev}" />" class="pn prev"><span class="glyphicon glyphicon-fast-backward"></span></a>
		    </c:if>
		    
		    
			<c:url value="soldConfigurationList" var="prev">
		        <c:param name="page" value="${page}"/>
		        
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
		                <c:url value="soldConfigurationList" var="url">
		                    <c:param name="page" value="${i.index}"/>
		                  
		                </c:url>
		                <a href='<c:out value="${url}" />'>${i.index}</a>
		            </c:otherwise>
		        </c:choose>
		    </c:forEach>
		    
		    
		    <c:url value="soldConfigurationList" var="next">
		        <c:param name="page" value="${page + 2}"/>
		        
		    </c:url>
		    <c:if test="${page + 1 < maxPages}">
		        <a class = "btn btn-small btn-default" href='<c:out value="${next}" />' ><span class="glyphicon glyphicon-step-forward"></span></a>
		    </c:if>
		    
		    <c:url value="soldConfigurationList" var="next">
		        <c:param name="page" value="${maxPages}"/>
		        
		    </c:url>
		    <c:if test="${page + 2 < maxPages}">
		        <a class = "btn btn-small btn-default" href='<c:out value="${next}" />'><span class="glyphicon glyphicon-fast-forward"></span></a>
		    </c:if>
		
		
		</div>
	</div>	
	</sec:authorize>
</body>
</html>