<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<link href="jsp/bootstrap-3.3.0/css/bootstrap-responsive.css" rel="stylesheet">
<link rel="stylesheet" href="jsp/bootstrap-3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="jsp/css/style.css" />
<link rel="stylesheet" type="text/css" href="jsp/css/animate.css" media="screen"/>

<title>Register page</title>
</head>

<body class="body" id="snow">
	<%--String registerresult = (String)request.getAttribute("result");--%>
	<div>
		<div class="container">
			<div  id="formRegis">
				<h1>Register</h1>
			</div>
			<div >
				<form name="frm" action="Register.html" method="post">
				<br>
					<table id="userpass">
						<tr>
							<td class="col-sm-2 control-label"><p>Name</p></td>
							<td><input class="form-control" size="35" type="text"
								name="name1" /><br></td>
						</tr>
						<tr>
							<td class="col-sm-2 control-label"><p>Surname</p></td>
							<td><input class="form-control" size="35" type="text"
								name="surname" /><br></td>
						</tr>
						<tr>
							<td class="col-sm-2 control-label"><p>Email</p></td>
							<td><input class="form-control" size="35" type="text"
								name="email" /><br></td>
						</tr>
						<tr>
							<td class="col-sm-2 control-label"><p>Username</p></td>
							<td><input class="form-control" size="35" type="text"
								name="usr" /><br></td>
						</tr>
						<tr>
							<td class="col-sm-2 control-label"><p>Password</p></td>
							<td><input class="form-control" size="35" type="password"
								name="pwd" /><br></td>
						</tr>
						<tr>
							<td colspan="2" width="150" align="right">
							<input type="submit" value="submit" class="btn btn-default"
								onclick="return validate(name1,email,usr,pwd)" /> 
								<input type="reset" value="cancel" class="btn btn-default" /></td>
						</tr>
					</table>
					<!-- <div class="warning">* support only gmail</div> -->
				</form>
			</div>
			<!-- end div register -->
		</div>
		<!-- end div content -->

		<br><br><br><br>
	
	</div>
 <%@ include file="Foot.jsp"%>   
</body>
<script type="text/javascript" src="jsp/script/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="jsp/script/browserdetect.js" media="screen"></script>
<script type="text/javascript">
	function validate(names, email, usr, pwd) {

		if (names.value == "" || email.value == "" || usr.value == ""
				|| pwd.value == "") {
			alert("not enough data. please check your data.");
			return false;
		} else {
			var confirmdata = window.confirm("confirm your data.");
			if (confirmdata) {
				return true;
			} else
				return false;
		}
	}
</script>
<script src="jsp/bootstrap-3.3.0/js/jquery.min.js"></script>
<script src="jsp/bootstrap-3.3.0/js/bootstrap.min.js"></script>
</html>

























