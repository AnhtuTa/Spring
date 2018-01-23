<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>JSTL tutorial</title>
	<style>
		h3 {
			color: blue;
		}
	</style>
</head>
<body>
	<h3>Demo c:out</h3>
	<c:out value="${sessionScope.manager_ss.name}" /><br> <%-- giong het ket qua cua: ${sessionScope.manager_ss.name} --%>
	<c:out value="${'<div><b>'}" /><br>
	
	<h3>Demo c:set</h3>
	<c:set var="school" scope="session" value="${'Dai Hoc Bach Khoa HN'}" />
	<c:set var="faculty" scope="session" value="f*cking SET" />
	<%=session.getAttribute("school") %><br>	<%-- Nen dung c:out thay vi dung scriptlet! --%>
	<c:out value="${sessionScope.faculty }" /><br>
	
	<h3>Demo c:remove</h3>
	<c:remove var="faculty" scope="session"/>
	<c:out value="${sessionScope.faculty }" /><br>		<%-- ko hien thi cai gi! Vi atttibute nay da bi remove --%>
	<c:remove var="school" scope="page"/>
	<c:out value="${sessionScope.school }" /><br>		<%-- Van hien thi, vi scope=page, ma trong page hien tai ko co thuoc tinh school --%>
	
	<h3>Demo c:if</h3>
	<c:if test="${school == 'Dai Hoc Bach Khoa HN' }">
		<div>Mày chọn đúng trường rồi đấy!!!</div>
	</c:if>
	<c:set var="birth_year" value="1995" scope="page"/>
	<c:if test="${birth_year > 1990}">
		<div>Mày còn quá trẻ!</div>
	</c:if>
	
	<h3>Demo c:forEach</h3>
	<div style="color: blue">Chú ý: nếu phần này ko hiện j nghĩa là chưa set attribute cho session.
	Click <a href="http://localhost:8080/HeadFirst_ServletJSP/CreateSession">here</a> để tạo 
	attribute cho session</div>
	
	<c:forEach items="${sessionScope.person_ss.dog.toys}" var="dogToy">
		<div>${dogToy.name}</div>
	</c:forEach>
	<div>VD khac</div>
	<c:forEach var="i" begin="0" end="3">
		<div>${i}</div>
	</c:forEach>
	
	<h3>Demo c:forTokens</h3>
	<c:forTokens items="Anhtu,BKHN,f*cking SET,20171" delims="," var="token">
		<c:out value="${token}" /><br>
	</c:forTokens>
	
	<h3>Demo c:choose,when,otherwise; c:url,c:param</h3>
	<c:set var = "salary" scope = "session" value = "${2000*2}"/>
	<p>Your salary is : <c:out value = "${salary}"/></p>
	<c:choose>
		<c:when test = "${salary <= 0}">
			Salary is very low to survive.
		</c:when>
		
		<c:when test = "${salary > 1000}">
			Salary is very good.
		</c:when>
		
		<c:otherwise>
			No comment sir...
		</c:otherwise>
	</c:choose><br>
	<%-- c:choose, c:when giống switch, case trong Java, và c:otherwise giống default trong switch --%>
	
	<c:choose>
		<c:when test="${param.color=='red'}">
			<p style="color:red;">RED COLOR</p>
		</c:when>  
		
		<c:when test="${param.color=='blue'}">
			<p style="color:blue;">BLUE COLOR</p>
		</c:when>  
		
		<c:otherwise>
			<b>No color!</b>
			<c:url value = "JSTL_demo.jsp" var = "blue_url">
				<c:param name = "color" value = "blue"/>
			</c:url>
			<div>click <a href="<%=request.getContextPath() %>/JSTL_demo.jsp?color=red">red color</a> to show red color</div>
			<div>click <a href="${blue_url}">blue color</a> to show blue color</div>		
			
		</c:otherwise>
	</c:choose>
	
	<h3>Demo c:param, c:url, c:import</h3>
	<c:url value = "index.html" var = "myURL">
		<c:param name = "trackingId" value = "1234"/>
		<c:param name = "reportType" value = "summary"/>
	</c:url>
	<div style="background: yellow">
		<c:import url = "${myURL}" charEncoding="utf-8"/>		<%-- print this url to this page. if we don't want this, we have to store result in a value by using attribute: var --%>
	</div>
	<c:import var = "tuto_url" url = "http://www.tutorialspoint.com"/>
	<%-- Now we can display the url above, but don't do this! --%>
	<%-- c:out value="${tuto_url}"  --%>
	<a href="${myURL}">click here</a> to go to index.html<br>
	
</body>
</html>