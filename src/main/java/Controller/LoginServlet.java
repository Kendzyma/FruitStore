package Controller;

import utils.dbConnect;
import Services.AuthInterface.Auth;
import Services.AuthService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Login", value = "/login")
public class LoginServlet extends HttpServlet {
    private Auth authService = new AuthService(dbConnect.getConnection());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("Login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Email = request.getParameter("email");
        String password = request.getParameter("password");
     String res =  authService.Login(Email,password);

        if(res.equalsIgnoreCase("Admin")){
            HttpSession session = request.getSession();
            session.setAttribute("name",Email);
            RequestDispatcher rp = request.getRequestDispatcher("my-account.jsp");
            rp.forward(request,response);
        }

        else if (res.equalsIgnoreCase("customer")) {
            HttpSession session = request.getSession();
            session.setAttribute("name",Email);
            RequestDispatcher rp = request.getRequestDispatcher("index.jsp");
            rp.forward(request,response);
        } else {
            request.setAttribute("name", "Invalid username or password");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }

}
