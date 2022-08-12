//package Controller;
//
//import Dao.Interface.ProductDao;
//import Dao.ProductDaoImp;
//import model.Product;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//
//@WebServlet(name = "UpdateProduct", value = "/UpdateProduct")
//public class UpdateProduct extends HttpServlet {
//    int id;
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        ProductDao dao = new ProductDaoImp();
//      i  id = Integer.parseInt(request.getParameter("id"));
//       Product product = dao.getProduct(Integer.parseInt(request.getParameter("id")));
//       request.setAttribute("pname",product.getProductName());
//       request.setAttribute("category",product.getCategory());
//       request.setAttribute("price",product.getPrice());
//       request.setAttribute("quantity",product.getQuantity());
//
//        RequestDispatcher rp = request.getRequestDispatcher("UpdateProduct.jsp");
//        rp.forward(request,response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//}
