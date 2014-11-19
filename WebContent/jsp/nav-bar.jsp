<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		
   		<link href="jsp/bootstrap-3.3.0/css/bootstrap-responsive.css" rel="stylesheet">
		<link rel="stylesheet" href="jsp/bootstrap-3.3.0/css/bootstrap.min.css">
    	 
<body>
<div class="container" id="userpass">
 	<div id = "banner">
 		<div class="nav-title"><p>Christmas Random</p></div>
 	</div>
 	<div id = "nav-menu">
 		<ul>
			<li><div class="nav-title"><a href="Logout.html">Logout</a></div></li>
			<li><div class="nav-title"><a href="event-list.html">Event</a></div></li>
			<li>
			<div id="language">
					
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
	
			</div>
			</li>
		</ul>
	</div>
</div>  

</body>
</html> --%>