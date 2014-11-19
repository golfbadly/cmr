<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<HEAD>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<TITLE>Page1.jsp</TITLE>
</HEAD>
<BODY>
		<%
	    
		    String suggetsword=request.getParameter("suggestword").toLowerCase();
		   
			String[] mySuggests=new String[]{"Anna","Apple","An","America","Ali","Ahmed"
			,"Brittany","Ball","Cinderella","Car","Diana","Door","Eva","Ear"
			,"Fiona","Fan","Gunda","Game","Hege","High","Inga","India","Johanna","Joomla"
			,"Kitty","Kitkat","Linda","Loser","Nina","Noise","Ophelia","Open","Pradit"
			,"Pracha","Amanda","Area","Raquel","Rar","Cindy","Sarawut","Doris","Do","Eve"
			,"Event","Evita","Sunniva","Sun","Tove","Tall","Unni","Under","Violet","Vote"
			,"Liza","Land","Elizabeth","English","Ellen","Wenche","XP-Link","Vicky","Vila"};
			
		    out.println("<table width='100%' border='0' cellpadding='0' cellspacing='0'>");
		
		    for(int i=0;i<mySuggests.length;i++)
		    {
		       if(mySuggests[i].toLowerCase().startsWith((suggetsword.substring(0,1))))
		       {
		          out.print("<tr><td>"+mySuggests[i]+"</td></tr>");	       
		       }
		    }
		    out.println("</table>");
		%>
</body>
</html>