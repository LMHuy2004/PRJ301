

<%@page import="dto.ProjectDTO"%>
<%@page import="java.util.List"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-16WWW">
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


        <%                if (session.getAttribute("user") != null) {
                UserDTO user = (UserDTO) session.getAttribute("user");
        %>
        <h1>Welcome <%=user.getName()%></h1>
    <form action="MainController" method="post">
        <input type="hidden" name="action" value="logout">
        <button type="submit">Logout</button>
    </form>
        <%
            String searchTerm = request.getAttribute("searchTerm") + "";
            searchTerm = searchTerm.equals("null") ? "" : searchTerm;
        %>
        
        <% if (session.getAttribute("user") != null) {
                UserDTO user1 = (UserDTO) session.getAttribute("user");
                if (user1.getRole().equals("Founder")) {
        %>
        <form action="MainController">
            <input type="hidden" name="action" value="search"/>
            <label for="searchInput">Search Project:</label>
            <input type="text" id="searchInput" name="searchTerm" value="<%=searchTerm%>" class="search-input" placeholder="Enter project name"/>
            <input type="submit" value="Search" class="search-btn"/>
        </form>
            <%}
            }%>
            
        <% if (session.getAttribute("user") != null) {
                UserDTO user1 = (UserDTO) session.getAttribute("user");
                if (user1.getRole().equals("Founder")) {
        %>
        <a href="project.jsp">Add Project</a>
        <%}
            }%>

        <%
            if (request.getAttribute("projects") != null) {
                List<ProjectDTO> projects = (List<ProjectDTO>) request.getAttribute("projects");

        %>
        <table>
            <thead>
                <tr>
                    <th>ProjectID</th>
                    <th>ProjectName</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>EstimatedLaunch</th>
                        <% if (session.getAttribute("user") != null) {
                                UserDTO user1 = (UserDTO) session.getAttribute("user");
                                if (user1.getRole().equals("Founder")) {
                        %>

                    <th>Action</th>
                        <%}
                        }%>
                </tr>
            </thead>
            <tbody>
                <%for (ProjectDTO p : projects) {
                %>
                <tr>
                    <td><%=p.getProjectId()%></td>
                    <td><%=p.getProjectName()%></td>
                    <td><%=p.getDescription()%></td>
                    <td><%=p.getStatus()%></td>
                    <td><%=p.getEstimatedLaunch()%></td>
                    <% if (session.getAttribute("user") != null) {
                            UserDTO user1 = (UserDTO) session.getAttribute("user");
                            if (user1.getRole().equals("Founder")) {
                    %>
                    <td>
                        <form action="update.jsp" method="get">
                            <input type="hidden" name="projectId" value="<%= p.getProjectId()%>">
                            <img src="assets/images/update.png" style="height: 20px">
                            <button type="submit" class="update-btn">Update</button>
                        </form>
                    </td>
                        <%
                                }
                            }
                        %>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
            
        <%    }
            }%>
    </div>

</body>
</html>