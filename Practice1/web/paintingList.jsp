<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Painting Page</title>
    </head>
    <body>
        <h1>Welcome ${sessionScope.account.fullName}!</h1>
        <!--Logout-->
        <a href="MainController?action=Logout">Logout</a>
        <!---->

        <!-- Search -->
        <form action="MainController">
            <input type="text" name="txtSearch" value="">
            <input type="submit" name="action" value="Search">
        </form>

        <!--phần search này làm cuối-->
        <c:if test="${requestScope.LIST_RESULT != null && !requestScope.LIST_RESULT.isEmpty()}">
            <!--gõ table ctrl + space enter ra bảng-->
            <table border="1">
                <thead>
                    <tr>
                        <th>no</th>
                        <th>id</th>
                        <th>description</th>
                        <th>height</th>
                        <th>width</th>
                        <th>price</th>
                        <!--delete-->
                        <th>action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="count" value="${0}"></c:set>
                        <!--add library jstl 1.2.1 để dùng c:forEach-->
                    <c:forEach items="${requestScope.LIST_RESULT}" var="p">
                        <!--delete-->
                    <form action="MainController">
                        <input type="hidden" name="pid" value="${p.id}">
                        <input type="hidden" name="txtSearch" value="${TXTSEARCH}">
                        <!---->    
                        <tr>
                            <td>${count + 1}</td>
                            <td>${p.id}</td>
                            <td>${p.description}</td>
                            <td>${p.height}</td>
                            <td>${p.width}</td>
                            <td>${p.price}</td>
                            <!--delete-->
                            <td><button type="submit" name="action" value="Delete">Delete</button></td>
                            <!---->
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <!---->

</body>
</html>
