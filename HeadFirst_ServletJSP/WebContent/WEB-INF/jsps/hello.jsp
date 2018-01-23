<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demo chapter 7 and 8</title>
</head>
<body>
	<h2>Demo chapter 7 and 8: script, EL,...</h2>
	
	<h3>Using jsp:useBean tag</h3>
	
	<br/><div><b>Person1's Info</b></div>
	<!-- Chú ý rằng person1 phải trùng với key trong lệnh request.setAttribute("person1", p); (ở bên servlet) -->
	<jsp:useBean id="person1" class="beans.Person" scope="request" />
	<jsp:getProperty name="person1" property="name" /><br/>
	<jsp:getProperty name="person1" property="address" /><br/>
	<jsp:getProperty name="person1" property="dog" /><br/>	<!-- Không thể làm ntn đc vì thuộc tính 'dog' ko thuộc kiểu String hoặc int -->
	
	
	<!-- Giả sử dùng 1 thằng person mà bên request ko gửi sang, vẫn có thể tạo mới nó và set các thuộc tính cho nó để các thuộc tính đó khác null -->
	<!-- Dùng jsp:useBean thì luôn tạo mới 1 bean nếu ko tồn tại, nhưng ta phải tự thiết lập các
	thuộc tính cho bean đó -->
	<jsp:useBean id="person2" class="beans.Person" scope="request">
		<!-- Chú ý: việc set thuộc tính sau chỉ đc thực hiện nếu bean person2 KHÔNG TỒN TẠI TỪ request VÀ ĐC TẠO MỚI -->
		<jsp:setProperty name="person2" property="name" value="Nguyen bka"/>
		<jsp:setProperty name="person2" property="address" value="Sai Gon"/>
	</jsp:useBean>
	
	<!-- Bây h thử lấy giá trị vừa set -->
	<br/><div><b>Person2's Info</b></div>
	Name: <jsp:getProperty name="person2" property="name"/>, 
	and address: <jsp:getProperty name="person2" property="address"/><br/>
	
	<!-- Example with type, class -->
	<br/><div><b>Employee's Info</b></div>
	<jsp:useBean id="human1" type="beans.Human" class="beans.Employee" scope="request" />	<!-- Type là kiểu abstract, còn class là kiểu concrete. KHÔNG THỂ HOÁN VỊ LẠI! -->
	<jsp:getProperty name="human1" property="name"/><br/>
	<jsp:getProperty name="human1" property="address" /><br/>
	<jsp:getProperty name="human1" property="id" /><br/>
	<jsp:getProperty name="human1" property="dept" /><br/>
	
	<h3>Using EL</h3>
	<div><b>Person1's info</b></div>
	name: ${person1.name}, address: ${person1.address},<br/>
	his dog's name: ${person1.dog.name},<br>
	his dog's toy: ${person1.dog.toys}<br/>
	<i>Of course, his dog's info didn't display as we want! So we should use this</i><br/>
	
	<br><div>Cach khac</div>
	<div><b>Person1's info</b></div>
	name: ${person1['name']}, address: ${person1['address']},<br/>
	his dog's name: ${person1["dog"].name},<br>
	his dog's toy: ${person1['dog'].toys}<br/>
	
	<i>Of course, his dog's info didn't display as we want! So we should use this</i><br/>
	his dog's toy's name:<br>
	Toy: ${person1.dog.toys["0"].name},
	 	 ${person1['dog'].toys[1].name},
	 	 ${person1["dog"].toys["2"]["name"]}<br/>
	<i>It work!</i><br/>
	<div style="color: blue;">Nếu số lượng phần tử trong mảng toy[] ko biết trước thì làm ntn? Có thể dùng c:forEach (JSTL)</div>
	
	<br/><div><b>We using ArrayList</b></div>
	<%
		//Using scripting. It looks really bad for non-java programmer
		@SuppressWarnings("unchecked")
		List<String> foodList = (List<String>) request.getAttribute("foodList");
		for(String str : foodList) {
			out.print(str + "<br/>");
		}
	%><br/>
	<div>First food: ${foodList[0]}</div>
	<div>Second food: ${foodList["1"]}</div><br/>	<!-- Both ways is oK! -->
	
	<br/><div><b>Now we using Map</b></div>
	mem1: ${personMap.mem1.name}<br/>
	mem2: ${personMap["mem2"].name}<br/>	<!-- Both ways is oK! -->
	mem3: ${personMap.mem3.name}<br/>
	mem4: ${personMap.mem4.name}<br/>
	
	<!-- Đa số trường hợp ko biết trước kích thước của mảng, ArrayList hay Map, do đó cần dùng
	vòng for. Nếu dùng EL thì có c:forEach... -->
</body>
</html>





