<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>JSONP and CORS demo</title>
<link rel="stylesheet" href="/css/style.css" />
</head>

<body>
	<h3 style="text-align: center;">JSONP demo</h3>
	<h4>What is JSONP?</h4>
	<ul>
		<li>JSONP is a method for sending JSON data without worrying about cross-domain issues.</li>
		<li>JSONP does not use the XMLHttpRequest object.</li>
		<li>JSONP uses the &lt;script&gt; tag instead</li>
	</ul>
	<div>Note:</div>
	<ul>
		<li>Requesting a file from another domain can cause problems, due
			to cross-domain policy.</li>
		<li>Requesting an external script from another domain does not
			have this problem</li>
	</ul>
	<h3>
		<strong>Ưu điểm và nhược điểm</strong>
	</h3>
	<p>Ưu điểm:</p>
	<ul>
		<li>Dễ dàng implement ở phía client</li>
		<li>Sử dụng được với tất cả các browser</li>
	</ul>
	<p>Nhược điểm</p>
	<ul>
		<li>Cần sự hỗ trợ phía server</li>
		<li>Chỉ gửi được GET request</li>
		<li>Không thể gửi header kèm theo</li>
	</ul>

	<h3>CHÚ Ý: Cần chạy project SJ_BootRESTful và cả project này (mỗi project trên 1 cổng khác nhau: 8084 và 8080)</h3>
	<div>Lấy employee = AJAX request tới 1 domain khác (localhost:8080/students) chắc chắn 
	sẽ bị lỗi: <b>Cross origin requests are only supported for protocol schemes: http, data, 
	chrome, chrome-extension, https.</b> (Mở inspect lên mà xem!)</div>
	<div class="btn" onclick="showStudentsAJAX()">Show employee</div>
	
	<h3>Có nhiều cách khắc phục:<br>Cách 1: sử dụng JSONP thay vì tạo đối tượng XMLHttpRequest:</h3>
	<div>JSONP: Ta sẽ chạy 1 script, với nội dung của script chính là nội dung của RESTful trả về</div>
	<div class="btn" onclick="showStudentsJSONP()">Show employee using JSONP</div>
	
	<div>Đôi khi ta muốn truyền thêm tham số tới server, và muốn chỉ rõ
	hàm callback, thì ta có thể làm như sau: </div>
	<div class="btn" onclick="showStudentsJSONP_moreDynamic()">Show employee using JSONP more dynamic</div>
	
	<h3>Cách 2: sử dụng CORS (Cross-Origin Resource Sharing): nó cho phép JavaScript ở một trang web 
	có thể tạo request lên một REST API được host ở một domain khác.</h3>
	<div>
		Cơ chế hoặt động của CORS: Xem tại đây:
		<a href="https://viblo.asia/p/cross-domain-ajax-requests-l5y8Rr52vob3#_2-cors-3">https://viblo.asia/p/cross-domain-ajax-requests-l5y8Rr52vob3#_2-cors-3</a><br>
		Tham khảo: <a href="https://codeaholicguy.com/2018/05/07/cors-la-gi/">https://codeaholicguy.com/2018/05/07/cors-la-gi/</a><br>
	</div>
	<div class="btn" onclick="showStudentsCORS()">Show employee using CORS</div>
	<div>Mở inspect, vào tab network sau đó click btn ở trên, sau đó trong tab network
	xuất hiện thêm 1 tên <b>demo_cors</b>. Click vào đó để xem Response Headers, sẽ thấy dòng:<br>
	Access-Control-Allow-Origin: http://localhost:8084 (chính là domain của client này)</div>
	
	<h3 style="color:#6db33f;">Kết quả trả về từ server:</h3>
	<div id="result"></div>
	
	<h3>Pre-flight requests</h3>
	<div>
		2 ví dụ dưới đây đều có Pre-flight requests, vì nó đều gửi đi 1
		custom header X-PINGOTHER. Pre-flight requests là những request được gửi
		trước những request thực sự để kiểm tra xem request thực sự có an toàn
		để gửi đi hay không.
	</div>
	<div>
		Pre-flight requests xảy ra khi:
		<ul>
			<li>request thực sự là 1 method bất kỳ trừ GET, POST, HEAD hoặc POST's request's content type is anything other than  application/x-www-form-urlencoded, multipart/form-data, or text/plain</li>
			<li>request contains any custom headers</li>
		</ul>
	</div>
	<div>
		<h4>Demo CORS POST method</h4>
		<div id="insert_result">
			<input type="text" name="name" id="st_name" placeholder="name" value="demo1">
			<input type="text" name="school" id="st_school" placeholder="school" value="demo2">
			<div class="btn" onclick="insertStudent()">Send!</div>
		</div>
	</div>
	<div>
		Mở inspect, tab network, chạy ví dụ trên, sẽ thấy 2 request giống nhau, đó là:
		insert_student(method: OPTIONS), và insert_student(method: POST). Điều này tương tự
		với ví dụ dưới. Nếu không thấy thì tắt Chrome đi sau đó bật lại sẽ thấy!
	</div>
	<div>
		<h4>Demo CORS DELETE method</h4>
		<div id="delete_result">
			<input type="text" name="id" id="st_id" placeholder="enter student's ID" value="123">
			<div class="btn" onclick="deleteStudent()">Delete!</div>
		</div>
	</div>
	<div>See more: <a href="https://spring.io/understanding/CORS">https://spring.io/understanding/CORS</a></div>
	<div>See more: <a href="https://butchiso.com/2018/06/cors-tro-ve-can-ban.html">Làm thế nào để sửa lỗi “CORS”</a></div>
	
	<h3>XSS demo</h3>
	<div>Chạy ví dụ sau trên IE, vì trên Chrome không chạy được do bảo mật hơn!</div>
	<form action="/test-xss" method="post">
		<input type="text" style="width: 300px;" name="enter_something" placeholder="enter something..." 
			value='<script>for(var i=0; i<10; i++) {alert("WTF " + i);}</script>'>
		<input type="submit">
	</form>
