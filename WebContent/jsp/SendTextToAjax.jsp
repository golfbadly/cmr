<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Sent Text To Ajax</title>
	<link rel="stylesheet" type="text/css" href="jsp/css/keyword.css" />
</head>
<body><center>
	
	<fieldset style="width:450px;">
	<legend><font size="2" color="blue"> Your profile </font></legend>
	<form name="frmProfile" action="StartUpdateMemberProfile.html" method="post">
	    <table>
	    <%--<tr> <td>      </td><td><input type="hidden" name="memberid" value="<%=mb.getMemberid()%>" /></td> </tr>--%>
		<tr> <td>Name:     </td><td><input type="text" size="35" name="namee" value="Sarawut" readonly /></td> </tr>
		<tr> <td>Surname:  </td><td><input type="text" size="35" name="surname" value="Pinthong" readonly /></td> </tr>
		<tr> <td>Email:    </td><td><input type="text" size="35" name="email" value="sarawut.pth@gmail.com" readonly /></td> </tr>
		<tr> <td>Username: </td><td><input type="text" size="35" name="username" value="sarawut" readonly /></td> </tr>
		<tr> <td>Password: </td><td><input size="35" type="password" name="password" value="sarawut" readonly /></td> </tr>
	    <tr> <td>Keyword:  </td><td><input size="35" type="text" name="keyword" value="" readonly /></td> </tr>
	    <tr> <td>           </td><td align="right">	                                     
							       <input type="submit" name="edit" value="Edit Profile"  />
		                    </td></tr>		               
	    </table>
	</form>
	</fieldset>	
	<br />
	<fieldset style="width:450px;">
	<legend><font size="2" color="blue">Insert keyword</font></legend>
	<form name="frmKeyword" action="Inputkeyword.html" method="post">
		<table>
			<tr>
			<td>Keyword :</td>
				<td><input type="text" name="keywords" size="35"></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="submit" onclick="return validate()" /></td>
				<td><input type="hidden" name="memberid" value="" /></td>
				<td><input type="hidden" name="username" value="" /></td>
				<td><input type="hidden" name="password" value="" /></td> 
			</tr>
		</table>
	</form>
	
</center>
</body>
</html>