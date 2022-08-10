package Controller;

import Dao.CartDao;
import Dao.Interface.ProductDao;
import Dao.ProductDaoImp;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddToCart", value = "/AddToCart")
public class AddToCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
        ProductDao productDao = new ProductDaoImp();
        //CartDao cartDao = n
       HttpSession session = request.getSession();
       try {
           if(session.getAttribute("name") == null){
               response.sendRedirect("Login.jsp");
           }
           else {

           }
       }
       catch (Exception e){

       }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
