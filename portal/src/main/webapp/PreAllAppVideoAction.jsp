<%-- 
    Document   : preEditWorkAll
    Created on : 27 Mar, 2015, 3:26:16 PM
    Author     : AMS
--%>
 <%
             String map_vido_app_id = (String) session.getAttribute("map_vido_app_id");
             System.out.println("inside precategorization jsp"+map_vido_app_id);
        %>
<META HTTP-EQUIV="Refresh" CONTENT="0;URL=allvideos?app_id=<%=map_vido_app_id%> ">
        
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
    </head>
   
    <body>
        
    </body>
</html>
