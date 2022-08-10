package Controller;

import Dao.Interface.ProductDao;
import Dao.ProductDaoImp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteProduct", value = "/DeleteProduct")
public class DeleteProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int id = Integer.parseInt(request.getParameter("id"));
        ProductDao dao = new ProductDaoImp();
       if (dao.deleteProducts(id)){
           RequestDispatcher rp = request.getRequestDispatcher("AllProducts.jsp");
           rp.forward(request,response);
       }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
