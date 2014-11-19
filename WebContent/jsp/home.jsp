<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.RequestDispatcher" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>redirect page</title>
</head>
<body>
  
<% 
  String sig_req = request.getParameter("signed_request");  
  String request_ids = request.getParameter("request_ids");	
  
  if(request_ids!="null"&&request_ids!=null){	  
	  session.setAttribute("request_ids", request_ids);
  }
            //response.sendRedirect(response.encodeRedirectUrl("home.html?signed_request="+sig_req));
           
        	response.sendRedirect("home.html?signed_request="+sig_req);
			//request.getRequestDispatcher("home.html?signed_request="+sig_req);
         	//RequestDispatcher dispatcher = request.getRequestDispatcher("home.html");
//         	request.setAttribute("signed_request", sig_req);
// 			dispatcher.forward(request, response);
  %>        
              
   
                           
          </body>
</html>
