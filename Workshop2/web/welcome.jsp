<%-- 
    Document   : welcome
    Created on : Mar 19, 2025, 7:50:47 PM
    Author     : l26m1
--%>

<%@page import="java.util.List"%>
<%@page import="dto.CategoryDTO"%>
<%@page import="utils.AuthUtils"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                border: 1px solid black;
                padding: 8px;
                text-align: center;
            }
            th {
                background-color: red;
            }
            .delete-btn {
                color: red;
                font-weight: bold;
                cursor: pointer;
            }
            .update-btn {
                color: blue;
                font-weight: bold;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <div style="min-height: 500px; padding: 10px">
            <%                if (session.getAttribute("user") != null) {
                    UserDTO user = (UserDTO) session.getAttribute("user");
            %>
            <h1> Welcome <%=user.getName()%> </h1>
            <%
                String searchTerm = request.getAttribute("searchTerm") + "";
                searchTerm = searchTerm.equals("null") ? "" : searchTerm;
            %>
            <div class="search-section">
                <form action="MainController">
                    <input type="hidden" name="action" value="search"/>
                    <label for="searchInput">Search Categories:</label>
                    <input type="text" id="searchInput" name="searchTerm" value="<%=searchTerm%>" class="search-input" />
                    <input type="submit" value="Search" class="search-btn"/>
                </form>
            </div>


            <!--    -------------------------------        ---->

            <%
                if (request.getAttribute("categories") != null) {
                    List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categories");

            %>
            <table class="category-table">
                <thead>
                    <tr>
                        <th>category_id</th>
                        <th>category_name</th>
                        <th>description</th>
                            <% if (AuthUtils.isAdmin(session)) {
                            %>
                        <th>Action</th>
                            <%}%>

                    </tr>
                </thead>
                <tbody>
                    <%            for (CategoryDTO c : categories) {
                    %>
                    <tr>
                        <td><%=c.getCategory_id()%></td>
                        <td><%=c.getCategory_name()%></td>
                        <td><%=c.getDescription()%></td>
                        <%
                            if (AuthUtils.isAdmin(session)) {
                        %>
                        <td>
                            <form action="MainController" method="post">
                                <input type="hidden" name="action" value="ViewExams">
                                <input type="hidden" name="category_id" value="<%= c.getCategory_id()%>">
                                <input type="submit" value="View exams">
                            </form>
                        </td>
                        <%}%>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            <%    }
            } else { %>
            You do not have permission to access this content.
            <%}%>
        </div>
    </body>
</html>
