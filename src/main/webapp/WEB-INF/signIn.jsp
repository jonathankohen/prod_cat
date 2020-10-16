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
	   <c:if test="${user != null}">
		   <div class="alert alert-info">Welcome ${user.firstName}!</div>
	   </c:if>
		<div class="row">
			<!-- <div class="col-sm-8">
				<div class="card">
					<div class="card-header bg-dark text-light">
						Register
					</div>
					<div class="card-body">
						Form
					</div>
				</div>
			</div> -->
			<form:form class="col-sm-8" action="/register" method="post"
				modelAttribute="registerringUser">
				<div class="row">
					<div class="form-group col-sm-6">
						<label>First Name</label>
						<form:input path="firstName" class="form-control" />
						<form:errors path="firstName" class="text-danger" />
					</div>
					<div class="form-group col-sm-6">
						<label>Last Name</label>
						<form:input path="lastName" class="form-control" />
						<form:errors path="lastName" class="text-danger" />
					</div>
				</div>
				<div class="form-group">
					<label>Email</label>
					<form:input path="email" class="form-control" />
					<form:errors path="email" class="text-danger" />
				</div>
				<div class="row">
					<div class="form-group col-sm-6">
						<label>Password</label>
						<form:input type="password" path="password" class="form-control" />
						<form:errors path="password" class="text-danger" />
					</div>
					<div class="form-group col-sm-6">
						<label>Confirm Password</label>
						<form:input type="password" path="confirm" class="form-control" />
						<form:errors path="confirm" class="text-danger" />
					</div>
				</div>
				<input type="submit" value="Register" class="btn btn-primary" />
			</form:form>
			<form:form class="col-sm-4" action="/login" method="post"
				modelAttribute="loginUser">
				<div class="form-group">
					<label>Email</label>
					<form:input path="email" class="form-control" />
					<form:errors path="email" class="text-danger" />
				</div>
				<div class="form-group">
					<label>Password</label>
					<form:input type="password" path="password" class="form-control" />
					<form:errors path="password" class="text-danger" />
				</div>
				<input type="submit" value="Login" class="btn btn-primary" />
			</form:form>

		</div>
	</div>
</body>
</html>