<%@ page import="Dao.Interface.ProductDao" %>
<%@ page import="Dao.ProductDaoImp" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title" %>
<!DOCTYPE html>
<html lang="en">
<!-- Basic -->

<head>
<%@include file="includes/head.jsp"%>
</head>

<body>
<!-- Start Main Top -->

<!-- End Main Top -->

<!-- Start Main Top -->
<header class="main-header">
    <!-- Start Navigation -->
<%@include file="includes/nav.jsp"%>
</header>
<!-- End Main Top -->
<%
    ProductDao productDao = new ProductDaoImp();
  List<Product> productList = productDao.getAllProducts();
%>
]
    <div class="container">
    <div class="card-header my-3">All Products</div>
    <div class="row">
<%
    if(!productList.isEmpty()){
    for(Product product : productList){%>
<div class="col-md-3 my-3">
    <div class="card w-100" style="width: 18rem;">
        <img src=images/<%=product.getImage()%> class="card-img-top" alt="...">
        <div class="card-body">
            <h5 class="card-title"><%=product.getProductName()%></h5>
            <h6 class="price">Price: $<%=product.getPrice()%></h6>
            <h6 class="category">Category: <%=product.getCategory()%></h6>
            <div class="mt-3 d-flex justify-content-between">
                <a href="AddToCart?id=<%=product.getProductId()%>" class="btn btn-primary">Add to Cart</a>
            </div>
        </div>

    </div>
</div>

<%}
    }
%>

</div>

</div>
<!-- Start Footer  -->
<footer>

</footer>
<!-- End Footer  -->

<!-- Start copyright  -->
<div class="footer-copyright">
    <p class="footer-company">All Rights Reserved. &copy; 2018 <a href="#">ThewayShop</a> Design By :
        <a href="https://html.design/">html design</a></p>
</div>
<!-- End copyright  -->

<a href="#" id="back-to-top" title="Back to top" style="display: none;">&uarr;</a>

<!-- ALL JS FILES -->
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<!-- ALL PLUGINS -->
<script src="js/jquery.superslides.min.js"></script>
<script src="js/bootstrap-select.js"></script>
<script src="js/inewsticker.js"></script>
<script src="js/bootsnav.js."></script>
<script src="js/images-loded.min.js"></script>
<script src="js/isotope.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/baguetteBox.min.js"></script>
<script src="js/form-validator.min.js"></script>
<script src="js/contact-form-script.js"></script>
<script src="js/custom.js"></script>
</body>

</html>