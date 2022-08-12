<%@ page import="Dao.Interface.ProductDao" %>
<%@ page import="model.Product" %>
<%@ page import="Dao.ProductDaoImp" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 8/10/22
  Time: 2:10 AM
  To change this template use File | Settings | File Templates.
--%>
<html>
<%
  HttpSession session2 = request.getSession(false);
  String adminsession2 = (String) session2.getAttribute("name");
  String adminRole2 = (String) session2.getAttribute("customerRole");
  if(adminsession2 == null){
    response.sendRedirect("index.jsp");
  }
  else if(!adminRole2.equals("Admin")){
    response.sendRedirect("index.jsp");
  }
%>


<head>
    <%@include file="includes/head.jsp"%>
</head>
<body>
<%@include file="includes/nav.jsp"%>
<div>
<table class="table table-striped">
  <thead>
  <tr>
    <th scope="col">S/N</th>
    <th scope="col">Product Name</th>
    <th scope="col">Category</th>
    <th scope="col">Price</th>
    <th scope="col">Quantity</th>
  </tr>
  </thead>
  <tbody>
    <%
      ProductDao productDao = new ProductDaoImp();
  List<Product> productList = productDao.getAllProducts();
  int index = 1;
  %>
  <%

  for(Product product : productList){%>
  <tr>
    <th scope="row"><%=index++%></th>
    <td><%=product.getProductName()%></td>
    <td><%=product.getCategory()%></td>
    <td><%=product.getPrice()%></td>
    <td><%=product.getQuantity()%></td>
    <td><a class="btn btn-info" href="UpdateProductPage?id=<%=product.getProductId()%>">Edit</a>&nbsp;
      <a class="btn btn-danger" href="DeleteProduct?id=<%=product.getProductId()%>">Delete</a>
    </td>
  </tr><%} %>
</div>
<%--  <%@include file="includes/footer.jsp"%>--%>
</body>
</html>
