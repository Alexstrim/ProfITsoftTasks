<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 18.01.2019
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Add Client</title>
    <script>
        <%
        if (request.getParameter("Send") != null) {
            session.setAttribute("clientType", request.getParameter("clientType"));
            session.setAttribute("Send", request.getParameter("Send"));
        }
        %>
    </script>
</head>
<body>
        <c:if test="${empty Send}">
            <h1>Select the type of client you want to add</h1><br>
            <form method="post">
                <select name = "clientType">
                    <option value="individual">Individual</option>
                    <option value="legalEntity">LegalEntity</option>
                    <input type="submit" name = "Send" value="Okey"/>
                </select>
            </form><br>
            <a href="/ShowContractsServlet">Home</a>
        </c:if>
        <c:if test="${clientType == 'individual'}">
            <jsp:useBean id="indiv" class="ua.profitsoft.strymeneshenko.data.Individual" scope="session"/>
            <h1>Add individual client</h1>
            <c:if test="${not empty errors}">
                <c:forEach var="error" items="${errors}">
                    <font color="red"><c:out value="${error.value}"/><br></font>
                </c:forEach>
            </c:if>
            <form method="post" action="AddClientServlet">
                First name:<input type="text" name="firstName"><br>
                Last name: <input type="text" name="lastName"><br>
                Address: <input type="text" name="address"><br>
                <input type="submit" name="addIndividual" value="Add Individual">
            </form><br>
            <a href="/ShowContractsServlet">Home</a>
        </c:if>
        <c:if test="${clientType == 'legalEntity'}">
            <jsp:useBean id="legal" class="ua.profitsoft.strymeneshenko.data.LegalEntity" scope="session"/>
            <h1>Add Legal Entity client</h1>
            <c:if test="${not empty errors}">
                <c:forEach var="error" items="${errors}">
                    <font color="red"><c:out value="${error.value}"/><br></font>
                </c:forEach>
            </c:if>
            <form method="post" action="AddClientServlet">
                Name Organization: <input type="text" name="nameOrganization"><br>
                Address: <input type="text" name="address"><br>
                <input type="submit" name="addLegalEntity" value="Add LegalEntity">
            </form><br>
            <a href="/ShowContractsServlet">Home</a>
        </c:if>
</body>
</html>
