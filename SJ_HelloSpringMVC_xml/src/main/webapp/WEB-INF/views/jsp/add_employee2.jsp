<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	table {
		border-collapse: collapse;
	}
	td {
		padding: 5px;
	}
</style>
</head>
<body>
	<jsp:include page="_menu.jsp"></jsp:include>
	<h2>This is add_employee2.jsp</h2>
	
<!-- 
	modelAttribute: biểu thị đối tượng được sử dụng trong form. 
		Ví dụ modelAttribute=”employee” tức là form này sẽ thực hiện 
		xử lý các thuộc tính của đối tượng employee vừa truyền tới từ controller.
	Thuộc tính path trong các thẻ form thực hiện map với các thuộc
		tính của đối tượng trong modelAttribute. Ví dụ path=”id” sẽ được 
		hiểu là giá trị của thuộc tính ‘id’ trong đối tượng employee
 -->
 
	<form method="post" action="view-employee2">
		<table border="1">
			<tr>
				<td><label>Id</label></td>
				<td><input type="text" name="txt_id" /></td>
			</tr>
			<tr>
				<td><label>Name</label></td>
				<td><input name="txt_name" /></td>
			</tr>
			<tr>
				<td><label>Address</label></td>
				<td><textarea name="txt_address"></textarea></td>
			</tr>
			<tr>
				<td><label>Email</label></td>
				<td><input name="txt_email" /></td>
			</tr>
			<tr>
				<td><label>Gender</label></td>
				<td>
					<input type="radio" name="gender" value="male"> Male<br>
  					<input type="radio" name="gender" value="female"> Female<br>
				</td>
			</tr>
			<tr>
				<td><label>Favorite</label></td>
				<td>
					<c:forEach items="${favoriteList}" var="favorite">
						<input type="checkbox" name="favorites" value="${favorite}">${favorite}<br>
					</c:forEach>
					
				</td>
			</tr>
			<tr>
				<td><label>Position</label></td>
				<td>
					<select name="position">
						<c:forEach items="${positionList}" var="position">
							<option value="${position}">${position}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
		<input style="margin-top: 10px; padding: 5px;" type="submit" value="Submit" />
	</form>
</body>
</html>