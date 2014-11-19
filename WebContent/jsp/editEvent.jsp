<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@page import="org.springframework.web.servlet.i18n.SessionLocaleResolver"%>
<%@ page import="com.xplink.random_cm.datamodel.*"%> 

<html>
<head>
	 <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<!--  <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
	 <link href="jsp/bootstrap-3.3.0/css/bootstrap-responsive.css" rel="stylesheet">
	 <link rel="stylesheet" href="jsp/bootstrap-3.3.0/css/bootstrap.min.css">
	 <link rel="stylesheet" type="text/css" href="jsp/css/animate.css" media="screen"/>
	 <link rel="stylesheet" href="jsp/css/jquery-ui-1.9.2.custom.css">
	 <link rel="stylesheet" type="text/css" href="jsp/css/style.css" media="screen"/>

<title>Edit event</title>
</head>

<body class="body" id="snow">
<%@ include file="Nav.jsp" %>
<% 
	EventBean eventdata = (EventBean)request.getAttribute("eventdata");
	MemberBean member = (MemberBean)session.getAttribute("member");
	String startDate = (String)request.getAttribute("startDate");
	String endDate = (String)request.getAttribute("endDate");
	String registerresult = (String)request.getAttribute("result"); 
%>
<div class="container">

    <div>
		   <img src="pics/raffle-box.png" class="img-responsive" id="rafflebox"/>
	</div>


	<h1> 
		<label class="head-text-eventlist">
			<fmt:message key="head.randomcm.editevent"/> 
		</label>
	</h1>

<div class="register">
<input type="hidden" id="flag" value="flag....value"/>

	<form id="formImage" name="formImage" action="editEvent.html?event=edit&eventId=<%=eventdata.getId()%>&memberId=<%= member.getMemberid() %>&imgType=<%=eventdata.getImgType() %>" method="post" enctype="multipart/form-data" commandName="eventBean">
	<table>
			<tr> 
			     <td class="title-input" ><fmt:message key="edit.randomcm.event.name"/></td>
			     <td><input class="form-control" id="eventName" class="input-text" size="35" type="text" name="eventName" value="<% if(eventdata.getEventName()!=null) {%><%=eventdata.getEventName() %> <%} %> "/></td> 
			</tr>
			<tr> 
			     <td class="title-input"><fmt:message key="edit.randomcm.event.desc"/></td>
			     <td align="left"><textarea class="form-control" id="desc" name="desc" rows="2" cols="20" onkeyup="textLimit(this, 200);"><%if(eventdata.getDesc()!=null) {%><%=eventdata.getDesc() %> <%} %></textarea></td>
			</tr>
			<tr>
				<td class="title-input"><fmt:message key="edit.randomcm.event.startDate"/> </td>
				<td align="left"><input class="btn btn-default" type="text" name="dateStart" id="dateStart"  value="<% if(eventdata.getDateStart()!=null){%><%=startDate %> <%} %>" readonly/></td>
			</tr>
			<tr>
			    <td class="title-input"><fmt:message key="edit.randomcm.event.endDate"/></td>
				<td align="left"><input class="btn btn-default" type="text" name="dateEnd" id="dateEnd" value="<% if(eventdata.getDateEnd()!=null)  {%><%=endDate %> <%} %>" readonly/></td>
			</tr>
			<tr>
			    <td id="edit-event-submit" colspan="3" align="middle">
				    <input type="hidden" name="event" />
					<input class="btn btn-default" type="submit" value="<fmt:message key="common.button.save"/>" onclick="return validateEvent()" />
					<%-- <input class="btn btn-default" id="backToEvent" type="button" value="<fmt:message key="common.button.backEvent"/>" /> --%>
				</td>
			</tr>
	</table>
	<div class="warning">
	</div>
	</form>
</div>  

</div>   
 <%@ include file="Foot.jsp"%>   	 
</body>

     <script type="text/javascript" src="jsp/script/jquery-1.6.2.min.js"></script>   
	 <script type="text/javascript" src="jsp/script/jquery-ui-1.9.2.custom.js"></script> 
 	 <script type="text/javascript" src="jsp/script/ajax.js" ></script>
 	 <script type="text/javascript" src="jsp/script/locale.js"></script>
 	 <script type="text/javascript" src="jsp/script/event.js"></script> 
 	 <script type="text/javascript" src="jsp/script/prototype.js" ></script>
 	 <script type="text/javascript" src="jsp/script/browserdetect.js"></script>
 	 <script type="text/javascript" src="jsp/script/validate.js"></script>
 	 
 	 <!--  Bootstrap -->
    	 <script src="jsp/bootstrap-3.3.0/js/jquery.min.js"></script>
    	 <script src="jsp/bootstrap-3.3.0/js/bootstrap.min.js"></script>
</html>