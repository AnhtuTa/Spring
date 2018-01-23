<%@page import="java.net.InetAddress"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="_menu.jsp"></jsp:include>
	<h3>This is test21.jsp</h3>
	<%
		String hostname = InetAddress.getLocalHost().getHostName();
		out.print("hostname = " + hostname);
	%>
	<p>The context path is: ${pageContext.request.contextPath}.</p>
	<div>postId = ${requestScope.postId}</div>
	<div>postId = ${postId}</div>
</body>
</html>