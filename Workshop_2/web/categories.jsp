<%@page import="java.util.List"%>
<%@page import="dto.ExamCategoryDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Exam Categories</h2>
        
        <table border="1">
            <tr>
                <th>Category Name</th>
                <th>Description</th>
            </tr>
            <%
                List<ExamCategoryDTO> categories = (List<ExamCategoryDTO>) request.getAttribute("CATEGORIES_LIST");
                if (categories != null) {
                    for (ExamCategoryDTO category : categories) {
            %>
                        <tr>
                            <td><%= category.getCategoryName() %></td>
                            <td><%= category.getDescription() %></td>
                        </tr>
            <%
                    }
                }
            %>
        </table>
        
        <br>
        <a href="dashboard.jsp">Back to Dashboard</a>
    </body>
</html>