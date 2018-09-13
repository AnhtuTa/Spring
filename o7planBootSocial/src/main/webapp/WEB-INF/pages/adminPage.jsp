<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<title>${title}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<jsp:include page="_menu.jsp" />
	<h2>Admin Page</h2>
	<h3>
		Hello ${sessionScope.userFullName}
	</h3>
	<b>This is protected page</b>
</body>
</html>