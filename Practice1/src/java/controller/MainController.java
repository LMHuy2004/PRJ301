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

    private static final String LOGIN_PAGE="login.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url= LOGIN_PAGE;
        try {
            //your code here
            String action= request.getParameter("action");
            if(action==null) {
                url=LOGIN_PAGE;
            } 
            //(login)
            else if (action.equals("Login")) {
                url = "LoginController";
            } 
            //logout
            else if (action.equals("Logout")) {
                url = "LogoutController";
            }
            //search
            else if (action.equals("Search")) {
                url = "SearchController";
            }
            //delete
            else if (action.equals("Delete")) {
                url = "DeleteController";
            }
            
        }catch (Exception e) {
            log("Error at MainController: "+e.toString());
        }finally{
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
