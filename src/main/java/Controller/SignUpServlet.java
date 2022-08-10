package Controller;

import utils.dbConnect;
import Services.AuthInterface.Auth;
import Services.AuthService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignUpServlet", value = "/signup")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("SignUp.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Auth authService = new AuthService(dbConnect.getConnection());
        try {
            if (authService.SignUp(request)) {
                    out.println("Successs");

                    response.sendRedirect("Login.jsp");
            } else {
                request.setAttribute("name", "Email Taken, enter new Email");
                request.getRequestDispatcher("SignUp.jsp").forward(request, response);
            }
        }
        catch (Exception e){
            e.getMessage();
        }
    }
}
