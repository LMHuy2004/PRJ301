
<%@page import="dto.ProjectDTO"%>
<%@page import="dto.UserDTO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% if (session.getAttribute("user") != null) {
                UserDTO user = (UserDTO) session.getAttribute("user");
                if (user.getRole().equals("Founder")) {
        %>
        <%
            ProjectDTO project = new ProjectDTO();
            try {
                project = (ProjectDTO) request.getAttribute("project");
            } catch (Exception e) {
                project = new ProjectDTO();
            }
            if (project == null) {
                project = new ProjectDTO();
            }
            // get error information
            String txtProjectID_error = request.getAttribute("txtProjectID_error") + "";
            txtProjectID_error = txtProjectID_error.equals("null") ? "" : txtProjectID_error;
            String txtProjectName_error = request.getAttribute("txtProjectName_error") + "";
            txtProjectName_error = txtProjectName_error.equals("null") ? "" : txtProjectName_error;
            String txtEstimatedLaunch_error = request.getAttribute("txtEstimatedLaunch_error") + "";
            txtEstimatedLaunch_error = txtEstimatedLaunch_error.equals("null") ? "" : txtEstimatedLaunch_error;


        %>

        <h1>Project Information</h1>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="add"/>

            <% String projectID = request.getAttribute("projectID") + "";
                projectID = projectID.equals("null") ? "" : projectID;
            %>

            <label for="txtProjectID">Project ID:</label>
            <input type="text" id="txtProjectID" name="txtProjectID" value="<%=projectID%>"/>
            <% if (!txtProjectID_error.isEmpty()) {%>
            <div class="error-message"><%=txtProjectID_error%></div>
            <% } %>

            <% String projectName = request.getAttribute("projectName") + "";
                projectName = projectName.equals("null") ? "" : projectName;
            %>

            <label for="txtProjectName">Project Name:</label>
            <input type="text" id="txtProjectName" name="txtProjectName" value="<%=projectName%>"/>
            <% if (!txtProjectName_error.isEmpty()) {%>
            <div class="error-message"><%=txtProjectName_error%></div>
            <% } %>

            <% String description = request.getAttribute("description") + "";
                description = description.equals("null") ? "" : description;
            %>

            <label for="txtDescription">Description:</label>
            <input type="text" id="txtDescriptionr" name="txtDescription" value="<%=description%>"/>

            <% String status = request.getAttribute("status") + "";
                status = status.equals("null") ? "" : status;
            %>

            <label for="txtStatus">Status:</label>
            <input type="text" id="txtStatus" name="txtStatus" value="<%=status%>"/>

            <% String estimatedLaunch = request.getAttribute("txtEstimatedLaunch") + "";
                estimatedLaunch = estimatedLaunch.equals("null") ? "" : estimatedLaunch;
            %>

            <label for="txtEstimatedLaunch">Estimated Launch:</label>
            <input type="date" id="txtEstimatedLaunch" name="txtEstimatedLaunch" value="<%=estimatedLaunch%>"/>







            <input type="submit" value="Save" />
            <input type="reset" value="Reset"/>

        </form>
        <a href="MainController?action=search" class="back-link">Back to List</a>

        <%} else {%>

        <h1>403 Error</h1>
        <p>You do not have permission to access this content!</p>
        <a href="MainController?action=search" class="back-link">Back to List</a>

        <%}
        } else {%>

        <h1>Access Denied</h1>
        <p>Please log in to access this page.</p>
        <a href="login.jsp" class="back-link">Go to Login</a>

        <%}%>

    </body>
</html>
