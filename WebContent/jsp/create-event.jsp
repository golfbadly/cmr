<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	 <script type="text/javascript" src="jsp/script/jquery-ui-1.9.2.custom.js"></script>
	 <script type="text/javascript" src="jsp/script/browserdetect.js"></script>
	 <link rel="stylesheet" type="text/css" href="jsp/css/style.css" media="screen"/>
 	 <script src="jsp/script/datetimepicker_css.js"></script>
 	 <script type="text/javascript" src="jsp/script/fbdialog.js"></script>
     <script src="//connect.facebook.net/en_US/all.js"></script>    
<title>Create event.</title>
</head>
<body class="body">
<div id="fb-root"></div>
	<%--String registerresult = (String)request.getAttribute("result");--%>
<div id="page">
<div id="content">
<div class="head-text">
	<h1>Create event</h1>
</div>
<div class="register">
	<form name="frm" action="create-event.html" method="post">
	<table>
			<tr> <td class="title-input" >Event name:</td><td><input class="input-text" size="35" type="text" name="eventName" />  </td> </tr>
			<tr> <td class="title-input">Start date:  </td><td align="left"><input size="10" type="text" name="dateStart" id="dateStart" /> <img src="pics/cal.gif" onclick="javascript:NewCssCal('dateStart')" style="cursor:pointer"/></td> </tr>
			<tr> <td class="title-input">End date:    </td><td align="left"><input size="10" type="text" name="dateEnd" id="dateEnd" />  <img src="pics/cal.gif" onclick="javascript:NewCssCal('dateEnd')" style="cursor:pointer"/></td> </tr>
			<tr> <td colspan="2" width="150" align="right">
				    <input type="hidden" name="event" />
					<input type="submit" value="submit" onclick="return validate(eventName,dateStart)" />
					<input type="reset" value="cancel" />
				</td>
			</tr>
	</table>
	<div class="warning">
		* support only gmail
	</div>
	</form>
</div>   <!-- end div register -->
</div>	 <!-- end div content -->
<div id="copyright">
		Copyright&copy;2011
		<a class="brownLink" href="http://www.xp-link.com">XPLink</a>
		. All rights reserved.
	</div>
</div>   <!-- end div page -->

</body>
</html>