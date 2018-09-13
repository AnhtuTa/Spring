<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>
<html>
<head>
<title>index</title>
</head>
<body>
	<jsp:include page="_menu.jsp"/>
	<h1>Spring MVC - Security</h1>
	<h2>${message}</h2>
	<a href='<c:url value="/admin" />'>admin</a>
	<a href='<c:url value="/user" />'>user</a>
</body>
</html>