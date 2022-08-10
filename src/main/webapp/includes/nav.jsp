<%@ page import="static java.lang.System.in" %>
<%
  HttpSession session1 = request.getSession(false);

  String sesion = (String) session1.getAttribute("name");
%>
<nav class="navbar navbar-expand-lg navbar-light bg-light navbar-default bootsnav">
  <div class="container">
    <!-- Start Header Navigation -->
    <div class="navbar-header">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-menu" aria-controls="navbars-rs-food" aria-expanded="false" aria-label="Toggle navigation">
        <i class="fa fa-bars"></i>
      </button>
      <a class="navbar-brand" href="index.html"><img src="images/logo.png" class="logo" alt=""></a>
    </div>
    <!-- End Header Navigation -->

    <!-- Collect the nav links, forms, and other content for toggling -->
    <%
      if(sesion!=null){%>
    <div class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp">
      <div class="collapse navbar-collapse" id="navbar-menu">
      <ul class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp">
      <li class="nav-item active"><a class="nav-link" href="index.jsp">Home</a></li>
      <li class="nav-item"><a class="nav-link" href="about.html">About Us</a></li>
      <li class="nav-item"><a class="nav-link" href="contact-us">contact us</a></li>
        <li class="nav-item"><a class="nav-link" href="logout">Log out</a></li>
      </ul>
      </div>
    <div class="attr-nav">
      <ul>
        <li class="search"><a href="#"><i class="fa fa-search"></i></a></li>
        <li class="side-menu">
          <a href="#">
            <i class="fa fa-shopping-bag"></i>
            <span class="badge">3</span>
            <p>My Cart</p>
          </a>
        </li>
      </ul>
    </div>
      <%}
      else{%>
      <div class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp">
        <div class="collapse navbar-collapse" id="navbar-menu">
          <ul class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp">
            <li class="nav-item active"><a class="nav-link" href="index.jsp">Home</a></li>
            <li class="nav-item"><a class="nav-link" href="about.html">About Us</a></li>
            <li class="nav-item"><a class="nav-link" href="login">Login</a></li>
            <li class="nav-item"><a class="nav-link" href="signup">Sign up</a></li>
            <li class="nav-item"><a class="nav-link" href="contact-us">contact us</a></li>
          </ul>
        </div>
      </div>
     <%}
    %>
    <!-- /.navbar-collapse -->
  <!-- Start Side Menu -->
  <div class="side">
    <a href="#" class="close-side"><i class="fa fa-times"></i></a>
    <li class="cart-box">
      <ul class="cart-list">
        <li>
          <a href="#" class="photo"><img src="images/img-pro-01.jpg" class="cart-thumb" alt="" /></a>
          <h6><a href="#">Delica omtantur </a></h6>
          <p>1x - <span class="price">$80.00</span></p>
        </li>
        <li>
          <a href="#" class="photo"><img src="images/img-pro-02.jpg" class="cart-thumb" alt="" /></a>
          <h6><a href="#">Omnes ocurreret</a></h6>
          <p>1x - <span class="price">$60.00</span></p>
        </li>
        <li>
          <a href="#" class="photo"><img src="images/img-pro-03.jpg" class="cart-thumb" alt="" /></a>
          <h6><a href="#">Agam facilisis</a></h6>
          <p>1x - <span class="price">$40.00</span></p>
        </li>
        <li class="total">
          <a href="#" class="btn btn-default hvr-hover btn-cart">VIEW CART</a>
          <span class="float-right"><strong>Total</strong>: $180.00</span>
        </li>
      </ul>
    </li>
  </div>
  <!-- End Side Menu -->
</nav>
<!-- End Navigation -->