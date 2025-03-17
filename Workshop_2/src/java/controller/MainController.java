package controller;

import dao.ExamCategoryDAO;
import dao.UserDAO;
import dto.ExamCategoryDTO;
import dto.ExamDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MainController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MainController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("logout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("login.jsp");
        } else if ("filterExams".equals(action)) {
        try {
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));

            ExamCategoryDAO categoryDAO = new ExamCategoryDAO();
            List<ExamDTO> exams = categoryDAO.getExamsByCategory(categoryId);

            request.setAttribute("EXAM_LIST", exams);
            request.setAttribute("SELECTED_CATEGORY", categoryId);
            
            // Fetch categories again for dropdown
            List<ExamCategoryDTO> categories = categoryDAO.getAllCategories();
            request.setAttribute("CATEGORIES_LIST", categories);

            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("txtUserID");
        String password = request.getParameter("txtPassword");

        try {
        UserDAO userDAO = new UserDAO();
        UserDTO user = userDAO.checkLogin(username, password);
        
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("LOGIN_USER", user);

            // Fetch exam categories
            ExamCategoryDAO categoryDAO = new ExamCategoryDAO();
            List<ExamCategoryDTO> categories = categoryDAO.getAllCategories();
            request.setAttribute("CATEGORIES_LIST", categories);

            // Forward to dashboard with categories
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } else {
            request.setAttribute("ERROR", "Invalid username or password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}