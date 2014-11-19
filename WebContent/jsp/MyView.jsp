<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@page import="org.springframework.web.servlet.i18n.SessionLocaleResolver"%>
<%@ page import="com.xplink.random_cm.datamodel.*"%>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	 <link rel="stylesheet" type="text/css" href="jsp/css/style.css" media="screen"/>
	 <link rel="stylesheet" type="text/css" href="jsp/css/keyword.css"></link>
	 <!-- <link rel="stylesheet" type="text/css" href="jsp/css/jquery-ui-1.9.2.custom.css"></link>
	 <link href="jsp/css/jquery.booklet.latest.css" type="text/css" rel="stylesheet" media="screen, projection, tv" /> -->
   	 <link rel="stylesheet" href="jsp/bootstrap-3.3.0/css/bootstrap.min.css">
     <link rel="stylesheet" type="text/css" href="jsp/css/animate.css" media="screen"/>
     
	<title>My view</title>
</head>
<body class="body" id="snow">
 	<%@ include file="Nav.jsp" %>   
<div class="container">

 	
	<% 
	   KeywordBean kb = (KeywordBean)request.getAttribute("keyword"); 
	   MemberBean mb = (MemberBean)session.getAttribute("member"); 
	   String eventDetailId = (String)request.getAttribute("eventDetailId");
	   request.setAttribute("keyword",kb);
	   String outkeyword = (String)request.getAttribute("keyout");
	   String memberId = request.getParameter("memberId");
	   String eventId = request.getParameter("eventId");
	   String eventName = (String)session.getAttribute("eventName");
	   String randomed =  (String)request.getAttribute("randomed");
 	%>
 	
 				<input id="eventDetailId" type="hidden" value="<%=eventDetailId%>"/>
				<input id="memberId" type="hidden" value="<%=memberId%>"/>
				<input id="eventId" type="hidden" value="<%=eventId %>"/>
				<input id="eventName" type="hidden" value="<%=eventName%>"/>	
 	
	<br />
	
<!-- Modal receive keyword -->
   
<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            <h4 class="modal-title" id="myModalLabel">Receive keyword</h4>
            </div>
            <div class="modal-body">
                <h3><%= outkeyword %></h3>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
    </div>
  </div>
</div>


	<%-- <input type="hidden" id="keywordReceive" name="keyword" value="<%= outkeyword %>"/> --%>
	<%if(randomed.equals("t")) {%>
		<a class="letter_closed" href="#" data-toggle="modal" data-target="#basicModal">
			 <img src="pics/letter_closed.png"  alt="received keyword !!!" width="128px" height="128px"/>
		</a>
		<!-- <a href="#" class="btn btn-primary btn-lg" data-toggle="modal"
   			data-target="#basicModal" style="margin-left:38%; margin-top: 10%;">Receive Keyword</a> -->
   			
	<%}%>
	<div id="noteLayer">
		<div id="noteTitle"><%=eventName%></div>
		<div id="noteContent">
			<textarea rows="6" cols="25" onkeyup="textLimit(this, 300);" id="noteText" disabled><%=kb.getInkeyword()%></textarea>
			<%if(randomed.equals("f")){ %>
			<!-- <div id="editButton" onclick="showEditNote()">edit</div> -->
			<input id="createBtn" type="submit" value="Edit" onclick="showEditNote()" class="btn btn-default"/>
			<div id="statusText"></div>		
			<%} %>
			<!-- <input type="button" value="Back to Event" class="btn btn-default" id="backBtnMyView"
	       		onClick="javascript:window.location.href = 'event-list.html;'"> -->
		</div>
	</div>
	

<div id="dialog-modal" title="Keyword"><%= outkeyword %></div>
</div> 



</body>

<script type="text/javascript" src="jsp/script/jquery-1.6.2.min.js"></script>
	 <!--  <script type="text/javascript" src="jsp/script/jquery-ui-1.9.2.custom.js"></script> --> 
	 <script type="text/javascript" src="jsp/script/browserdetect.js"></script>
	 <script type="text/javascript" src="jsp/script/prototype.js" ></script>
	 <script type="text/javascript" src="jsp/script/ajax.js" ></script>
	 <script type="text/javascript" src="jsp/script/locale.js"></script>
	 <script type="text/javascript" src="jsp/script/event.js"></script>
	 <script type="text/javascript" src="jsp/script/validate.js"></script>
	 <script type="text/javascript" src="jsp/script/keyword.js"></script>
	  <script src="jsp/bootstrap-3.3.0/js/jquery.min.js"></script>
    	 <script src="jsp/bootstrap-3.3.0/js/bootstrap.min.js"></script>
</html>