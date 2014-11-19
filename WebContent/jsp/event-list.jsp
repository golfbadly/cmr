<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.xplink.random_cm.datamodel.*"%>      
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@page import="org.springframework.web.servlet.i18n.SessionLocaleResolver"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0">	 --> 
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>List Event Page</title>	 	
		<link rel="stylesheet" type="text/css" href="jsp/css/style.css" media="screen"/>
		<link rel="stylesheet" type="text/css" href="jsp/css/animate.css" media="screen"/> 
	    <link rel="stylesheet" href="jsp/css/fontello.css"> 
   		<link href="jsp/bootstrap-3.3.0/css/bootstrap-responsive.css" rel="stylesheet">
		<link rel="stylesheet" href="jsp/bootstrap-3.3.0/css/bootstrap.min.css">
		
		<title>List Event Page</title>
	</head>

<body class="body" id="snow">
		<% 
				ArrayList list = (ArrayList)request.getAttribute("list"); 
				List<String> dateStart =(List)request.getAttribute("dateStart");
				List<String> dateEnd = (List)request.getAttribute("dateEnd");
				MemberBean member = (MemberBean)session.getAttribute("member");							 
	  	%>
<%@ include file="Nav.jsp"%> 
<div class="container" id="list"> 

	<!-- <img style="width:100%" src="./pics/deer.gif" />  -->

	<!-- BOX -->	
		<div>
		   <img src="pics/raffle-box.png" class="img-responsive" id="rafflebox"/>
		</div>
	<!-- BOX -->
	
	<!-- <input type="button" value="Shake" onclick="function()" />  -->
	
	<h1> 
		<label class="head-text-eventlist">
			<fmt:message key="head.randomcm.eventlist"/> 
		</label>
	</h1>	
	<div>
	   <div class="add-event"><a href="addEvent.html?event=no"><img src="pics/icons/add.gif"></a></div>
	   <div class="delete-event"><img src="pics/icons/delete.gif" onclick="deleteEvent()"></div>	
	</div>
	<br>
	<br>
	<form method="POST" id="deleteFrm" name="deleteFrm" action="deleteEvent.html">                                    
    <table id="table-event" class="table table-hover">
    	<thead>
		<tr style="border:0">
			<th class="th-style"></th>
			<th class="th-style"><fmt:message key="list.randomcm.eventlist.eventName"/></th>
			<th class="th-style"><fmt:message key="list.randomcm.eventlist.startDate"/> </th>
			<th class="th-style"><fmt:message key="list.randomcm.eventlist.endDate"/> </th>
			<th class="th-style"><fmt:message key="list.randomcm.eventlist.keyword"/> </th>
			<th class="th-style"><fmt:message key="list.randomcm.eventlist.invite"/> </th>
			<th class="th-style"><fmt:message key="list.randomcm.eventlist.random"/> </th>
			<th class="th-style"><fmt:message key="list.randomcm.eventlist.edit"/></th>
		</tr >
		</thead>
		<%  EventBean event = new EventBean();
		          DateStart startDate = new DateStart();
		          DateEnd endDate = new DateEnd();
		          for (int i = 0; i < list.size(); i++) {
				 	 event = (EventBean)list.get(i);
				 	 startDate.setStartDate(dateStart.get(i)) ;
				 	 endDate.setEndDate(dateEnd.get(i));				 	
				 	%>
		<tbody>		
	    <tr>
	    	<td class="td-style" >
	    	<%if(member.getMemberid() == Integer.parseInt(event.getCreateBy())){ %>
	    	     <input type="checkbox" name="eventDel" value="<%= event.getId() %>" />  
	    	<%} %>
	    	</td>
	    	<td class="td-style"><%= event.getEventName() %> </td>
	    	<td class="td-style">   <%= startDate.getStartDate() %></td>
	    	<td class="td-style"><%=endDate.getEndDate() %></td>
	    	
<!-- 	    	###keyword Icon -->
	    	<td class="td-style">
	    		<a href="MyView.html?eventDetailId=<%=event.getEventDetailId()%>&eventId=<%=event.getId()%>&memberId=<%= member.getMemberid() %>&eventName=<%=event.getEventName()%>&randomed=<%=event.getRandomed()%>">
	    	                             <%if(event.getRandomed().equals("f")) { %>	
	    	                               <img src="pics/icons/keyword.gif"/>
	    	                             <%} else{%>
		   	        						<img src="pics/icons/NewYearletter.png"/>
		   	       	 					 <%} %>	    	                              
	    	                           </a>
	    	</td>
	    	<td class="td-style">
<!-- 	    	###invite Icon -->
				<%if(member.getFB().equals("T")){ 	   					
					  if(event.getRandomed().equals("f")) { %>
		                  <a href="#"><img src="pics/icons/invite.gif" onclick="showInvite(<%= event.getId() %>)" /></a>
		            <%}
				  }else if(member.getFB().equals("F")){ 		
		              if(event.getRandomed().equals("f")) { %>      
		                  <a href="CallMail.html"><img src="pics/icons/email.gif" /></a>
		            <%} 
		          } %>  		   			

		   	  </td>
		   	  <td class="td-style">
<!-- 	    	###Random Icon -->
					<%if(member.getMemberid() == Integer.parseInt(event.getCreateBy())) { %>	 
		   	  			<%if(event.getReadyState()&&event.getRandomed().equals("f")) { %>
		   	        		<a href="Randoms.html?eventId=<%=event.getId()%>&memberName=<%=member.getName()%>&memberId=<%= member.getMemberid() %>&eventName=<%= event.getEventName() %>&eventDetailId=<%= event.getEventDetailId() %>">
		   	        		<img src="pics/icons/gear.png" onclick="shakeBox()" /><!--i class="iconG-shuffle"></i--></a>
		   	        	<%} else if(event.getReadyState()&&event.getRandomed().equals("t")){%>
		   	        		
		   	        	<%} else{%>
		   	        		disable
		   	       	 	<%} %>
		   	       	 <%} %>		   	        
		   	  </td>
		   	  <td class="td-style">
		   	  		<%if(member.getMemberid() == Integer.parseInt(event.getCreateBy())&&event.getRandomed().equals("f")) { %>	
		   	  			<a href="GetEventToedit.html?event=no&eventId=<%=event.getId()%>&eventName=<%= event.getEventName() %>">
		   	  			<img src="pics/icons/edit_small.gif"/></a>
		   	  		<%} %>
		   	  </td><%} %>	  	    	
	    </tr>
	    <tbody>
	    <tfoot>
	        <tr height="12px">
	              <td colspan = "8"></td>
	        </tr>
		</tfoot>
		</table>
	</form>
					
</div>  
<%@ include file="Foot.jsp"%> 
</body>
	 	<script type="text/javascript" src="jsp/script/jquery-1.6.2.min.js"></script> 
		<script type="text/javascript" src="jsp/script/jquery-ui-1.9.2.custom.js"></script>
        <script src="jsp/bootstrap-3.3.0/js/jquery.min.js"></script>
    	<script src="jsp/bootstrap-3.3.0/js/bootstrap.min.js"></script>
    	<script type="text/javascript" src="jsp/script/fbdialog.js"></script>
		<script type="text/javascript" src="jsp/script/locale.js"></script>
		<script type="text/javascript" src="jsp/script/prototype.js"></script>
	    <script type="text/javascript" src="jsp/script/event.js"></script>
	 	<script type="text/javascript" src="jsp/script/browserdetect.js"></script>
	 	<script src="//connect.facebook.net/en_US/all.js"></script>   
</html>


