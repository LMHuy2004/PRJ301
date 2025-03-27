<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h5 style="color: red">${requestScope.ERROR}</h5>
        <form action="MainaController" method="post">
            UserID<input type="text" name="txtUserId" value="">
            Password<input type="password" name="txtPassword" value="">
            <input type="submit" name="action" value="Login">
            <%
                String message = request.getAttribute("message") + "";
            %>
            <%=message.equals("null") ? "" : message%>
        </form>
    </body>
</html>