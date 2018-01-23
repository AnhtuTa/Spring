<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="form_result.jsp">
		Name: <input type="text" name="userName"><br/>
		Address: <input type="text" name="userAddr"><br/>
		<input type="submit" value="Let's go!">
	</form><br/><br/>
	
	<form action="form_result2.jsp">
		Name: <input type="text" name="userName"><br/>
		Address: <input type="text" name="address"><br/>	<!-- name của textfield trùng 1 thuộc tính với bean -->
		<input type="submit" value="Let's go!">
	</form><br/><br/>
	
	<form action="form_result3.jsp">
		Name: <input type="text" name="name"><br/>
		Address: <input type="text" name="address"><br/>	<!-- name của textfield trùng tất cả thuộc tính với bean -->
		<input type="submit" value="Let's go!">
	</form><br/><br/>
</body>
</html>