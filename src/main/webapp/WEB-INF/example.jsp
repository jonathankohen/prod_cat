<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Games and Genres</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
    <script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <h1>Plan your party!</h1>
        <form:form action="/example" method="post" modelAttribute="newExample">
            <div class="form-group">
                <label>Party:</label>
                <form:input path="party" class="form-control" />
                <form:errors path="party" class="text-danger" />
            </div>
            <div class="form-group">
                <label>Start:</label>
                <form:input type="date" path="start" class="form-control" />
                <form:errors path="start" class="text-danger" />
            </div>
            <input type="submit" value="Plan your party!" class="btn btn-success" />
        </form:form>
    </div>
</body>
</html>