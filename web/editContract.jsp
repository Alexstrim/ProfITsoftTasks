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
    <script>
        <%
        if (request.getParameter("edit") != null) {
            String dateConc = request.getParameter("dateConclusion");
            String dateStart = request.getParameter("startDate");
            String dateEnd = request.getParameter("endDate");
            if(dateConc != ""){
                editContract.setDateConclusion(UtilDate.stringToDate(dateConc,"yyyy-MM-dd"));
            }
            if(dateStart != ""){
                editContract.setStartDate(UtilDate.stringToDate(dateStart,"yyyy-MM-dd"));
            }
            if(dateEnd != ""){
                editContract.setEndDate(UtilDate.stringToDate(dateEnd,"yyyy-MM-dd"));
            }
        }
        %>
    </script>
</head>
<body>
    <h1>Fill in the fields you want to change</h1>
    <form action="/EditContractServlet" method="post">
        Date conclusion will look: <c:out value="${editContract.dateConclusion}"/><br>
        Start date will look: <c:out value="${editContract.startDate}"/><br>
        End date will look: <c:out value="${editContract.endDate}"/><br>
        <input type="submit" value="Apply">
    </form>
    <br>
    <form method="post">
        Date conclusion: <input type="date" name="dateConclusion"><br>
        Start date of contract: <input type="date" name="startDate"><br>
        End date of contract: <input type="date" name="endDate"><br>
        <input type="submit" name="edit" value="Edit">
    </form>
    <br>
    <a href="/ShowContractsServlet">cancel</a>
</body>
</html>
