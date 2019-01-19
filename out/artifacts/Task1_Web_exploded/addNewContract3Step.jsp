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
    <script>
        <%
            if(request.getParameter("Cancel") != null){
                response.sendRedirect("ShowContractsServlet");
            }
        %>
    </script>
</head>
<body>
    <h1>Third step of adding a contract</h1><br>
    <h2>List of insured persons: </h2>
    <table>
        <c:forEach var="person" items="${sessionScope.persons}">
            <tr>
                <td>${person}</td>
                <td>
                    <form action="AddContractServlet" method="post">"
                        <input type="hidden" name="personId" value="${person.id}">
                        <input type="submit" name ="command" value="Add selected persons to contract">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form action="AddContractServlet" method="post">"
        <input type="submit" name ="command" value="Add contract">
    </form>
    <form method="post">
        <input type="submit" name="Cancel" value="Cancel">
    </form>
</body>
</html>
