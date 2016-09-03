<%-- 
    Document   : logout
    Created on : Apr 12, 2014, 4:35:11 PM
    Author     : AMS
--%>

<%
    session.setAttribute("userid", null);
    session.invalidate();
    response.sendRedirect("LoginPage.jsp");
%>