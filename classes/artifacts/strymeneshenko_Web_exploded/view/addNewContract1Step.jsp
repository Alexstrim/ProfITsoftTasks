<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="ua.profitsoft.strymeneshenko.util.UtilDate" %>
<%@ page import="ua.profitsoft.strymeneshenko.validate.ContractValidate" %><%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 19.01.2019
  Time: 2:59
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="contr" scope="session" class="ua.profitsoft.strymeneshenko.data.Contract"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="outDate" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Add new contract [1 step]</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">First step of adding a contract</h1>
    </div>
    <form method="post" action="${pageContext.request.contextPath}/addContract/saveContract1">
        <div class="form-group center-block">
            <label class="col-form-label" for="conclusion">Date conclusion:</label>
            <outDate:inputDate name="dateConclusion" id="conclusion"/>
            <c:forEach var="er" items="${requestScope['org.springframework.validation.BindingResult.contract'].getFieldErrors('dateConclusion')}">
                <span class="text-danger">${er.defaultMessage}</span>
            </c:forEach>
        </div>
        <div class="form-group">
            <label for="start">Start date of contract:</label>
            <outDate:inputDate name="startDate" id="start"/>
            <c:forEach var="er" items="${requestScope['org.springframework.validation.BindingResult.contract'].getFieldErrors('startDate')}">
                <span class="text-danger">${er.defaultMessage}</span>
            </c:forEach>
        </div>
        <div class="form-group">
            <label for="end">End date of contract:</label>
            <outDate:inputDate name="endDate" id="end"/>
            <c:forEach var="er" items="${requestScope['org.springframework.validation.BindingResult.contract'].getFieldErrors('endDate')}">
                <span class="text-danger">${er.defaultMessage}</span>
            </c:forEach>
        </div>
        <input style="margin-right: 10px" class = "btn btn-primary col-lg-1" type="submit" name = "next" value="Next">
    </form>
    <form action="${pageContext.request.contextPath}/addContract/exit">
        <input class="btn btn-danger col-lg-1" type="submit" name="Cancel" value="Cancel">
    </form>
</div>
</body>
</html>
