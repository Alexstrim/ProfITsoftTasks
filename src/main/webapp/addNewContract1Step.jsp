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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
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
<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">First step of adding a contract</h1>
    </div>
   <%-- <c:if test="${not empty er}">
        <!-- Trigger the modal with a button -->
        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">Show the fields that you must fill</button>
        <!-- Modal -->
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">You must fill</h4>
                    </div>
                    <div class="modal-body">
                        <c:forEach var="error" items="${er}">
                            <font class="text-danger"><c:out value="${error.value}"/><br></font>
                        </c:forEach>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </c:if>--%>
    <form method="post">
        <div class="form-group center-block">
            <label class="col-form-label" for="conclusion">Date conclusion:</label>
            <outDate:inputDate name="dateConclusion" id="conclusion"/>
        </div>
        <div class="form-group">
            <label for="start">Start date of contract:</label>
            <outDate:inputDate name="startDate" id="start"/>
        </div>
        <div class="form-group">
            <label for="end">End date of contract:</label>
            <outDate:inputDate name="endDate" id="end"/>
        </div>
        <input style="margin-right: 10px" class = "btn btn-primary col-lg-1" type="submit" name = "next" value="Next">
    </form>
    <form method="post">
        <input class="btn btn-danger col-lg-1" type="submit" name="Cancel" value="Cancel">
    </form>
</div>
</body>
</html>
