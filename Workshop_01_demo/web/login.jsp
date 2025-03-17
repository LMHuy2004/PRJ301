<%-- 
    Document   : login
    Created on : Mar 4, 2025, 7:57:09 PM
    Author     : l26m1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        
    </head>
    <body>
        <div class="login-container">
            <h2>Login</h2>
            <form action="LoginServlet" method="post">
                <label for="txtUsername">Username:</label>
                <input type="text" id="txtUsername" name="txtUsername" required>

                <label for="txtPassword">Password:</label>
                <input type="password" id="txtPassword" name="txtPassword" required>

                <button type="submit">Login</button>
            </form>
        </div>
    </body>
</html>
