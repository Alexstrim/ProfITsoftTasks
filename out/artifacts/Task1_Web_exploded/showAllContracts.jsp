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
</head>
<body>
<h1>All contracts here</h1>
<table border = "1 px">
    <c:forEach var="con" items="${sessionScope.contracts}">
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
                <form action="ShowInfoContractServlet" method="get">
                    <input type="hidden" name="contractId" value="${con.number}">
                    <input type="submit" value="View contract information">
                </form>
            </td>
            <td>
                <form action="DeleteContractServlet" method="post">
                    <input type="hidden" name="contractId" value="${con.number}">
                    <input type="submit" value="Delete contract">
                </form>
            </td>
            <td>
                <form action="EditContractServlet" method="get">
                    <input type="hidden" name="contractId" value="${con.number}">
                    <input type="submit" value="Edit contract">
                </form>
            </td>
        </tr>
    </c:forEach>
    <br>
    <ul>
        <li><a href="addNewClient.jsp">Add new Client</a></li>
        <li><a href="addNewInsuredPerson.jsp">Add new Insured Person</a></li>
        <li><a href="AddContractServlet">Add new Contract</a></li>
    </ul>
</table>
</body>
</html>
