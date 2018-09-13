<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ls" value="${sessionScope.localeString}" />
<html>
<head>
<title>Login</title>
</head>
<body onload="document.f.username.focus()">
	<jsp:include page="_menu.jsp" />

	<h1><spring:message code="label.register.title" /></h1>

	<form:form action="${pageContext.request.contextPath}/${ls}/register"
		method="POST" modelAttribute="newRegisterUser">
		<table>
			<tr>
				<td><spring:message code="label.username" /></td>
				<td><form:input path="username" /></td>
			</tr>
			<tr>
				<td><spring:message code="label.email" /></td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td><spring:message code="label.password" /></td>
				<td><form:password path="password" /></td>
			</tr>
			<tr>
				<td><spring:message code="label.fullname" /></td>
				<td><form:input path="fullname" /></td>
			</tr>
			
			<tr>
				<td><input name="submit" type="submit" value="<spring:message code="label.register.submit" />" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>