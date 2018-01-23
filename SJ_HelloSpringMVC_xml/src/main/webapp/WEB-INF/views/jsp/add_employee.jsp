<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="_menu.jsp"></jsp:include>
	<h2>Employee</h2>
	
<!-- 
	modelAttribute: biểu thị đối tượng được sử dụng trong form. 
		Ví dụ modelAttribute=”employee” tức là form này sẽ thực hiện 
		xử lý các thuộc tính của đối tượng employee vừa truyền tới từ controller.
	Thuộc tính path trong các thẻ form thực hiện map với các thuộc
		tính của đối tượng trong modelAttribute. Ví dụ path=”id” sẽ được 
		hiểu là giá trị của thuộc tính ‘id’ trong đối tượng employee
 -->
 
	<form:form method="get" action="view-employee" modelAttribute="employee">
		<table>
			<tr>
				<td><form:label path="id">Id</form:label></td>
				<td><form:input path="id" /></td>
			</tr>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td><form:label path="Address">Addess</form:label></td>
				<td><form:textarea path="address" /></td>
			</tr>
			<tr>
				<td><form:label path="email">Email</form:label></td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td><form:label path="gender">Gender</form:label></td>
				<td><form:radiobutton path="gender" value="male" label="male" />
					<form:radiobutton path="gender" value="female" label="female" /></td>
			</tr>
			<tr>
				<td><form:label path="favorites">Favorite</form:label></td>
				<td><form:checkboxes items="${favoriteList}" path="favorites" />
				</td>
			</tr>
			<tr>
				<td><form:label path="position">Position</form:label></td>
				<td><form:select path="position">
						<form:option value="NONE" label="Select" />
						<form:options items="${positionList}" />
					</form:select></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>