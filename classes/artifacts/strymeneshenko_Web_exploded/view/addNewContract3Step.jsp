<%@ page import="ua.profitsoft.strymeneshenko.data.InsuredPerson" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %><%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 19.01.2019
  Time: 6:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="contr" scope="session" class="ua.profitsoft.strymeneshenko.data.Contract"/>
<html>
<head>
    <title>Add new contract [3 step]</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">Third step of adding a contract</h1>
    </div>
    <h2 class="text-info">List of insured persons: </h2>
    <table class="table">
        <c:forEach var="person" items="${sessionScope.persons}">
            <tr>
                <td>${person}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/addContract/saveInsuredPersonToContract" method="post">
                        <input type="hidden" name="personId" value="${person.id}">
                        <input type="submit" class="btn btn-success" name ="command" value="Add selected persons to contract">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form action="${pageContext.request.contextPath}/addContract/finishContract" method="post">
        <input type="submit" style="margin-right: 10px" class="btn btn-primary col-lg-1" name ="command" value="Add contract">
    </form>
    <form action="${pageContext.request.contextPath}/addContract/exit">
        <input type="submit" class="btn btn-danger col-lg-1" name="Cancel" value="Cancel">
    </form>
</div>
</body>
</html>
