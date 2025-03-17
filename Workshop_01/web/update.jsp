<%@page import="dao.ProjectDAO"%>
<%@page import="dto.ProjectDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String id = request.getParameter("projectId");
    ProjectDAO dao = new ProjectDAO();
    ProjectDTO project = dao.readbyID(id);
%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Project</title>
</head>
<body>
    <h2>Update Project</h2>
    <form action="MainController" method="POST">
        <input type="hidden" name="action" value="updateProject">
        <input type="hidden" name="projectId" value="<%= project.getProjectId() %>">

        Project Name: <input type="text" name="projectName" value="<%= project.getProjectName() %>"><br>
        Description: <input type="text" name="description" value="<%= project.getDescription() %>"><br>
        Status: <input type="text" name="status" value="<%= project.getStatus() %>"><br>
        Estimated Launch: <input type="date" name="estimatedLaunch" value="<%= project.getEstimatedLaunch() %>"><br>

        <input type="submit" value="Save">
    </form>
</body>
</html>
