<%-- 
    Document   : preEditWorkAll
    Created on : 27 Mar, 2015, 3:26:16 PM
    Author     : AMS
--%>
 <%
             String app_name = (String) session.getAttribute("app_id");
             System.out.println("inside precategorization jsp"+app_name);
        %>
<META HTTP-EQUIV="Refresh" CONTENT="0;URL=listcategorization?app_name=<%=app_name%> ">
        
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
    </head>
   
    <body>
        
    </body>
</html>
