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
</head>
<body>
    <h1>Second step of adding a contract</h1><br>
    <h2>List of clients who have not yet signed a contract: </h2>
    <table>
        <c:forEach var="client" items="${sessionScope.clients}">
            <tr>
                <td>${client}</td>
                <td>
                    <form action="AddContractServlet" method="post">
                        <input type="hidden" name="client" value="${client.id}">
                        <input type="submit" name ="command" value="Add to contract">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form method="post">
        <input type="submit" name="Cancel" value="Cancel">
    </form>
</body>
</html>
