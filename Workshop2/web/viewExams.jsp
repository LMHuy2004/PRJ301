<%@page import="java.util.List"%>
<%@page import="dto.ExamsDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>View Exams</title>
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
            color: white;
        }
    </style>
</head>
<body>
    <h1>Exam List</h1>
    <table>
        <thead>
            <tr>
                <th>exam_id</th>
                <th>exam_title</th>
                <th>Subject</th>
                <th>category_id</th>
                <th>total_marks</th>
                <th>Duration</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<ExamsDTO> examsList = (List<ExamsDTO>) request.getAttribute("examsList");
                if (examsList != null && !examsList.isEmpty()) {
                    for (ExamsDTO exam : examsList) {
            %>
            <tr>
                <td><%= exam.getExam_id()%></td>
                <td><%= exam.getExam_title()%></td>
                <td><%= exam.getSubject() %></td>
                <td><%= exam.getCategory_id()%></td>
                <td><%= exam.getTotal_marks()%></td>
                <td><%= exam.getDuration() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="6">No exams available.</td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <br>
    <a href="welcome.jsp">Back to Categories</a>
</body>
</html>
