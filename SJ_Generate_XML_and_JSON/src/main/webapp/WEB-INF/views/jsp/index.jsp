<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>
</head>
<body>
	<h3>This is index.jsp</h3>
	<h4>Try these</h4>
	<ul>
		<li><a href="${pageContext.request.contextPath}/get-pupil/xml/5">[XML] Get a pupil who has id = 5</a></li>
		<li><a href="${pageContext.request.contextPath}/get-pupil-list/xml/0/100">[XML] Get first 100 pupils</a></li>
		<li><a href="${pageContext.request.contextPath}/get-pupil/json/5">[JSON] Get a pupil who has id = 5</a></li>
		<li><a href="${pageContext.request.contextPath}/get-pupil-list/json/0/100">[JSON] Get first 100 pupils</a></li>
	</ul>
</body>
</html>