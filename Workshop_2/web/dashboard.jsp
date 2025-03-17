<%@page import="java.util.List"%>
<%@page import="dto.ExamCategoryDTO"%>
<%@page import="dto.ExamDTO"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exam Dashboard</title>
    </head>
    <body>
        <%
            HttpSession sessionObj = request.getSession(false);
            UserDTO user = (UserDTO) sessionObj.getAttribute("LOGIN_USER");
            if (user == null) {
                response.sendRedirect("login.jsp");
            }
        %>
        <h2>Welcome, <%= user.getName() %></h2>
        <p>Role: <%= user.getRole() %></p>

        <hr>

        <h3>Exam Categories</h3>
        <form action="MainController" method="get">
            <label>Select Category:</label>
            <select name="categoryId" onchange="this.form.submit()">
                <option value="">-- Select Category --</option>
                <%
                    List<ExamCategoryDTO> categories = (List<ExamCategoryDTO>) request.getAttribute("CATEGORIES_LIST");
                    int selectedCategory = request.getAttribute("SELECTED_CATEGORY") != null 
                                            ? (int) request.getAttribute("SELECTED_CATEGORY") 
                                            : -1;
                    for (ExamCategoryDTO category : categories) {
                %>
                    <option value="<%= category.getCategoryId() %>" <%= (category.getCategoryId() == selectedCategory) ? "selected" : "" %>>
                        <%= category.getCategoryName() %>
                    </option>
                <%
                    }
                %>
            </select>
            <input type="hidden" name="action" value="filterExams">
        </form>

        <hr>

        <h3>Exams</h3>
        <table border="1">
            <tr>
                <th>Exam Title</th>
                <th>Subject</th>
                <th>Total Marks</th>
                <th>Duration (mins)</th>
            </tr>
            <%
                List<ExamDTO> exams = (List<ExamDTO>) request.getAttribute("EXAM_LIST");
                if (exams != null && !exams.isEmpty()) {
                    for (ExamDTO exam : exams) {
            %>
                        <tr>
                            <td><%= exam.getExamTitle() %></td>
                            <td><%= exam.getSubject() %></td>
                            <td><%= exam.getTotalMarks() %></td>
                            <td><%= exam.getDuration() %></td>
                        </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="4">No exams available for this category.</td>
                </tr>
            <%
                }
            %>
        </table>

        <br>
        <p><a href="MainController?action=logout">Logout</a></p>
    </body>
</html>
