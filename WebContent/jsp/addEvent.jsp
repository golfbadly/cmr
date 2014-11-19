<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@page import="org.springframework.web.servlet.i18n.SessionLocaleResolver"%>
	
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<link href="jsp/bootstrap-3.3.0/css/bootstrap-responsive.css" rel="stylesheet">
<link rel="stylesheet" href="jsp/bootstrap-3.3.0/css/bootstrap.min.css">		  
<link rel="stylesheet" type="text/css" href="jsp/css/style.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="jsp/css/animate.css" media="screen"/>
<link rel="stylesheet" href="jsp/css/jquery-ui-1.9.2.custom.css">
 
<title>Create event</title>
</head>
<body class="body" id="snow">
<%@ include file="Nav.jsp" %>
<div class="container">

<img src="pics/raffle-box.png" class="img-responsive" id="rafflebox"/>

	<h1> 
		<label class="head-text-eventlist">
			<fmt:message key="create.randomcm.title.event"/> 
		</label>
	</h1>

<div>
	<form id="formEvent" name="formEvent" action="addEvent.html" >
	<table id="table-add">
			<tr> 
			    <td class="title-input" ><fmt:message key="edit.randomcm.event.name" /></td>
			    <td><input class="form-control" size="35" type="text" name="eventName" /> </td>
			</tr>
			<tr> 
				<td colspan="2" width="150">
				    <input type="hidden" name="event" />
				    <div id="addBtn">
					<input id="backAdd" type="submit" value="<fmt:message key="common.button.save"/>" onclick="return validateName()" 
					class="btn btn-default"/>
					<!-- <input type="button" value="Back to Event" class="btn btn-default" id="backBtn"
	       			onClick="javascript:window.location.href = 'event-list.html;'"> -->
	       			</div>
				</td>
				
			</tr>
	</table>
	<div class="warning">
	</div>
	</form>
</div>  
</div>
<div id="foot">
<%@ include file="Foot.jsp"%>
</div> 
</body>
<!-- <script type="text/javascript" src="/jsp/script/fbdialog.js"></script> -->
	 	<script type="text/javascript" src="jsp/script/event.js"></script>
	    <script type="text/javascript" src="jsp/script/validate.js"></script>
	     <script type="text/javascript" src="jsp/script/prototype.js" ></script>
	 	<script type="text/javascript" src="jsp/script/ajax.js" ></script>
	 	<script type="text/javascript" src="jsp/script/locale.js"></script>
	 	<script type="text/javascript" src="jsp/script/jquery-ui-1.9.2.custom.js"></script>
	 	<script type="text/javascript" src="jsp/script/browserdetect.js"></script>
	 	<script src="//connect.facebook.net/en_US/all.js"></script>  
	 	<script src="jsp/bootstrap-3.3.0/js/jquery.min.js"></script>
    	 <script src="jsp/bootstrap-3.3.0/js/bootstrap.min.js"></script>
</html>