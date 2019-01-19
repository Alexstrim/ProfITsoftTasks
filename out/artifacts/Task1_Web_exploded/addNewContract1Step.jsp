<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="ua.profitsoft.strymeneshenko.util.UtilDate" %>
<%@ page import="ua.profitsoft.strymeneshenko.validate.ContractValidate" %><%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 19.01.2019
  Time: 2:59
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="contr" scope="session" class="ua.profitsoft.strymeneshenko.data.Contract"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="outDate" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Add new contract [1 step]</title>
    <script>
        <%
            if(request.getParameter("Cancel") != null){
                session.removeAttribute("contr");
                response.sendRedirect("ShowContractsServlet");
            }
            if(request.getParameter("next") != null){
                contr.setDateConclusion(UtilDate.stringToDate(request.getParameter("dateConclusion"),"yyyy-MM-dd"));
                contr.setStartDate(UtilDate.stringToDate(request.getParameter("startDate"),"yyyy-MM-dd"));
                contr.setEndDate(UtilDate.stringToDate(request.getParameter("endDate"),"yyyy-MM-dd"));
                ContractValidate validate = new ContractValidate();
                Map<String,String> errors = validate.validate(contr);
                request.setAttribute("er", errors);
                if(errors.size() == 0){
                    response.sendRedirect("addNewContract2Step.jsp");
                }
            }
        %>
    </script>
</head>
<body>
    <h1>First step of adding a contract</h1><br>
    <c:if test="${not empty er}">
        <c:forEach var="error" items="${er}">
            <font color="red"><c:out value="${error.value}"/><br></font>
        </c:forEach>
    </c:if>
    <form method="post">
        Date conclusion: <outDate:inputDate name="dateConclusion"/><br>
        Start date of contract: <outDate:inputDate name="startDate"/><br>
        End date of contract: <outDate:inputDate name="endDate"/><br>
        <input type="submit" name = "next" value="Next">
    </form>
    <form method="post">
        <input type="submit" name="Cancel" value="Cancel">
    </form>
</body>
</html>
