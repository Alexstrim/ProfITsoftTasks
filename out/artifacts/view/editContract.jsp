<%@ page import="ua.profitsoft.strymeneshenko.util.UtilDate" %><%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 19.01.2019
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="editContract" scope="session" class="ua.profitsoft.strymeneshenko.data.Contract"/>
<html>
<head>
    <title>Edit contract</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">Fill in the fields you want to change</h1>
    </div>
    <form action="${pageContext.request.contextPath}/applyEditContract" method="post">
        <h3 class="text-info">Date conclusion will look: <c:out value="${editContract.dateConclusion}"/></h3>
        <h3 class="text-info">Start date will look: <c:out value="${editContract.startDate}"/></h3>
        <h3 class="text-info">End date will look: <c:out value="${editContract.endDate}"/></h3>
        <input type="submit" class="btn btn-primary" value="Apply">
    </form>
    <br>
    <form action="${pageContext.request.contextPath}/editContract" method="post">
        <div class="form-group">
            <label for="conclusion">Date conclusion:</label>
            <input type="date" class="form-control" id="conclusion" name="dateConclusion">
        </div>
        <div class="form-group">
            <label for="start">Start date of contract:</label>
            <input type="date" class="form-control" id="start" name="startDate">
        </div>
        <div class="form-group">
            <label for="end">End date of contract:</label>
            <input type="date" class="form-control" id="end" name="endDate">
        </div>
        <input type="submit"  class="btn btn-success col-lg-1" style="margin-right: 10px" name="edit" value="Edit">
        <a class="btn btn-danger col-lg-1" href="${pageContext.request.contextPath}/">cancel</a>
    </form>
</div>
</body>
</html>
