 <%-- 
    Document   : login
    Created on : Mar 19, 2025, 7:25:00 PM
    Author     : l26m1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <style>
            body {

                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background-color: #f4f4f4;

            }

            div {
                background: #fff;
                padding: 10px;
                border-radius: 8px;
                text-align: center;
                width: 300px;
            }

            h2 {
                margin-bottom: 20px;
                color: #333;
                font-weight: bold;
            }

            input {
                width: 100%;
                padding: 10px;
                margin: 10px 0;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }

            button {
                width: 100%;
                padding: 10px;
                background-color: #007BFF;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
            }

            button:hover {
                background-color: #0056b3;
            }

            .message {
                color: red;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <div>

            <h2>Đăng nhập</h2>
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="login" />

                <input type="text" name="txtUsername" placeholder="Username" required>
                <input type="password" name="txtPassword" placeholder="Password" required>

                <button type="submit" >Đăng nhập</button>

                <%
                    String message = request.getAttribute("message") + "";
                %>
                <%=message.equals("null") ? "" : message%>
            </form>

        </div>
    </body>
</html>
