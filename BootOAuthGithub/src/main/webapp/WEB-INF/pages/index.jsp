<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width" />
<base href="/" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Chat đê!</title>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/img/favicon.png">
<style type="text/css">
</style>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />
</head>

<noscript>
	<meta http-equiv="refresh" content="0; url=/noJS" />
	<style type="text/css">
div {
	display: none;
}
</style>
</noscript>
<body>

	<h1>Login</h1>
	<div class="container unauthenticated">
		<div>
			With Facebook: <a href="/login/facebook">click here</a>
		</div>
		<div>
			With Github: <a href="/login/github">click here</a>
		</div>
	</div>
	<div class="container authenticated" style="display: none">
		Logged in as: <span id="user"></span>
		<div>
			<button onClick="logout()" class="btn btn-primary">Logout</button>
		</div>
	</div>
	<!-- <script type="text/javascript" src="/webjars/js-cookie/js.cookie.js"></script> -->
	<script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
	<script type="text/javascript">
          $.ajaxSetup({
            beforeSend : function(xhr, settings) {
              if (settings.type == 'POST' || settings.type == 'PUT'
                  || settings.type == 'DELETE') {
                if (!(/^http:.*/.test(settings.url) || /^https:.*/
                    .test(settings.url))) {
                  // Only send the token to relative URLs i.e. locally.
                  xhr.setRequestHeader("X-XSRF-TOKEN", Cookies
                      .get('XSRF-TOKEN'));
                }
              }
            }
          });
          $.get("/user", function(data) {
            $("#user").html(data.name);
            $(".unauthenticated").hide();
            $(".authenticated").show();
          });
          var logout = function() {
            $.post("/logout", function() {
              $("#user").html('');
              $(".unauthenticated").show();
              $(".authenticated").hide();
            })
            return true;
          }
        </script>
</body>
</body>
</html>