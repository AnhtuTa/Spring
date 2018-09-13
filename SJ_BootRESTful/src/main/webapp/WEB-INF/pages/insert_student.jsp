<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Insert student</h2>
	<form action="/student2" method="post">
		<table>
			<tr>
				<td>Name</td>
				<td><input type="text" name="st_name"></td>
			</tr>
			<tr>
				<td>School</td>
				<td><input type="text" name="st_school"></td>
			</tr>
		</table>
		<input type="submit">
	</form>
</body>
</html>