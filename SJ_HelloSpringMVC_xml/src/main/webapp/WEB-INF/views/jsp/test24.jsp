<%@page import="java.util.List"%>
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
	<h3>This is test23.jsp. Hello!</h3>
	<div>name = ${name}</div>
	<div>age = ${age}</div>
	<%
		@SuppressWarnings("unchecked")
		List<String> foodList = (List<String>) request.getAttribute("foodList");
		for(String f: foodList) {
			out.print(f + "<br>");
		}
	%>
</body>
</html>