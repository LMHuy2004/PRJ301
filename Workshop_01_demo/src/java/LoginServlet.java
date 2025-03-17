import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Get input from form
        String username = request.getParameter("txtUsername"); 
        String password = request.getParameter("txtPassword");
        
        // Dummy authentication (replace with database check)
        if ("huy".equals(username) && "123".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            response.sendRedirect("dashboard.jsp");
        } else {
            response.getWriter().println("<h3>Invalid username or password. Try again!</h3>");
        }
    }
}
