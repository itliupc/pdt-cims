<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<title>Shiro</title> 
	<link href="static/css/login.css" type="text/css" rel="stylesheet" /> 
	<script>
		function doLogin(){
			window.location.href="index.html";
			/* var username = $("input[name='username']").val();
			var password = $("input[name='password']").val();
			 $.ajax({
			      url: '/ajaxLogin',
			      dataType: "json",
			      data: {"username": username,"password":password},
			      type: "POST",
			      async:false,
			      success: function (data) {
			    	 sessionStorage.setItem("contentPath",data.contentPath);
			    	 sessionStorage.setItem("username",data.username);
			    	 sessionStorage.setItem("sessionId",data.sessionId);
			    	 window.location.href="/index";
			     }
			  }); */
		}
		
	</script>
</head> 
<body> 
	<div class="login">
	    <div class="message">登录</div>
	    <div id="darkbannerwrap"></div>
	    
	    <form action="dologin" method="post">
			<input name="username" placeholder="用户名" type="text" />
			<hr class="hr15" />
			<input name="password" placeholder="密码" type="password" />
			<hr class="hr15" />
			<input value="登录" style="width:100%;" type="submit" />
			<hr class="hr20" />
		</form>
	</div>
</body>
</html>