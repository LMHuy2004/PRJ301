
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng ký</title>
        <style>
            .register-container{
                display: flex;
                justify-content: center;
                align-items: center;
                padding-top: 5%;
            }
            
            .register-form{
                height: 400px;
                width: 400px;
                border-style: solid;
                align-content: center;
                justify-content: center;
            }
            
            .form-title{
                padding:0 0 10px 150px;
            }
            
            .form-group{
                display: flex;
                align-items: center;
                padding: 10px 0 10px 0;
            }
            
            label{
                width: 125px;
                text-align: left;
                margin-right: 0px;
                padding: 0 0 0 10px;
            }
            
            input {
                width: 200px; 
                padding: 5px;
            }
            
            .submit-btn{
                
                display: flex;
                margin: 20px 0 0 35%;
                padding: 12px 20px;
                border-radius: 4px;
                cursor: pointer;
                
                font-size: 16px;
                transition: background-color 0.3s;
                
            }
        </style>
    </head>
    <body>
        <div class="register-container">
            <div class="register-form">
                <h2 class="form-title">Đăng ký</h2>
                <form action="MainController" method="post">
                <input type="hidden" name="action" value="login"/>
                
                <div class="form-group">
                    <label>Tên đăng nhập</label>
                    <input placeholder="Username" type="text" id="userId" name="txtUserID" required/>
                </div>
                
                <div class="form-group">
                    <label for="password">Mật khẩu</label>
                    <input placeholder="Password" type="password" id="password" name="txtPassword" required />
                </div>
                <div class="form-group">
                    <label for="password">Nhập lại mật khẩu</label>
                    <input placeholder="Password" type="password" id="password" name="txtPassword" required />
                </div>
                
                <button type="submit" class="submit-btn">Đăng ký</button>
                
                <%
                        String message = request.getAttribute("message")+"";
                    %>
                    <%=message.equals("null")?"":message%>
                </form>    
            </div>
        </div>
    </body>
</html>
