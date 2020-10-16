<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Products and Categories</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
    <script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">    
        <h1>${title}</h1>
        <div class="row">
           <div class="col-sm-4">
               <form:form action="/products/new" method="post" modelAttribute="newProductPlus">
                   <div class="form-group">
                       <label>Name</label>
                       <form:input path="name" class="form-control" />
                       <form:errors path="name" class="text-danger" />
                   </div>
                   <div class="form-group">
                       <label>Description</label>
                       <form:input path="description" class="form-control" />
                       <form:errors path="description" class="text-danger" />
                   </div>
                   <div class="form-group">
                       <label>Price</label>
                       <form:input path="price" class="form-control" />
                       <form:errors path="price" class="text-danger" />
                   </div>
                   <div class="form-group">
                       <label>Categories</label>
                       <form:input path="categoryInput" class="form-control" />
                       <form:errors path="categoryInput" class="text-danger" />
                   </div>
                   <input type="submit" value="Add Product" class="btn btn-primary" />
               </form:form>
           </div>
           <div class="col-sm-8">
               <table class="table">
                   <tr>
                       <th>Name</th>
                       <th>Description</th>
                       <th>Price</th>
                       <th>Categories</th>
                       <th>Average Rating</th>
                   </tr>
                   <c:forEach items="${allProducts}" var="product">
                      <tr>
                          <td><a href="/products/${product.id}">${product.name}</a></td>
                          <td>${product.description}</td>
                          <td>${product.price}</td>
                          <td>${product.categoriesDescription()}</td>
                          <td>${product.getAverageRating()}</td>
                      </tr>
                   </c:forEach>
                </table>
           </div>
        </div>
    </div>
    
</body>
</html>