</body>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
<script type="text/javascript">
	// for(var i=0; i<10; i++) {alert("WTF " + i);}
	
	let result = document.getElementById("result");
	let insert_result = document.getElementById("insert_result");
	let delete_result = document.getElementById("delete_result");
	
	function showStudentsAJAX() {
		let xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				result.innerHTML = this.responseText;
			}
		};
		xhttp.open("GET", "localhost:8080/students", true);		//cross-origin: request tới 1 domain khác bị restrict!
		xhttp.send();
	}

	function showStudentsJSONP() {
		// create a script with source from another domain and run it
		
		var script = document.createElement("script");
	    script.type = "text/javascript";
	    script.src = "http://localhost:8080/demo_jsonp"; 
	    document.body.appendChild(script);
	    
	    // URL sẽ trả về 1 String có giá trị là:
	    // "clientFunction(data_in_here)"
	    // nghĩa là khi hàm showStudentsJSONP được gọi, thì sẽ tạo 1 script tới domain kia,
	    // domain đó trả về lời gọi hàm clientFunction() đã được định nghĩa ở trên,
	    // kq là hàm clientFunction() được gọi với tham số truyền vào
	    // là data từ server trả về
	}
	
	function showStudentsJSONP_moreDynamic() {
		var script = document.createElement("script");
	    script.type = "text/javascript";
	    script.src = "http://localhost:8080/demo_jsonp_dynamic?callback=myCallbackFucntionName&num_records=5"; 
	    document.body.appendChild(script);
	}
	
	function showStudentsCORS() {
		let xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var json = JSON.parse(this.responseText);
				appendDataToResultDiv(json, "#be00db");
			}
		};
		xhttp.open("GET", "http://localhost:8080/cors/demo_cors", true);
		xhttp.send();
	}
	
	// tham số json chính là data mà client muốn lấy từ server
	// hàm nàu append data đó vào thẻ có id=result
	function clientFunction(json) {
		appendDataToResultDiv(json);
	}
	
	// tham số json chính là data mà client muốn lấy từ server
	// hàm nàu append data đó vào thẻ có id=result
	function myCallbackFucntionName(json) {
		appendDataToResultDiv(json, "#2196F3");
	}
	
	function insertStudent() {
		let data = {
			name: document.getElementById("st_name").value,
			school: document.getElementById("st_school").value
		};
		console.log(data);
		console.log(JSON.stringify(data));
		
		let xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				insert_result.innerHTML = this.responseText;
			}
		};
		xhttp.open("POST", "http://localhost:8080/cors/insert_student");
		// PHẢI ĐỂ LÀ KIỂU application/json hoặc application/raw, hoặc text/plain, thì bên controller mới đọc được,
		// nếu để là kiểu application/x-www-form-urlencoded thì sẽ bị mã hóa và bên controller
		// không đọc được. VD: send data = {"name":"demo1","school":"demo2"} thì:
		// - kiểu application/json: bên controller sẽ nhận được là {"name":"demo1","school":"demo2"}
		// - kểu application/x-www-form-urlencoded: bên controller sẽ nhận được là %7B%22name%22%3A%22demo1%22%2C%22school%22%3A%22demo2%22%7D=
		// 
		// Nguyên nhân: application/x-www-form-urlencoded: the keys and values are encoded 
		// in key-value tuples separated by '&', with a '=' between the key and the value. 
		// Non-alphanumeric characters in both keys and values are percent encoded
		// (key và value được encode theo kiểu key=value và ngăn cách nhau bởi &, các kí tự
		// không phải chữ hoặc số sẽ được encode kiểu %)
		//
		// Dĩ nhiên dùng kiểu multipart/form-data cũng không được!
		xhttp.setRequestHeader("Content-Type", "text/plain");
		xhttp.setRequestHeader('X-PINGOTHER', 'pingpong');
		xhttp.send(JSON.stringify(data));
	}
	
	function deleteStudent() {
		let data = {
			id: document.getElementById("st_id").value
		};
		
		let xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				delete_result.innerHTML = this.responseText;
			}
		};
		xhttp.open("DELETE", "http://localhost:8080/cors/delete_student");
		xhttp.setRequestHeader("Content-Type", "text/plain");
		xhttp.setRequestHeader('X-PINGOTHER', 'pingpong');
		xhttp.send(JSON.stringify(data));
	}
	
	function appendDataToResultDiv(json, color="black") {
		for(var i=0; i<json.length; i++) {
			var divTag = document.createElement("div");
			divTag.setAttribute("style", "color: " + color)
			divTag.innerHTML = json[i].name + " - " + json[i].school;
		    result.appendChild(divTag);
		}
		
		var divTag = document.createElement("div");
		divTag.innerHTML = "============================"
	    result.appendChild(divTag);
	}
</script>

</body>
</html>