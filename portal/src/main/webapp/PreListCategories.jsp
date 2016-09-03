<%-- 
    Document   : preEditWorkAll
    Created on : 27 Mar, 2015, 3:26:16 PM
    Author     : AMS
--%>
 <%
             String categorization_name = (String) session.getAttribute("categorization_ID");
             System.out.println("inside precategory jsp"+categorization_name);
            
        %>
        <META HTTP-EQUIV="Refresh" CONTENT="0;URL=listcategories?cat_name=<%=categorization_name%>">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     
    </head>
    <body>
        
    </body>
</html>
