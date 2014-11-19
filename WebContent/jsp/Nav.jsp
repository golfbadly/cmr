<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">		
   		<link href="jsp/bootstrap-3.3.0/css/bootstrap-responsive.css" rel="stylesheet">
		<link rel="stylesheet" href="jsp/bootstrap-3.3.0/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="jsp/css/style.css" media="screen"/> 	 
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation" id ="navBanner">
	<div class="container">
		<div class="navbar-header">
			<!-- <button type="button" class="navbar-toggle collapsed" id="Nav-menu" data-toggle="collapse" 
				data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button> -->   
			<a class="navbar-brand" id="Nav-brand">Christmas Random</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse" aria-expanded="false" style="height: 1px;">
	 		<form class="navbar-form navbar-right" id="Nav-menu" role="form">
	 		
						<%String local = (String) session.getAttribute("LOCALE");%>
						<% if(local != null) { %>
							<% if(local.equals("th")){ %>
								<a id="localelink_th" onclick="switchLang('th','event-list.html')" class="image-border"><img
								src="pics/icons/thai-flag.png" alt="Thai" title="Thai"/></a>
								 : <a id="localelink_en" onclick="switchLang('en','event-list.html')"><img
								src="pics/icons/eng-flag.png" alt="English" title="English" /></a>
							<%}else{ %>
								<a id="localelink_th" onclick="switchLang('th','event-list.html')"><img
								src="pics/icons/thai-flag.png" alt="Thai" title="Thai"/></a>
								 : <a id="localelink_en" onclick="switchLang('en','event-list.html')" class="image-border"><img
								src="pics/icons/eng-flag.png" alt="English" title="English"/></a>
							<%} %>
						<%}else{ %>
						<a id="localelink_th" onclick="switchLang('th','event-list.html')"><img
							src="pics/icons/thai-flag.png" alt="Thai" title="Thai" class="image-border"/></a>
							 : <a id="localelink_en" onclick="switchLang('en','event-list.html')" ><img
							src="pics/icons/eng-flag.png" alt="English" title="English"/></a>
						<%} %>
						
	 			<button type="button" class="btn btn-success" id="btnLogout"
	 			onClick="javascript:window.location.href = 'event-list.html;'"><fmt:message key="common.button.backEvent"/></button> 
	 		</form>
		</div>
	</div>
</nav>
</body>
</html>