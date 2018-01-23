<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.error {
	color: red;
}
</style>
</head>
<body>
	<h2>add-user.jsp</h2>
	<form:form action="add-user" method="POST" modelAttribute="user">
    Id: <form:input path="id" />
		<form:errors path="id" cssClass="error" />
		<br />
		<br />
    Name: <form:input path="name" />
		<form:errors path="name" cssClass="error" />
		<br />
		<br />
    Email: <form:input path="email" />
		<form:errors path="email" cssClass="error" />
		<br />
		<br />
    Phone Number: <form:input path="phoneNumber" />
		<form:errors path="phoneNumber" cssClass="error" />
		<br />
		<br />
    Date Of Birth: <form:input path="dateOfBirth" />
		<form:errors path="dateOfBirth" cssClass="error" />
		<br />
		<button type="submit">Submit</button>
	</form:form>
</body>
</html>