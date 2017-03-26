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
<title>Make configuration</title>
<style type="text/css">
	div.padding {
		padding: 25px;
	}
	a.mandatoryOne {
		color : white;
		background-color: red;
	}
	a.mandatoryOneToMany{
		color : white;
		background-color : blue;
	}
	a.mandatoryZero {
		color : white;
		background-color : green;
	}
	a.mandatoryZeroToMany {
		color : white;
		background-color : purple;
	}
	p.mandatoryOne {
		color: red;
	}
	p.mandatoryOneToMany{
		color : blue;
	}
	p.mandatoryZero {
		color : green;
	}
	p.mandatoryZeroToMany {
		color : purple;
	}
</style>
</head>
<body>

	<div class = "padding">
		
	</div>
	<sec:authorize access="hasRole('SELLER') or hasRole('ADMIN')">
	<div class = "container">
	<div>
		<p class = "mandatoryOne">Mandatory component (1)</p>
		<p class = "mandatoryOneToMany">Mandatory one to many component (1...N)</p>
		<p class = "mandatoryZero">Optional component (0)</p>
		<p class = "mandatoryZeroToMany">Optional component (0...N)</p>
		
	</div>
	
	<div>
		<c:url value="makeConfiguration" var="url">
				<c:param name="filter" value="all"/>
		</c:url>
		<a href='<c:out value="${url}" />' class="btn btn-default">All</a>
		<c:forEach items = "${typeOfComponents }" var = "type">
			<c:url value="makeConfiguration" var="url">
				<c:param name="filter" value="${type.name}"/>
		    </c:url>
		    <c:choose>
		    	<c:when test="${type.valueOfComponent == 'one' }"><a href='<c:out value="${url}" />' class="btn btn-default mandatoryOne">${type.name}</a></c:when>
		    	<c:when test="${type.valueOfComponent == 'oneToMany' }"><a href='<c:out value="${url}" />' class="btn btn-default mandatoryOneToMany">${type.name}</a></c:when>
		    	<c:when test="${type.valueOfComponent == 'zero' }"><a href='<c:out value="${url}" />' class="btn btn-default mandatoryZero">${type.name}</a></c:when>
		    	<c:otherwise><a href='<c:out value="${url}" />' class="btn btn-default mandatoryZeroToMany">${type.name}</a></c:otherwise>
		    	
		    </c:choose> 
		</c:forEach>
	</div>
	<h3>Make configuration</h3>
		<table class="table table-hover">
			<tr>
				<th><c:url value="makeConfiguration" var="url">
		                    <c:param name="sorter" value="id/asc"/>
		                    <c:if test = "${not empty filter }">
		                    	<c:param name="filter" value="${filter }"/>
		                    </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-down"></span></a>
		            Id
		            <c:url value="makeConfiguration" var="url">
		                    <c:param name="sorter" value="id/desc"/>
		                    <c:if test = "${not empty filter }">
		                    	<c:param name="filter" value="${filter }"/>
		                    </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-up"></span></a>
		            </th>
				<th><c:url value="makeConfiguration" var="url">
		                    <c:param name="sorter" value="code/asc"/>
		                    <c:if test = "${not empty filter }">
		                    	<c:param name="filter" value="${filter }"/>
		                    </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-down"></span></a>
		            Code
		            <c:url value="makeConfiguration" var="url">
		                    <c:param name="sorter" value="code/desc"/>
		                    <c:if test = "${not empty filter }">
		                    	<c:param name="filter" value="${filter }"/>
		                    </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-up"></span></a>
		           </th>
				<th>Type of component</th>
				<th><c:url value="makeConfiguration" var="url">
		                    <c:param name="sorter" value="name/asc"/>
		                    <c:if test = "${not empty filter }">
		                    	<c:param name="filter" value="${filter }"/>
		                    </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-down"></span></a>
		            Name
		            <c:url value="makeConfiguration" var="url">
		                    <c:param name="sorter" value="name/desc"/>
		                    <c:if test = "${not empty filter }">
		                    	<c:param name="filter" value="${filter }"/>
		                    </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-up"></span></a>
		            </th>
				<th><c:url value="makeConfiguration" var="url">
		                    <c:param name="sorter" value="price/asc"/>
		                    <c:if test = "${not empty filter }">
		                    	<c:param name="filter" value="${filter }"/>
		                    </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-down"></span></a>
		            Price
		            <c:url value="makeConfiguration" var="url">
		                    <c:param name="sorter" value="price/desc"/>
		                    <c:if test = "${not empty filter }">
		                    	<c:param name="filter" value="${filter }"/>
		                    </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-up"></span></a>
		            </th>
				<th><c:url value="makeConfiguration" var="url">
		                    <c:param name="sorter" value="stock/asc"/>
		                    <c:if test = "${not empty filter }">
		                    	<c:param name="filter" value="${filter }"/>
		                    </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-down"></span></a>
		            On stock
		            <c:url value="makeConfiguration" var="url">
		                    <c:param name="sorter" value="stock/desc"/>
		                    <c:if test = "${not empty filter }">
		                    	<c:param name="filter" value="${filter }"/>
		                    </c:if>
		                </c:url>
		            <a href='<c:out value="${url}" />'><span class="glyphicon glyphicon-arrow-up"></span></a>
		            </th>
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
									
					<td><c:url value = "makeConfiguration" var = "url">
						<c:param name = "add" value = "${component.id }" />
						<c:if test = "${not empty filter }">
				       		<c:param name="filter" value="${filter}"/>
				       	</c:if>
				       	<c:if test = "${not empty page }">
				       		<c:param name="page" value="${page + 1}"/>
				       	</c:if>
				       	<c:if test = "${not empty sorter }">
				       		<c:param name="sorter" value="${sorter}"/>
				       	</c:if>
					</c:url>
					<a href='<c:out value="${url}" />' class="btn btn-info">add</a>
					</td>
	
				</tr>
			</c:forEach>
		</table>
		
		<div>
		
			<c:url value="makeConfiguration" var="prev">
		        <c:param name="page" value="1"/>
		        <c:if test = "${not empty filter }">
		       		<c:param name="filter" value="${filter}"/>
		       	</c:if>
				<c:if test = "${not empty sorter }">
					<c:param name="sorter" value="${sorter}"/>
				</c:if>
				       	
		    </c:url>
		     <c:if test="${page > 1}">
		        <a class = "btn btn-small btn-default" href="<c:out value="${prev}" />" class="pn prev"><span class="glyphicon glyphicon-fast-backward"></span></a>
		    </c:if>
		    
		    
			<c:url value="makeConfiguration" var="prev">
		        <c:param name="page" value="${page}"/>
		        <c:if test = "${not empty filter }">
		       		<c:param name="filter" value="${filter}"/>
		       	</c:if>
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
		                <c:url value="makeConfiguration" var="url">
		                    <c:param name="page" value="${i.index}"/>
					        <c:if test = "${not empty filter }">
					       		<c:param name="filter" value="${filter}"/>
					       	</c:if>
							<c:if test = "${not empty sorter }">
								<c:param name="sorter" value="${sorter}"/>
							</c:if>
		                </c:url>
		                <a href='<c:out value="${url}" />'>${i.index}</a>
		            </c:otherwise>
		        </c:choose>
		    </c:forEach>
		    
		    
		    <c:url value="makeConfiguration" var="next">
		        <c:param name="page" value="${page + 2}"/>
		        <c:if test = "${not empty filter }">
		       		<c:param name="filter" value="${filter}"/>
		       	</c:if>
				<c:if test = "${not empty sorter }">
					<c:param name="sorter" value="${sorter}"/>
				</c:if>
		    </c:url>
		    <c:if test="${page + 1 < maxPages}">
		        <a class = "btn btn-small btn-default" href='<c:out value="${next}" />' ><span class="glyphicon glyphicon-step-forward"></span></a>
		    </c:if>
		    
		    <c:url value="makeConfiguration" var="next">
		        <c:param name="page" value="${maxPages}"/>
		        <c:if test = "${not empty filter }">
		       		<c:param name="filter" value="${filter}"/>
		       	</c:if>
				<c:if test = "${not empty sorter }">
					<c:param name="sorter" value="${sorter}"/>
				</c:if>
		    </c:url>
		    <c:if test="${page + 2 < maxPages}">
		        <a class = "btn btn-small btn-default" href='<c:out value="${next}" />'><span class="glyphicon glyphicon-fast-forward"></span></a>
		    </c:if>
		
		
		</div>
	
	<div class = "padding">
	
	</div>
	
	<c:if test = "${not empty checkMandatoryOne }">
		<script>
			alert("you can have only one component of that type");
		</script>
	</c:if>
	
		<c:if test = "${not empty checkMandatoryComponents }">
		<script>
			alert("You don't have all mandatory components");
		</script>
	</c:if>
	
	<c:if test = "${not empty componentList }">
		<table class="table table-hover">
			<tr>
				<th>Id</th>
				<th>Code</th>
				<th>Type of component</th>
				<th>Name</th>
				<th>Price</th>
				<th></th>
	
			</tr>
			<c:forEach items = "${componentList }" var = "list" varStatus="loop">
				<tr>
					<td>${list.id}</td>
					<td>${list.code}</td>
					<td>${list.typeOfComponent.name}</td>
					<td>${list.name}</td>
					<td>${list.price}</td>
				<%-- 	<form:form action="removeComponentFromConfiguration/${loop.index}" commandName="removeComponent" method="post">
						<td><input type="submit" value="remove" class = "btn btn-danger"/></td>
					</form:form> --%>
					<td><c:url value = "makeConfiguration" var = "url">
						<c:param name = "remove" value = "${loop.index }" />
						<c:if test = "${not empty filter }">
				       		<c:param name="filter" value="${filter}"/>
				       	</c:if>
				       	<c:if test = "${not empty page }">
				       		<c:param name="page" value="${page + 1}"/>
				       	</c:if>
				       	<c:if test = "${not empty sorter }">
		                    <c:param name="sorter" value="${sorter }"/>
		                </c:if>
					</c:url>
					<a href='<c:out value="${url}" />' class="btn btn-danger">remove</a>
					</td>
				</tr>
				
			
			</c:forEach>
			<tr>
				<td>Total</td>
				<td>${total }</td>
			</tr>
			<tr>
			<form:form action = "saveConfiguration" commandName="componentList" method = "post">
				<td><input type = "submit" value = "save" class = "btn btn-success" /></td>
			</form:form>
			</tr>
		</table>
	</c:if>
	</div>
	</sec:authorize>
</body>
</html>