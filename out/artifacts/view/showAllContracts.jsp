<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 17.01.2019
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Show all contracts</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class = "jumbotron">
        <h1 class="text-center">All contracts here</h1>
    </div>
    <ul class = "list-group">
        <li class = "list-group-item" ><a href="${pageContext.request.contextPath}/view/addNewClient.jsp" class="btn btn-success btn-block">Add new Client</a></li>
        <li class = "list-group-item" ><a href="${pageContext.request.contextPath}/view/addNewInsuredPerson.jsp" class="btn btn-success btn-block">Add new Insured Person</a></li>
        <li class = "list-group-item" ><a href="${pageContext.request.contextPath}/addContract" class="btn btn-success btn-block">Add new Contract</a></li>
    </ul>
<table class="table">
        <tr>
            <th>№</th>
            <th>Client</th>
            <th>Date conclusion</th>
            <th>Start date</th>
            <th>End date</th>
            <th colspan="4">Persons list</th>
        </tr>
    <c:forEach var="con" items="${contracts}">
        <%--<c:set var="contractId" value="${con.number}" scope="session"/>--%>
        <tr>
            <td>${con.number}</td>
            <td><c:out value="${con.client}"/></td>
            <td>${con.dateConclusion}</td>
            <td>${con.startDate}</td>
            <td>${con.endDate}</td>
            <td>
                <ul>
                    <c:forEach var="ip" items="${con.persons}">
                        <li><c:out value="${ip.lastName}"/></li>
                    </c:forEach>
                </ul>
            </td>
            <td>
                <form action="showInfoContract" method="get">
                    <input type="hidden" name="contractId" value="${con.number}">
                    <input type="submit" class="btn btn-info" value="View contract information">
                </form>
            </td>
            <td>
                <form action="deleteContract" method="post">
                    <input type="hidden" name="contractId" value="${con.number}">
                    <input type="submit" class="btn btn-danger" value="Delete contract">
                </form>
            </td>
            <td>
                <form action="goEditContract" method="get">
                    <input type="hidden" name="contractId" value="${con.number}">
                    <input type="submit" class="btn btn-success" value="Edit contract">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
