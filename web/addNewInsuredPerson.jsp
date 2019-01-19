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
</head>
<body>
    <h1>Add new Insured Person</h1><br>
    <c:if test="${not empty errors}">
        <c:forEach var="error" items="${errors}">
            <font color="red"><c:out value="${error.value}"/></font><br>
        </c:forEach>
    </c:if>
    <form method="post" action="AddInsuredPersonServlet">
        First name: <input type="text" name="firstName"><br>
        Last name: <input type="text" name="lastName"><br>
        Date of birth: <input type="date" name="date"><br>
        Cost: <input type="number" name="cost"><br>
        Identification number: <input type="number" name="identificationNumber"><br>
        <input type="submit" name = "Add" value="Add">
    </form>
    <a href="/ShowContractsServlet">Home</a>
</body>
</html>
