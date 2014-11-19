<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@page import="org.springframework.web.servlet.i18n.SessionLocaleResolver"%>
<%@ page import="com.xplink.random_cm.datamodel.*"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <script type="text/javascript" src="jsp/script/jquery-ui-1.9.2.custom.js"></script>
	 <script type="text/javascript" src="jsp/script/browserdetect.js"></script>
	<link rel="stylesheet" type="text/css" href="jsp/css/style.css" media="screen"/>
	 <script type="text/javascript" src="jsp/script/prototype.js" ></script>
	 <script type="text/javascript" src="jsp/script/ajax.js" ></script>
	 <script type="text/javascript" src="jsp/script/locale.js"></script>
	 <script type="text/javascript" src="jsp/script/event.js"></script>
	 <script type="text/javascript" src="jsp/script/validate.js"></script>
	
	<title>My view</title>

</head>
<body class="body">
<div id="page">
<div id="content"> 

<%@ include file="nav-bar.jsp" %>

	<% 
	   KeywordBean kb = (KeywordBean)request.getAttribute("keyword"); 
	   MemberBean mb = (MemberBean)session.getAttribute("member"); 
	   String eventDetailId = (String)request.getAttribute("eventDetailId");
	   request.setAttribute("keyword",kb);
	   String outkeyword = (String)request.getAttribute("keyout");
	   String memberId = request.getParameter("memberId");
	   String eventId = request.getParameter("eventId");
	   String eventName = (String)session.getAttribute("eventName");
 	%>
 	
 <%-- <%@ include file="language.jsp" %> --%>

	<div class="head-text">
		<h1><fmt:message key="head.randomcm.profile"/><br> <%= eventName %></h1>
	</div>
	
	<br />
	<div class="fieldset-view">
	<fieldset >
	<legend><font size="2" color="blue"><fmt:message key="profile.randomcm.title.keyword"/></font></legend>
	<form name="frmKeyword" action="Inputkeyword.html?eventDetailId=<%= eventDetailId%>&memberId=<%=memberId%>&eventId=<%=eventId %>&eventName=<%=eventName%>" method="post">
		<table>
			 <tr> <td><div class="title-input-noborder"></div></td><td><textarea class="display-text" rows="2" cols="30" name="keyword" readonly><%=kb.getInkeyword()%></textarea></td> </tr>
	   
			<tr>
			<td><div class="title-input"><fmt:message key="profile.randomcm.keyword"/></div></td>
				<td><textarea rows="2" cols="30" name="keywords" class="input-text-keyword" ></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="<fmt:message key="common.button.save"/>" onclick="return validate()" /></td>
				<td><input type="hidden" name="memberid" value="<%=mb.getMemberid()%>" /></td>
				<td><input type="hidden" name="username" value="<%=mb.getUsername()%>" /></td>
				<td><input type="hidden" name="password" value="<%=mb.getPassword()%>" /></td> 
			</tr>
		</table>
	</form>
	</fieldset>
	
	<fieldset >
	<legend><font size="2" color="blue"><fmt:message key="profile.randomcm.title.received"/></font></legend>
	<form name="frmKeyword" action="Inputkeyword.html?eventDetailId=<%= eventDetailId%>&memberId=<%=memberId%>&eventId=<%=eventId %>" method="post">
		<table>
			 <tr> <td><div class="title-input"><fmt:message key="profile.randomcm.event"/> </div></td><td><input class="input-text-noborder" type="text" name="event" value="<%=eventName%>" readonly /></td> </tr>
	   
			<tr><td><div class="title-input"><fmt:message key="profile.randomcm.keyword"/> </div></td>
				<td><textarea class="display-text" rows="2" cols="30" readonly><%= outkeyword %></textarea></td>
			</tr>
		</table>
	</form>
	</fieldset>
	
	</div>
	<div class="fieldset-view">
	<fieldset>
	<legend><font size="2" color="blue"><fmt:message key="profile.randomcm.title.info"/></font></legend>
	<form name="frmProfile" action="StartUpdateMemberProfile.html?eventDetailId=<%= eventDetailId%>&username=<%=mb.getUsername()%>" method="post">
	    <table>
	    <%--<tr> <td>          </td><td><input type="hidden" name="memberid" value="<%=mb.getMemberid()%>" /></td> </tr>--%>
		<tr> <td><div class="title-input"><fmt:message key="profile.randomcm.info.name"/></div> </td><td><input class="input-text-noborder" type="text" name="name" value="<%= mb.getName()%>" readonly /></td> </tr>
		<tr> <td><div class="title-input"><fmt:message key="profile.randomcm.info.surname"/></div> </td><td><input class="input-text-noborder" type="text" name="surname" value="<%=mb.getSurname()%>" readonly /></td> </tr>
		<tr> <td><div class="title-input"><fmt:message key="profile.randomcm.info.email"/></div>  </td><td><input class="input-text-noborder" type="text" name="email" value="<%=mb.getEmail()%>" readonly /></td> </tr>
		<!-- <tr> <td><div class="title-input">Username: </div> </td><td><input class="input-text" type="text" name="username" value="<%=mb.getUsername()%>" readonly /></td> </tr>
		<tr> <td><div class="title-input">Password: </div></td><td><input class="input-text" type="text" size="35" type="password" name="password" value="<%=mb.getPassword()%>" readonly /></td> </tr>  -->
	    <tr> <td>           </td><td align="right">	                                     
							       <input type="submit" name="edit" value="<fmt:message key="common.button.edit.profile"/>"  />
		                    </td></tr>		               
	    </table>
	</form>
	</fieldset>	
	</div> <!-- end div fieldset-view -->
	
	<div >
		<div class="member-list">	
	    		<input type="button" value="<fmt:message key="common.button.memberlist"/>" onclick="listMember('<%= eventDetailId %>')">
        </div>
		<div >
	    		<input type="button" id="backEvent" value="<fmt:message key="common.button.backEvent"/>">
		</div>
	</div>
	<!-- <div class="logout">
		<a href="Logout.html">Log out</a>
	</div>  -->

</div> <!-- end div content -->
<div id="copyright">
Copyright&copy;2011
<a class="brownLink" href="http://www.xp-link.com">XPLink</a>
. All rights reserved.
</div>
</div> <!-- end div page -->
</body>
</html>