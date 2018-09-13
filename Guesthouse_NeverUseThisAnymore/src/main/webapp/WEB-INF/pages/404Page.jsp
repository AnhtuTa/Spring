<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<title><spring:message code="label.404.title" /></title>
</head>
<body>
	<jsp:include page="_menu.jsp" />

	<h3 style="color: red;"><spring:message code="msg.page_not_found" /></h3>
</body>
</html>