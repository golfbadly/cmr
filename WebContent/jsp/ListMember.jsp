<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.xplink.random_cm.datamodel.MemberBean"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Listmember Page</title>
		<%  ArrayList list = (ArrayList)request.getAttribute("list"); 
		    MemberBean member = (MemberBean)session.getAttribute("member"); 
		    int eventId = Integer.parseInt((String)request.getAttribute("eventId"));	
		    String eventDetailId = (String)request.getAttribute("eventDetailId");
		 %>
		
		<script type="text/javascript" src="jsp/script/jquery-ui-1.9.2.custom.js"></script>
	 	<script type="text/javascript" src="jsp/script/browserdetect.js"></script>
		<link rel="stylesheet" type="text/css" href="jsp/css/style.css" media="screen"/>
	</head>
<body class="body">
<div id="page">
<div id="content"> 
	<div class="head-text-list">
	<h1> Happy Christmas day.</h1>
	</div>
	   <div class="member-list">Member list</div>
	<br />

	<table border="1" class="list-table">
		<tr >
		<th class="td-style">No.</th><th class="td-style">Name-Surname</th><th class="td-style">E-mail</th><th class="td-style">Sending Status</th>
		</tr ><% for (int i = 0; i < list.size(); i++) {%>
				<% 	MemberBean memberbean = (MemberBean)list.get(i); %>
				<%  String status="no";
					if(memberbean.getStatus().equals("1")){
						status="yes";
					}
				%>
		
		<tr >
		<td class="td-style" ><%= i+1%></td><td class="td-style"><%= memberbean.getName()%>    <%= memberbean.getSurname() %></td><td class="td-style"><%= memberbean.getEmail() %></td><td class="td-style" align="center"><%= status %></td>
		</tr>
		<% } %>
	
	</table>
		
		<div class="back-link">
		    <a href="javascript: history.go(-1)">Back</a>
		</div>
<!-- 
		<div class="logout">
		  <a href="Logout.html">Log out</a>
	    </div>
	  -->   
	<br />
	<!-- 
		<form name="rdm"  action="Randoms.html?eventId=<%= eventId %>" method="post">
	         <input type="submit" id="random" value="Random" >
	          alert("Random complete");
	    </form>
	    
	    <form name="sendmail"  action="Sentmail.html?eventId=<%= eventId %>" method="post">
	         <input type="submit" name="sendmails" value="SendMail" />
	          alert("Sendmail complete");
	    </form>
	  -->
</div>  <!-- end div content   -->
<div id="copyright">
		Copyright&copy;2011
		<a class="brownLink" href="http://www.xp-link.com">XPLink</a>
		. All rights reserved.
</div>
</div>  <!-- end div page   -->

</body>
</html>
































