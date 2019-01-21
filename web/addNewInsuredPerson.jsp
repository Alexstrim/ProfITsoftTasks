<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 18.01.2019
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Add Insured Person</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">Add new Insured Person</h1>
    </div>
    <c:if test="${not empty errors}">
        <c:forEach var="error" items="${errors}">
            <p class="text-danger"><c:out value="${error.value}"/></p>
        </c:forEach>
    </c:if>
    <form method="post" action="AddInsuredPersonServlet">
        <div class="form-group">
            <label for="firstName">First name:</label>
            <input type="text" class="form-control" id="firstName" name="firstName">
        </div>
        <div class="form-group">
            <label for="lastName">Last name:</label>
            <input type="text" class="form-control" id="lastName" name="lastName">
        </div>
        <div class="form-group">
            <label for="dateOfBirth">Date of birth:</label>
            <input type="date" class="form-control" id="dateOfBirth" name="date">
        </div>
        <div class="form-group">
            <label for="cost">Cost:</label>
            <input type="number" class="form-control" id="cost" name="cost">
        </div>
        <div class="form-group">
            <label for="number">Identification number:</label>
            <input type="number" class="form-control" id="number" name="identificationNumber">
        </div>
        <input type="submit" class="btn btn-primary col-lg-1" style="margin-right: 10px" name = "Add" value="Add">
        <a href="/ShowContractsServlet" class="btn btn-danger col-lg-1">Home</a>
    </form>
</div>
</body>
</html>
