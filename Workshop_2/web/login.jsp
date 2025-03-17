<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="MainController" method="post">
            <h2>Đăng nhập</h2>

            <div>
                <label>Tên đăng nhập</label>
                <input type="text" name="txtUserID" required/>
            </div>
            <div>
                <label>Mật khẩu</label>
                <input type="password" name="txtPassword" required/>
            </div>
            
            <% if (request.getAttribute("ERROR") != null) {%>
                <p style="color: red;"><%= request.getAttribute("ERROR")%></p>
            <% }%>
            
            <button type="submit">Đăng nhập</button>
        </form>
    </body>
</html>