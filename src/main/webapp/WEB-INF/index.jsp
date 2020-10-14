<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products and Categories</title>
<link rel="stylesheet"
	href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row my-5">
			<div class="col-sm-4 mx-auto">
				<h1 class="display-1">Welcome</h1>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-4 mx-auto">
				<form:form action="/products" method="post" modelAttribute="newProduct">
					<div class="form-group">
						<label>Name:</label>
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
					<input type="submit" value="Add Game" class="btn btn-primary" />
				</form:form>
			</div>
			<div class="row">
				<div class="col">
					<form:form action="/categories" method="post" modelAttribute="newCategory">
						<div class="form-group">
							<label>Category Name</label>
							<form:input path="name" class="form-control" />
							<form:errors path="name" class="text-danger" />
						</div>
						<input type="submit" value="Add Genre" class="btn btn-primary" />
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>