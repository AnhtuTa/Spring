<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>This is test.jsp</h3>
	Click here: <a href="./admin/login">admin/login</a> to test<br>
	<div>
		Click here: <a href="./admin/oldLogin">admin/oldLogin</a> to test.
		Note: you can't access this link because of interceptor!
	</div>
</body>
</html>