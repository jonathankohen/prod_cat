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
	    <h1 class="my-5">Products and Categories</h1>
	    <div class="row">
	       <div class="col-sm-6">
	           <form:form action="/products" method="post" modelAttribute="newProduct">
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
                   <input type="submit" value="Add Product" class="btn btn-primary" />
               </form:form>
	       </div>
	       <div class="col-sm-6">
               <form:form action="/categories" method="post" modelAttribute="newCategory">
                   <div class="form-group">
                       <label>Category Name</label>
                       <form:input path="name" class="form-control" />
                       <form:errors path="name" class="text-danger" />
                   </div>
                   <input type="submit" value="Add Category" class="btn btn-primary" />
               </form:form>
           </div>
	    </div>
	    <div class="row mt-5">
	       <div class="col-sm-9">
	           <table class="table">
		           <tr>
		               <th>Name</th>
		               <th>Description</th>
		               <th>Price</th>
		               <th>Categories</th>
		           </tr>
		           <c:forEach items="${allProducts}" var="product">
		              <tr>
		                  <td>${product.name}</td>
		                  <td>${product.description}</td>
		                  <td>${product.price}</td>
		                  <td>${product.categoryDescription()}</td>
		              </tr>
		           </c:forEach>
		        </table>
	       </div>
	       <div class="col-sm-3">
	           <form action="/add_category" method="post">
	               <div class="form-group">
                       <label>Product</label>
                       <select name="product_id" class="form-control">
                            <c:forEach items="${allProducts}" var="product">
                                <option value="${product.id}">${product.name} (${product.price})</option>
                            </c:forEach>
                       </select>
                   </div>
                   <div class="form-group">
                       <label>Category</label>
                       <select name="category_id" class="form-control">
                            <c:forEach items="${allCategories}" var="category">
                                <option value="${category.id}">${category.name}</option>
                            </c:forEach>
                       </select>
                   </div>
                   <input type="submit" value="Add Category To Product" class="btn btn-primary" />
	           </form>
	       </div>
	    </div>
    </div>
    
</body>
</html>