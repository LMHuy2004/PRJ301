<%-- 
    Document   : login
    Created on : Mar 4, 2025, 7:26:31 PM
    Author     : l26m1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="LoginServlet" method="get">
        Username <input type="text" name="txtUsername"/> <br/>
        Password <input type="password" name="txtPassword"/> <br/>
        <input type="submit" value="Login"/>
        </form>
    </body>
</html>
