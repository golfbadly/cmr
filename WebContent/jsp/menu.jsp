<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@page import="org.springframework.web.servlet.i18n.SessionLocaleResolver"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Christmas Random Menu</title>

		<script type="text/javascript" src="jsp/script/jquery-1.6.2.min.js"></script>
	 	<script type="text/javascript" src="jsp/script/browserdetect.js"></script>  
		<link rel="stylesheet" type="text/css" href="jsp/css/style.css" media="screen"/>
		<script type="text/javascript" src="jsp/script/locale.js"></script>
		<script type="text/javascript" src="jsp/script/prototype.js"></script>
</head>
<body class="body">
<div id="page">

<div id = "content">  
 		<%@ include file="nav-bar.jsp" %>
 		
 		
 		<br>
 		<br>
 		<a href="adsSlide.html">slideShow</a>
 		



	<div id="v-menu">
		<ul id="v-nav"  class="txt-menu">
			<li class="first"><a href="addEvent.html?event=no">Create Event</a></li>
			<li class="second"><a href="event-list.html">Event List</a></li>
		</ul>
	</div>

<div id="ads">
         <iframe src="adsSlide.html" width="468" height="60" scrolling="no">
  			<p>Your browser does not support iframes.</p>
		</iframe>
</div>

</div>  <!-- end div content   -->


<div id="copyright">
		Copyright&copy;2011
		<a class="brownLink" href="http://www.xp-link.com">XPLink</a>
		. All rights reserved.
</div>

</div>  <!-- end div page   -->


</body>
</html>