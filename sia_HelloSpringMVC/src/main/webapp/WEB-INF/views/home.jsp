<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Home</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/home.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/home.css">
	<%
		out.print(request.getContextPath());
	%>
</head>
<body>
	<h3>Hello Spring MVC</h3>
	<h4>This project was created using the book: <i style="color: blue">Spring in action</i>, 4ht edition</h4>
</body>
</html>