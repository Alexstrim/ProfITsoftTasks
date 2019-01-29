<%@ page import="ua.profitsoft.strymeneshenko.util.UtilDate" %><%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 19.01.2019
  Time: 3:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="myFuncUtil" uri="utilDate.tld" %>
<jsp:useBean id="contr" scope="session" class="ua.profitsoft.strymeneshenko.data.Contract"/>
<html>
<head>
    <title>Add new contract [2 step]</title>
    <%--Doesn't work, couse variable requestScope.dateConclusion = "", don't understand why--%>
   <%-- <jsp:setProperty name="contr" property="dateConclusion" value="${myFuncUtil:transformDate(requestScope.dateConclusion,'yyyy-MM-dd')}"/>--%>
    <%
        if(request.getParameter("Cancel") != null){
            session.removeAttribute("contr");
            response.sendRedirect("ShowContractsServlet");
        }
    %>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class = "container">
    <div class="jumbotron">
        <h1 class="text-center">Second step of adding a contract</h1>
    </div>
    <h2 class="text-info">List of clients who have not yet signed a contract: </h2>
    <table class="table">
        <c:forEach var="client" items="${sessionScope.clients}">
            <tr>
                <td>${client}</td>
                <td>
                    <form action="AddContractServlet" method="post">
                        <input type="hidden" name="client" value="${client.id}">
                        <input class="btn btn-success" type="submit" name ="command" value="Add to contract">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form method="post">
        <input type="submit" class="btn btn-danger" name="Cancel" value="Cancel">
    </form>
</div>
</body>
</html>
