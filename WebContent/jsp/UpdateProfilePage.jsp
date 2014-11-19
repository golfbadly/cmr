<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@page import="org.springframework.web.servlet.i18n.SessionLocaleResolver"%>
<%@ page import="com.xplink.random_cm.datamodel.MemberBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="jsp/script/jquery-ui-1.9.2.custom.js"></script>
	 	<script type="text/javascript" src="jsp/script/browserdetect.js"></script>
		<link rel="stylesheet" type="text/css" href="jsp/css/style.css" media="screen"/>
		<script type="text/javascript" src="jsp/script/locale.js"></script>
		<script type="text/javascript" src="jsp/script/prototype.js" ></script>
	 	<script type="text/javascript" src="jsp/script/ajax.js" ></script>
	 	
<title>Update Profile</title>
</head>

<body class="body">
<div id="page">
<div id="content">
<%
MemberBean mb = (MemberBean) session.getAttribute("member");
String memberid = Integer.toString(mb.getMemberid());
String eventDetailId = (String)request.getAttribute("eventDetailId");
 %> 

 <br/>
<br />
<div class="head-text-edit">
	<h1><fmt:message key="update.randomcm.profile.title.profile"/></h1>
</div>

<br />
<form name="frmProfile" action="UpdateMemberProfile.html?eventDetailId=<%=eventDetailId %>" method="post">
<input type="hidden" name="memberid" value="<%=memberid%>" />
<table class="fieldset-view">
	<tr>
		<td class="title-input"><fmt:message key="update.randomcm.profile.name"/></td>
		<td><input class="input-text" type="text" name="name" value="<%=mb.getName()%>" /></td>
	</tr>
	<tr>
		<td class="title-input"><fmt:message key="update.randomcm.profile.surname"/></td>
		<td><input class="input-text" type="text" name="surname"
			value="<%=mb.getSurname()%>" /></td>
	</tr>
	<tr>
		<td class="title-input"><fmt:message key="update.randomcm.profile.email"/></td>
		<td><input class="input-text" type="text" name="emails" value="<%=mb.getEmail()%>" /></td>
	</tr>
<!--	<tr>
		<td class="title-input">Username:</td>
		<td ><input class="input-text" type="hidden" name="usernames"
			value="<%=mb.getUsername()%>" /></td>
	</tr>
	<tr>
		<td class="title-input">Password:</td>
		<td ><input class="input-text" type="hidden" name="passwords"
			value="<%=mb.getPassword()%>" /></td>
	</tr>
-->
	<tr>
		<td>	
		<div class="back-link">
		    <a href="javascript: history.go(-1)"><fmt:message key="common.back.maun"/></a>
		</div></td>
		<td align="right" class="title-input"><input type="submit" value="<fmt:message key="common.button.save"/>"
			onclick="return validate()" /> <!--input type="reset" name="cancle" value="cancle"  /-->
		</td>
	</tr>
</table>
</form>

<script type="text/javascript">
	
		
		function validate(){

		var name=document.frmProfile.namee.value;
		var email=document.frmProfile.emails.value;
		var usr=document.frmProfile.usernames.value;
		var pwd=document.frmProfile.passwords.value;	
		
			if(name==""||email==""||usr==""||pwd==""){
				alert("not enough data ! please check your data and submit again.");
				return false;
			}
			else{
				var confirmdata = window.confirm("confirm save data ?");
				if(confirmdata)
					return true;
				else
					return false;
			}
		}
	</script>
</div> <!-- end div content -->
    <div id="copyright">
		Copyright&copy;2011
		<a class="brownLink" href="http://www.xp-link.com">XPLink</a>
		. All rights reserved.
	</div>
</div> <!-- end div page -->
</body>
</html>
