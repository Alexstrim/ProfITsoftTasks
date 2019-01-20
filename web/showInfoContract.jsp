<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 19.01.2019
  Time: 1:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Show contract information</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="jumbotron">
        <h1 class="text-center">Info about chosen contract:</h1><br>
        <h3 class="text-info">Number:</h3> <c:out value="${contract.number}"/><br>
        <h3 class="text-info">Date conclusion: </h3><c:out value="${contract.dateConclusion}"/><br>
        <h3 class="text-info">Start date: </h3><c:out value="${contract.startDate}"/><br>
        <h3 class="text-info">End date: </h3><c:out value="${contract.endDate}"/><br>
        <h3 class="text-info">Client: </h3><c:out value="${contract.client}"/><br>
        <h3 class="text-info">Insured persons list:</h3>
        <ul class = "list-group">
            <c:forEach var="person" items="${contract.persons}">
                <li class="list-group-item"><c:out value="${person}"/></li>
            </c:forEach>
        </ul>
        <a href="ShowContractsServlet" class="btn btn-info">Home</a>
    </div>
</div>
</body>
</html>
