<%@ page import="Dao.Interface.ProductDao" %>
<%@ page import="model.Product" %>
<%@ page import="Dao.ProductDaoImp" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
      <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
      <%
      int userId = (int) session1.getAttribute("userId");
      CartDao productDao = new CartDao();
  List<Product> productList = productDao.getAllCarts(userId);
  int index = 1;
  %>
        <%int total = 0; %>
      <%
  for(Product product : productList){%>
    <tr>
      <th scope="row"><%=index++%></th>
      <td><%=product.getProductName()%></td>
      <td><%=product.getCategory()%></td>
      <td><%=product.getPrice()%></td>
      <td><%=product.getQuantity()%></td>
      <td>
        <a class="btn btn-danger" href="DeleteCartProduct?id=<%=product.getProductId()%>">Delete</a>
      </td>
      <% total =total + (Integer.parseInt(product.getPrice().substring(1)) * product.getQuantity());%>
    </tr><%} %>
</div>
<%--  <%@include file="includes/footer.jsp"%>--%>
<button type="button" class="btn btn-outline-primary">CheckOut</button>
<div><h5>Total Price = $<%=total%></h5></div>

</body>
</html>
