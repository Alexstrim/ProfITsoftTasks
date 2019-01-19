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
</head>
<body>
    <h1>Info about chosen contract:</h1><br>
    <h3>Number:</h3> <c:out value="${contract.number}"/><br>
    <h3>Date conclusion: </h3><c:out value="${contract.dateConclusion}"/><br>
    <h3>Start date: </h3><c:out value="${contract.startDate}"/><br>
    <h3>End date: </h3><c:out value="${contract.endDate}"/><br>
    <h3>Client: </h3><c:out value="${contract.client}"/><br>
    <h3>Insured persons list:</h3>
        <ul>
            <c:forEach var="person" items="${contract.persons}">
                <li><c:out value="${person}"/></li>
            </c:forEach>
        </ul>
    <a href="ShowContractsServlet">Home</a>
</body>
</html>
