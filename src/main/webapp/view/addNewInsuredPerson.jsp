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
    <script>
        document.getElementById("cost").defaultValue = "0";
    </script>
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">Add new Insured Person</h1>
    </div>
    <form method="post" action="${pageContext.request.contextPath}/addInsuredPerson">
        <div class="form-group">
            <label for="firstName">First name:</label>
            <input type="text" class="form-control" id="firstName" name="firstName">
            <c:forEach var="er" items="${requestScope['org.springframework.validation.BindingResult.insuredPerson'].getFieldErrors('firstName')}">
                <span class="text-danger">${er.defaultMessage}</span>
            </c:forEach>
        </div>
        <div class="form-group">
            <label for="lastName">Last name:</label>
            <input type="text" class="form-control" id="lastName" name="lastName">
            <c:forEach var="er" items="${requestScope['org.springframework.validation.BindingResult.insuredPerson'].getFieldErrors('lastName')}">
                <span class="text-danger">${er.defaultMessage}</span>
            </c:forEach>
        </div>
        <div class="form-group">
            <label for="dateOfBirth">Date of birth:</label>
            <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth">
            <c:forEach var="er" items="${requestScope['org.springframework.validation.BindingResult.insuredPerson'].getFieldErrors('dateOfBirth')}">
                <span class="text-danger">${er.defaultMessage}</span>
            </c:forEach>
        </div>
        <div class="form-group">
            <label for="cost">Cost:</label>
            <input type="number" class="form-control" id="cost" name="cost">
            <c:forEach var="er" items="${requestScope['org.springframework.validation.BindingResult.insuredPerson'].getFieldErrors('cost')}">
                <span class="text-danger">${er.defaultMessage}</span>
            </c:forEach>
        </div>
        <div class="form-group">
            <label for="number">Identification number:</label>
            <input type="number" class="form-control" id="number" name="identificationNumber">
            <c:forEach var="er" items="${requestScope['org.springframework.validation.BindingResult.insuredPerson'].getFieldErrors('identificationNumber')}">
                <span class="text-danger">${er.defaultMessage}</span>
            </c:forEach>
        </div>
        <input type="submit" class="btn btn-primary col-lg-1" style="margin-right: 10px" name = "Add" value="Add">
        <a href="${pageContext.request.contextPath}/" class="btn btn-danger col-lg-1">Home</a>
    </form>
</div>
</body>
</html>
