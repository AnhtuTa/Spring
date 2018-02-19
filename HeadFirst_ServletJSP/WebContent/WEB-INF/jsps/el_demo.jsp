<%@page import="beans.Toy"%>
<%@page import="beans.Manager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EL demo</title>
</head>
<body>
	<div style="color: blue; margin: 10px">
		How to get contextPath using EL:<br>
		ContextPath = ${pageContext.request.contextPath}<br>
		Nhận xét: muốn lấy thuộc tính của request thì phải thông qua đối tượng pageContext,
		chú ý thêm là contextPath KHÔNG phải là thuộc tính của requestScope (1 đối tượng ẩn trong EL),
		mà nó là thuộc tính của request, mà muốn dùng thằng request thì phải thông qua thằng 
		pageContext (1 đối tượng ẩn trong EL)
	</div>
	<jsp:useBean id="person2" class="beans.Person" scope="request">
		<!-- Chú ý: việc set thuộc tính sau chỉ đc thực hiện nếu bean person2 KHÔNG TỒN TẠI TỪ request VÀ ĐC TẠO MỚI -->
		<jsp:setProperty name="person2" property="name" value="Nguyen bka"/>
		<jsp:setProperty name="person2" property="address" value="Sai Gon"/>
	</jsp:useBean>
	
	<div>Gia tri cua name_ss trong session: <%=session.getAttribute("name_ss") %></div>
	<div>Gia tri cua name_ss trong session: ${session.name_ss}</div>	<%-- Dùng như này là sai, vì session là 1 đối tượng ẩn trong JSP, chứ ko phải trong EL, mà ta lại đang dùng EL (có dấu $) --%>
	<div>Gia tri cua name_ss trong session: ${sessionScope.name_ss}</div><br>	<%-- Dùng như này là đúng! Vì sessionScope là 1 đối tượng ẩn của EL --%>
	
	<h3>Demo đối tượng ẩn trong EL: session scope</h3>
	<%
		// ko nen dung scriptlet nhu the nay!
		Manager m = (Manager) session.getAttribute("manager_ss");
		out.print(m.getName());
	%>
	<div>Gia tri name cua manager_ss trong session: <%= ((Manager)session.getAttribute("manager_ss")).getName() %></div>
	<div>Gia tri name cua manager_ss trong session: ${sessionScope.manager_ss.name}</div>
	<div>Gia tri name cua manager_ss trong session: ${sessionScope["manager_ss"]["name"]}</div><br>
	<div>${sessionScope.manager_ss.dept.id }</div>
	<div>${sessionScope.manager_ss.dept.name }</div><br>
	
	<h3>Demo đối tượng ẩn trong EL: request scope</h3>
	<div><%=request.getAttribute("comics_rq") %></div>
	<div>${requestScope.comics_rq }</div>
	<div>${requestScope["comics_rq"] }</div>
	
	<div><%=((Toy) request.getAttribute("toy_rq")).getName() %></div>
	<div><%=((Toy) request.getAttribute("toy_rq")).getPrice() %></div>
	<div>${requestScope.toy_rq.name} - ${requestScope.toy_rq.price}</div>
	
	<h3>Demo đối tượng ẩn trong EL: param</h3>
	<div>Cần phải truy cập <a href="http://localhost:8080/HeadFirst_ServletJSP/el_demo?name_pr=Nguyen%20Thi%20Nhu%20Quynh&addr_pr=Hanoi">link sau</a> để chạy đc ví dụ này! </div>	
	<!-- Các đối tượng param và paramValues giúp bạn truy cập tới các giá trị tham số một cách thông thường thông qua các phương thức request.getParameter và request.getParameterValues. -->
	<!-- Đối tượng param trả về các giá trị chuỗi đơn, trong khi đó đối tượng paramValues trả về các mảng chuỗi. -->
	<div>${param.name_pr }</div>
	<div>${param.addr_pr }</div>
	
	<h3>Demo đối tượng ẩn trong EL: paramValues</h3>
	<div>Cần phải truy cập <a href="http://localhost:8080/HeadFirst_ServletJSP/el_demo?books=Conan&books=One%20Piece">link sau</a> để chạy đc ví dụ này! </div>
	<div>${paramValues.books[0]}</div>
	<div>${paramValues.books[1]}</div>
	
	<h3>Demo đối tượng ẩn trong EL: header</h3>
	<div>${header.User-Agent}</div>
	<div>${header.Host}</div>
</body>
</html>