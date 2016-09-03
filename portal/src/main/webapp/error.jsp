<%-- 
    Document   : sessionExpire
    Created on : Sep 5, 2013, 7:44:51 PM
    Author     : Fiza
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" href="css.css" rel="stylesheet"  />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-color: whitesmoke">

        <form >
            <div class="header">
                <h1  align="center">Session Expired!!</h1>
                <p align="center">
                    Your session has expired.  
                    Please <a href="LoginPage.jsp">return to the login page</a> 
                    and log in again to continue accessing your account.</p>
            </div>
            <%
                session.invalidate();
            %>
        </form>

    </body>
</html>
