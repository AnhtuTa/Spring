<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>This is the result from FormDemo.jsp</h2>
	
	<!-- get param from FormDemo.jsp -->
	<jsp:useBean id="person3" type="beans.Person" class="beans.Person" scope="request">
		<jsp:setProperty name="person3" property="name" param="userName"/>		<!-- userName là tên của textfield trong form bên FormDemo.jsp -->
		<jsp:setProperty name="person3" property="address"/>		<!-- If the request parameter name matches the bean property name, 
																		you don’t need to specify a value in the jsp:setProperty tag for that property.
																		trong trường hợp này thì thuộc tính address trùng với tên textfield bên FormDemo.jsp
																		nên nó tự động lấy giá trị đó luôn! -->
	</jsp:useBean>
	
	<div><b>Person3's Info</b></div>
	<jsp:getProperty name="person3" property="name" /><br/>
	<jsp:getProperty name="person3" property="address" /><br/>
</body>
</html>