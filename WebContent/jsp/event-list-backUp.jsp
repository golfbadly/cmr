<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.xplink.random_cm.datamodel.*"%>      
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@page import="org.springframework.web.servlet.i18n.SessionLocaleResolver"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>List Event Page</title>
		<script type="text/javascript" src="jsp/script/jquery-1.6.2.min.js"></script>
	    <script type="text/javascript" src="jsp/script/event.js"></script>
	 	<script type="text/javascript" src="jsp/script/browserdetect.js"></script>
	 	<script src="http://connect.facebook.net/en_US/all.js"></script>    
		<link rel="stylesheet" type="text/css" href="jsp/css/style.css" media="screen"/>
		<script type="text/javascript" src="jsp/script/fbdialog.js"></script>
		<script type="text/javascript" src="jsp/script/locale.js"></script>
		<script type="text/javascript" src="jsp/script/prototype.js"></script>
	</head>
<body class="body">
		<% 
				ArrayList list = (ArrayList)request.getAttribute("list"); 
				List<String> dateStart =(List)request.getAttribute("dateStart");
				List<String> dateEnd = (List)request.getAttribute("dateEnd");
				MemberBean member = (MemberBean)session.getAttribute("member");
							 
	  	%>
<div id="page">
<div id="content">  
<%@ include file="nav-bar.jsp" %>

	<div class="head-text-eventlist">
	<h1> <label><fmt:message key="head.randomcm.eventlist"/> </label></h1>
	</div>
	<div class="add-delete">
	   <div class="add-event"><a href="addEvent.html?event=no"><img src="pics/icons/add.gif"></a></div>
	   <div class="delete-event"><img src="pics/icons/delete.gif" onclick="deleteEvent()"></a></div>	
	</div>
	<br>
	<br>
	<form method="POST" id="deleteFrm" name="deleteFrm" action="deleteEvent.html"> 
	<div id ="frm-event">
	<table border="1" class="list-table">
		<tr >
		<th class="td-style"></th><th class="td-style"><fmt:message key="list.randomcm.eventlist.eventName"/> </th><th class="td-style"><fmt:message key="list.randomcm.eventlist.startDate"/> </th><th class="td-style"><fmt:message key="list.randomcm.eventlist.endDate"/> </th><th class="td-style"><fmt:message key="list.randomcm.eventlist.keyword"/> </th><th class="td-style"><fmt:message key="list.randomcm.eventlist.invite"/> </th><th class="td-style"><fmt:message key="list.randomcm.eventlist.random"/> </th>
		<th class="td-style"><fmt:message key="list.randomcm.eventlist.edit"/></th>
		</tr ><%  EventBean event = new EventBean();
		          DateStart startDate = new DateStart();
		          DateEnd endDate = new DateEnd();
		          for (int i = 0; i < list.size(); i++) {
				 	 event = (EventBean)list.get(i);
				 	 startDate.setStartDate(dateStart.get(i)) ;
				 	 endDate.setEndDate(dateEnd.get(i));				 	
				 	%>				
		<tr >
		<td class="td-style" ><input type="checkbox" name="eventDel" value="<%= event.getId() %>" />  </td><td class="td-style"><%= event.getEventName() %> </td><td class="td-style">   <%= startDate.getStartDate() %></td><td class="td-style"><%= endDate.getEndDate() %></td>
		<td class="td-style"><a href="MyView.html?eventDetailId=<%= event.getEventDetailId() %>&eventId=<%=event.getId()%>&memberId=<%=member.getMemberid()%>&eventName=<%=event.getEventName()%>"><img src="pics/icons/keyword.gif"/></a></td>
		<td class="td-style" align="center">		
		<%if(member.getMemberid() == Integer.parseInt(event.getCreateBy())) { %>
		       <a href="#"><img src="pics/icons/invite.gif" onclick="showInvite(<%= event.getId() %>)"/></a>
		   	  </td>
		   <td class="td-style"><a href="Randoms.html?eventId=<%=event.getId()%>&memberName=<%=member.getName()%>&eventName=<%= event.getEventName() %>"><img src="pics/icons/gear.gif"/></a></td>	  
		   <td class="td-style"><a href="GetEventToedit.html?event=no&eventId=<%=event.getId()%>&eventName=<%= event.getEventName() %>"><img src="pics/icons/edit_small.gif"/></a></td>
<%-- 		   <td class="td-style"><%if( event.getReadyState() )%><a href="GetEventToedit.html?event=no&eventId=<%=event.getId()%>&eventName=<%= event.getEventName() %>"><img src="pics/icons/edit_small.gif"/></a></td> --%>
		   
		   	<%} %>	   		
		</tr>
		<% } %>
	
	</table>
	</div>
	</form>
	
	<br />

</div>  <!-- end div content   -->

<div id="copyright">
		Copyright&copy;2011
		<a class="brownLink" href="http://www.xp-link.com">XPLink</a>
		. All rights reserved.
</div>
</div>  <!-- end div page   -->


<div id="fb-root"></div>

</body>
</html>


