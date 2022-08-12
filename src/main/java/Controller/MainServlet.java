package Controller;

import Dao.CartDao;
import Dao.Interface.ProductDao;
import Dao.Interface.UserDao;
import Dao.ProductDaoImp;
import Dao.UserDaoImp;
import Services.AuthInterface.Auth;
import Services.AuthService;
import model.Product;
import utils.dbConnect;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

@WebServlet(name = "MainServlet", value = "/")
public class MainServlet extends HttpServlet {
    int updateId;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath();
        switch (url){
            case "/login":
                LoginAction(request,response);
                break;
            case "/loginPage":
                LoginPageAction(request,response);
                break;
            case "/logout":
                LogoutAction(request,response);
            case "/Account":
                AccountAction(request,response);
            case "/AddProduct":
                addProductAction(request,response);
            case "/AddProductPage":
                addProductPage(request,response);
            case "/AllProducts":
                AllProductsPage(request,response);
            case "/BuyNow":
                BuyNowPages(request,response);
            case "/checkout":
                checkOutPage(request,response);
            case "/AddToCart":
                AddToCartPage(request,response);
            case "/signup":
                SignUpPage(request,response);
            case "/signupAction":
                SignUpAction(request,response);
            case "/UpdateProduct":
                updateProductAction(request,response);
            case "/UpdateProductPage":
                updateProductPage(request,response);
            case "/DeleteProduct":
                DeleteProductPage(request,response);
            case "/DeleteCartProduct":
                DeleteCartProduct(request,response);
            default:
                break;

        }
    }

    private void DeleteCartProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CartDao cartDao = new CartDao();
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        int Userid = (int) session.getAttribute("userId");

        if(cartDao.deleteProductFromCart(id,Userid)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("checkout.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void LoginAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         Auth authService = new AuthService(dbConnect.getConnection());
        String Email = request.getParameter("email");
        String password = request.getParameter("password");
        String res = authService.Login(Email, password);

        try {
            if (res.equalsIgnoreCase("Admin")) {
                HttpSession session = request.getSession();
                //    session.setAttribute("Response",res);
                session.setAttribute("name", Email);
                session.setAttribute("customerRole","Admin");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("my-account.jsp");
                requestDispatcher.forward(request, response);
            } else if (res.equalsIgnoreCase("customer")) {
                HttpSession session = request.getSession();
                session.setAttribute("name", Email);
                session.setAttribute("customerRole","customer");
                UserDao dao = new UserDaoImp();
                int userId = 0;
                ResultSet resultSet = dao.getUser(Email);
                while (resultSet.next()){
                    userId = resultSet.getInt("id");
                };
                session.setAttribute("userId",userId);
                RequestDispatcher rp = request.getRequestDispatcher("index.jsp");
                rp.forward(request, response);
            } else {
                request.setAttribute("name", "Invalid username or password");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        }
        catch (Exception e){
            e.getMessage();
        }
    }

    protected void LoginPageAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("Login.jsp");
    }

    private void LogoutAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }


    private void AccountAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("my-account.jsp");
    }

    private void addProductAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    private void addProductPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("AddProduct.jsp");
    }

    private void AllProductsPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("AllProducts.jsp").forward(request,response);
    }

    private void BuyNowPages(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        try {
            if(session.getAttribute("name") == null){
                request.getRequestDispatcher("Login.jsp").forward(request,response);
            }
            else {
                request.getRequestDispatcher("checkout.jsp").forward(request,response);
            }
        }
        catch (Exception e){

        }
    }

    private void checkOutPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("checkout.jsp");
        dispatcher.forward(request, response);
    }

    private void AddToCartPage(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int Quantity = 1;
        CartDao cartDao = new CartDao();
        HttpSession session = request.getSession();
        try {
            if(session.getAttribute("name") == null){
                RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
                dispatcher.forward(request, response);
            }
            else {
                int Userid = (int) session.getAttribute("userId");
                cartDao.addProductToCart(Userid, id, Quantity);
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }

        }
        catch (Exception e){

        }
    }


    private void SignUpPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("SignUp.jsp").forward(request,response);

    }

    private void SignUpAction(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        Auth authService = new AuthService(dbConnect.getConnection());
        try {
            if (authService.SignUp(request)) {
                request.setAttribute("signup", "Success");
                request.getRequestDispatcher("SignUp.jsp").forward(request, response);
            } else {
                request.setAttribute("signup", "Email Taken, enter new Email");
                request.getRequestDispatcher("SignUp.jsp").forward(request, response);
            }
        }
        catch (Exception e){
            e.getMessage();
        }
    }

    private void updateProductAction(HttpServletRequest request, HttpServletResponse response) {
        ProductDao dao =new ProductDaoImp();
        Product product = new Product();
        String productName = request.getParameter("productName");
        String category = request.getParameter("category");
        int quantity = Integer.parseInt(request.getParameter("Quantity"));
        String price = request.getParameter("Price");
        product.setProductName(productName);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setCategory(category);
        product.setProductId(updateId);
        try {
            if (dao.updateProduct(product)){
                request.setAttribute("UpdateSuccess", "Product updated Successfully");
                request.getRequestDispatcher("AllProducts").forward(request, response);
            }
        }
        catch (Exception e){
            e.getMessage();
        }
    }

    private void updateProductPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao dao = new ProductDaoImp();
       updateId = Integer.parseInt(request.getParameter("id"));
        Product product = dao.getProduct(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("pname",product.getProductName());
        request.setAttribute("category",product.getCategory());
        request.setAttribute("price",product.getPrice());
        request.setAttribute("quantity",product.getQuantity());

        RequestDispatcher rp = request.getRequestDispatcher("UpdateProduct.jsp");
        rp.forward(request,response);
    }

    private void DeleteProductPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProductDao dao = new ProductDaoImp();
        if (dao.deleteProducts(id)){
            RequestDispatcher rp = request.getRequestDispatcher("AllProducts.jsp");
            rp.forward(request,response);
        }
    }
}
