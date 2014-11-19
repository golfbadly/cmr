<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="jsp/css/keyword.css" />
	<title>Sent email page</title>
	<% String[] name={"P'dit","P'Aom","P'nuu","P'mue","P'mod"}; %>
</head>

<body>
	<br/><br/><br/><br/><br/>
	<h1>Party happy Christmas!!!</h1>
	<br/><br/><br/>
	<center>Welcome :  </center>
	<br/>
	<table>
	<tr>
	<td width="80px" class="center">No.</td><td width="200px" class="center">Name-Surname</td><td>sent</td>
	</tr>
	<% for(int i=0;i<name.length;i++) { %>
	<tr>
	<td class="center"><%= i+1%></td><td><%= name[i]%></td><td class="center"><a href="">sent</a></td>
	</tr>
	<% } %>
	
	</table>
</body>
</html>