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
    	<div class="card">
    		<div class="card-header bg-dark text-light">
    			${user.firstName} ${user.lastName}
    		</div>
    		<div class="card-body">
    			<p>${user.email}</p>
    			<p>Member since: ${user.createdAt}</p>
    			<p>Products uploaded: ${user.products.size()}</p>
    			<p>Reviews written: ${user.reviews.size()}</p>
    			<h4>Games you haven't reviewed yet:</h4>
    			<ul>
    				<c:forEach items="${productsToReview}" var="product">
    					<li><a href="/products/${product.id}">${product.name}</a></li>
    				</c:forEach>
    			</ul>
    		</div>
    	</div>
    </div>

</body>
</html>