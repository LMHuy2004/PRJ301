 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Painting Page</title>
    </head>
    <body>
        <h1>Welcome sessionScope.account.fullName!</h1>
        <!--Logout-->
        <a href="MainController?action=Logout">Logout</a>
        <!---->

        <!-- Search -->
        <form action="MainController">
            <input type="text" name="txtSearch" value="">
            <input type="submit" name="action" value="Search">
        </form>

        <!--phần search này làm cuối-->
        <c:if test="requestScope.LIST_RESULT">
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
                    </tr>
                </thead>
                <tbody>
                    <c:set var="count" value="0"></c:set>
                    <!--add library jstl 1.2.1 để dùng c:forEach-->
                    <c:forEach items="requestScope.LIST_RESULT" var="p">
                        <tr>
                            <td>1</td>
                            <td>p.id</td>
                            <td>p.description</td>
                            <td>p.height</td>
                            <td>p.width</td>
                            <td>p.price</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <!---->

    </body>
</html>

------------------------------------------------------------------------------------

package dto;



public class PaintingDTO {

    private String id, creator, description;
    private int height, width;
    private double price;
    private boolean status;
    
    public PaintingDTO() {
    }
    
    public PaintingDTO(String id, String creator, String description, int height, int width, double price, boolean status) {
        this.id = id;
        this.creator = creator;
        this.description = description;
        this.height = height;
        this.width = width;
        this.price = price;
        this.status = status;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getCreator() {
        return creator;
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public boolean isStatus() {
        return status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    
}

------------------------------------------------------------------------------------

package dao;

import dto.PaintingDTO;
import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;


import static utils.DBUtils.getConnection;

public class PaintingDAO extends DBUtils {
    
    public List<PaintingDTO> searchPainting(String txtSearch) throws SQLException {
        List<PaintingDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        //SELECT * FROM [tblPainting] WHERE [description] LIKE '%sown%' and status = 1
        String sql = "SELECT * FROM [tblPainting] WHERE [description] LIKE ? and status = 1";
        try {
            conn = getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, "%" + txtSearch + "%");
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String id = rs.getString("id");
                    String creator = rs.getString("creator");
                    String description = rs.getString("description");
                    int height = rs.getInt("height");
                    int width = rs.getInt("width");
                    double price = rs.getDouble("price");
                    boolean roleID = rs.getBoolean("roleID");
                    PaintingDTO painting = new PaintingDTO(id, creator, description, height, width, price, roleID);
                    list.add(painting);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
}

------------------------------------------------------------------------------------

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    
    private static final String LOGIN_PAGE = "login.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = LOGIN_PAGE;
            } //your code here (login)
            else if (action.equals("Login")) {
                url = "LoginController";
            } //logout
            else if (action.equals("Logout")) {
                url = "LogoutController";
            } //search
            else if (action.equals("Search")) {
                url = "SearchController";
            }
            
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

------------------------------------------------------------------------------------

package controller;

import dao.PaintingDAO;
import dto.PaintingDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String txtSearch = request.getParameter("txtSearch");
            PaintingDAO dao = new PaintingDAO();
            List<PaintingDTO> list = dao.searchPainting(txtSearch);
            if (list != null) {
                request.setAttribute("LIST_RESULT", list);
                request.getRequestDispatcher("paintingList.jsp").forward(request, response);
            }
        } catch (Exception e) {
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
