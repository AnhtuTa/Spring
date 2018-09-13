<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@page isELIgnored="false" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<title>${title}</title>
</head>
<body>
	<h1>User Info Page</h1>
    <h3>Welcome : ${pageContext.request.userPrincipal.name}</h3>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>