
package controller;

import dao.UserDAO;
import dao.ProjectDAO;
import dto.UserDTO;
import dto.ProjectDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Scanner;
import javax.servlet.http.HttpSession;


@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private ProjectDAO pd = new ProjectDAO();
    private static final String LOGIN_PAGE = "login.jsp";
    public static final Scanner sc = new Scanner(System.in);

    public UserDTO getUser(String strUserID) {
        UserDAO udao = new UserDAO();
        UserDTO user = udao.readbyID(strUserID);
        return user;
    }

    public boolean isValidLogin(String strUserID, String strPassword) {
        UserDTO user = getUser(strUserID);
        System.out.println(user);
//        System.out.println(user.getPassword());
        System.out.println(strPassword);
        return user != null && user.getPassword().equals(strPassword);
    }

    public void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchTerm = request.getParameter("searchTerm");
        if (searchTerm == null) {
            searchTerm = "";
        }
        List<ProjectDTO> projects = pd.searchByTitle(searchTerm);
        request.setAttribute("projects", projects);
        request.setAttribute("searchTerm", searchTerm);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");
            System.out.println("action: " + action);
            if (action == null) {
                url = LOGIN_PAGE;
            } else {
                if (action.equals("login")) {
                    String strUserID = request.getParameter("txtUserID");
                    String strPassword = request.getParameter("txtPassword");
                    if (isValidLogin(strUserID, strPassword)) {
                        url = "welcome.jsp";
                        UserDTO user = getUser(strUserID);
                        request.getSession().setAttribute("user", user);

                        // search
                        search(request, response);
                    } else {
                        request.setAttribute("message", "Incorrect UserID or Password");
                        url = "login.jsp";
                    }
                } else if (action.equals("logout")) {
                    HttpSession session = request.getSession();
                    if (session.getAttribute("user") != null) {
                        request.getSession().invalidate(); // Hủy bỏ session
                        url = "login.jsp";
                    }
                } else if (action.equals("search")) {
                    HttpSession session = request.getSession();
                    if (session.getAttribute("user") != null) {
                        // search
                        search(request, response);
                        url = "welcome.jsp";
                    }
                } else if (action.equals("updateProject")) {
                    try {
                        int projectId = Integer.parseInt(request.getParameter("projectId"));

                        String projectName = request.getParameter("projectName");
                        String description = request.getParameter("description");
                        String status = request.getParameter("status");
                        Date estimatedLaunch = Date.valueOf(request.getParameter("estimatedLaunch"));

                        ProjectDAO dao = new ProjectDAO();
                        ProjectDTO project = new ProjectDTO(projectId, projectName, description, status, estimatedLaunch);

                        boolean success = dao.update(project);

                        if (success) {
                            request.getRequestDispatcher("welcome.jsp").forward(request, response);
                            response.sendRedirect("welcome.jsp");  // Lỗi: Response đã commit trước đó
                        } else {
                            request.getRequestDispatcher("update.jsp").forward(request, response);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.sendRedirect("update.jsp?projectId=?" + request.getParameter("projectId")
                                + "&error=ServerError");
                    }

                } else if (action.equals("add")) {
                    HttpSession session = request.getSession();
                    if (session.getAttribute("user") != null) {
                        UserDTO user = (UserDTO) session.getAttribute("user");
                        if (user.getRole().equals("Founder")) {
                            try {
                                boolean checkError = false;

                                int projectId = Integer.parseInt(request.getParameter("txtProjectID"));
                                String projectName = request.getParameter("txtProjectName");
                                String description = request.getParameter("txtDescription");
                                String status = request.getParameter("txtStatus");
                                String dateString = request.getParameter("txtEstimatedLaunch");
                                Date estimatedLaunch = null;
                                //Check error format date
                                if (dateString != null && !dateString.trim().isEmpty()) {
                                    try {
                                        estimatedLaunch = Date.valueOf(dateString); // Format: YYYY-MM-DD
                                    } catch (IllegalArgumentException e) {
                                        request.setAttribute("txtEstimatedLaunch_error", "Invalid date format. Use YYYY-MM-DD.");
                                    }
                                }
                                //Check error của project id
                                try {

                                    if (projectId <= 0) {
                                        checkError = true;
                                        request.setAttribute("txtProjectID_error", "Project ID must be greater than 0.");
                                    }
                                } catch (NumberFormatException e) {
                                    checkError = true;
                                    request.setAttribute("txtProjectID_error", "Project ID must be a number.");
                                }
                                //Check error của project name
                                if (projectName == null || projectName.trim().isEmpty()) {
                                    checkError = true;
                                    request.setAttribute("txtProjectName_error", "Project Name is required.");
                                }

                                ProjectDTO project = new ProjectDTO(projectId, projectName, description, status, estimatedLaunch);

                                if (!checkError) {
                                    pd.create(project);
                                    // search
                                    search(request, response);
                                    url = "welcome.jsp";
                                } else {
                                    url = "project.jsp";
                                    request.setAttribute("project", project);
                                }
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
