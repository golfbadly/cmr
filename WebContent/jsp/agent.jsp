<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% response.addHeader("P3P","CP=\"CAO PSA OUR\""); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Facebook Agent Page</title>
</head>
<body>
<a>Agent!</a>
<%
	String redirectUrl = (String)request.getAttribute("redirectUrl"); 

   if(redirectUrl!=null ){  %>
	   <script>
       		top.location.href = "${redirectUrl}";   
   		</script>  
  <%  } else{	
	  response.sendRedirect("agent.html");
	}	
	%>	
<a><%=redirectUrl %></a>
</body>
</html>