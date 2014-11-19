<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.xplink.random_cm.datamodel.*"%>      
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@page import="org.springframework.web.servlet.i18n.SessionLocaleResolver"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">	
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0">	 --> 		 	
		<link rel="stylesheet" type="text/css" href="jsp/css/style.css" media="screen"/>
		<link rel="stylesheet" type="text/css" href="jsp/css/animate.css" media="screen"/>
		<link rel="stylesheet" href="jsp/css/fontello.css">
   		<link href="jsp/bootstrap-3.3.0/css/bootstrap-responsive.css" rel="stylesheet">
		<link rel="stylesheet" href="jsp/bootstrap-3.3.0/css/bootstrap.min.css">
   		 
<title>Sent email page</title>
</head>

<body class="body" id="snow"> 
<%@ include file="Nav.jsp" %>
<div class="container">
	<form class="mail" method="GET" action="SendMail.html">
	
	<h2>Christmas Random.</h2>
	<div id="send-mail">
	Mail to <br><input type="text" name="mailTo" class="form-control"> 
	<br>
	Subject <br><input type="text" name="subject" class="form-control">
	<br>
	Text <br><textarea name="text" rows="4" cols="50" class="form-control"></textarea>
	<br>
	</div>
	<input type="submit" value="<fmt:message key="common.button.save"/>" class="btn btn-default" id="createBtn">
	<!-- <input type="button" value="Back to Event" class="btn btn-default" id="backBtn"
	       onClick="javascript:window.location.href = 'event-list.html;'"> -->
	</form>
</div>

</body>
</html>