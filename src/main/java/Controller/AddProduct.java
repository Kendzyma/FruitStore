package Controller;

import Dao.Interface.ProductDao;
import Dao.ProductDaoImp;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddProduct", value = "/AddProduct")
public class AddProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("AddProduct.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String productName = request.getParameter("productName");
      String category = request.getParameter("category");
      String price =  request.getParameter("Price");
      int quantity = Integer.parseInt(request.getParameter("Quantity"));

        Product product = new Product();
        product.setProductName(productName);
        product.setCategory(category);
        product.setPrice(price);
        product.setQuantity(quantity);
        ProductDao productDao = new ProductDaoImp();
        if(productDao.addProduct(product)){
            request.setAttribute("Success", "Product Added successfully");
            request.getRequestDispatcher("AddProduct.jsp").forward(request, response);
        }

    }
}
