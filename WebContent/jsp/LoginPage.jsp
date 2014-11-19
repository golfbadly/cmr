<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--%@ page import="com.xplink.random_cm.servlets.AjaxLogin"%-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<link href="jsp/bootstrap-3.3.0/css/bootstrap-responsive.css" rel="stylesheet">
<link rel="stylesheet" href="jsp/bootstrap-3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="jsp/css/animate.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="jsp/css/style.css" />

<title>Christmas Random</title>
</head>
<body class="body" id="snow">

	<%
		String loginresult = (String) request.getAttribute("result");
	%>

		<div class="container">
			<div>
				<h1>Welcome to Christmas random.</h1>
				<br>
			</div>
	
			<div>
				<br>
				<form name="frmLogin" action="Login.html">
					<table  id="table-login">
						<tr>
							<td class="col-sm-2 control-label"><p
									id="userpass">Username</p></td>
							<td><input class="form-control" type="text" name="username"
								class="username" /><br></td>
						</tr>
						<tr>
							<td class="col-sm-2 control-label"><p
									id="userpass">Password
								<p></td>
							<td><input class="form-control" type="password"
								name="password" class="password" /><br></td>
						</tr>
						<tr>
							<td colspan="2" align="right"><input type="submit"
								class="btn btn-default" value="Login"
								onclick="return validate()" /> <input type="reset"
								class="btn btn-default" value="cancel" /></td>
						</tr>
						<tr>
						</tr>
						<tr>
							<td>
								<div id="fb-root"></div>
							</td>
							<td colspan="2" align="right"><br>
							<a href="CallRegister.html" class="register" id="userpass">Register</a></td>
						</tr>
					</table>

				</form>
			</div>
	
			<!-- end div login-form -->

			<%
				if (loginresult == "false") {
			%>
			<script type="text/javascript">
				alert("username or password incorrect ");
			</script>
			<%
				}
			%>
	</div>
 <%@ include file="Foot.jsp"%>     
</body>

<script type="text/javascript"src="jsp/script/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="jsp/script/browserdetect.js"></script>
<script src="jsp/bootstrap-3.3.0/js/jquery.min.js"></script>
<script src="jsp/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function validate() {

		var username = document.frmLogin.username.value;
		var password = document.frmLogin.password.value;

		if (username == "" || password == "") {
			window.alert("please fill username or password.");
			return false;
		} else {
			return true;
		}
	}
</script>

</html>








