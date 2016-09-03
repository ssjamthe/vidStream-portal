<%-- 
    Document   : preEditWorkAll
    Created on : 27 Mar, 2015, 3:26:16 PM
    Author     : AMS
--%>
<%
             String category_name = (String) session.getAttribute("cateory_id");
             System.out.println("inside PreListVideoAction.jsp ::::"+category_name);
        %>
 <META HTTP-EQUIV="Refresh" CONTENT="0;URL=listvideos?categoies_Name=<%=category_name%>">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     
    </head>
    <body>
        
    </body>
</html>
