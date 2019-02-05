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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
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
<div class="container">
        <c:if test="${empty Send}">
            <div class="jumbotron">
                <h1 class="text-center">Select the type of client you want to add</h1>
            </div>
            <form method="post">
                <select class="form-control" name = "clientType">
                    <option value="individual">Individual</option>
                    <option value="legalEntity">LegalEntity</option>
                </select>
                <br>
                <input type="submit" class="btn btn-success col-lg-1" style="margin-right: 10px" name = "Send" value="Okey"/>
                <a href="${pageContext.request.contextPath}/" class="btn btn-danger col-lg-1">Home</a>
            </form>
        </c:if>
        <c:if test="${clientType == 'individual'}">
            <jsp:useBean id="indiv" class="ua.profitsoft.strymeneshenko.data.Individual" scope="session"/>
            <div class="jumbotron">
                <h1 class="text-center">Add individual client</h1>
            </div>

            <form method="post" action="${pageContext.request.contextPath}/addIndividual">
                <div class="form-group">
                    <label for="firstName">First name:</label>
                    <input type="text" id="firstName" class="form-control" name="firstName">
                    <c:forEach var="er" items="${requestScope['org.springframework.validation.BindingResult.individual'].getFieldErrors('firstName')}">
                        <span class="text-danger">${er.defaultMessage}</span>
                    </c:forEach>
                </div>
                <div class="form-group">
                    <label for="lastName">Last name:</label>
                    <input type="text" id="lastName" class="form-control" name="lastName">
                    <c:forEach var="er" items="${requestScope['org.springframework.validation.BindingResult.individual'].getFieldErrors('lastName')}">
                        <span class="text-danger">${er.defaultMessage}</span>
                    </c:forEach>
                </div>
                <div class="form-group">
                    <label for="address">Address:</label>
                    <input type="text" id="address" class="form-control" name="adress">
                    <c:forEach var="er" items="${requestScope['org.springframework.validation.BindingResult.individual'].getFieldErrors('adress')}">
                        <span class="text-danger">${er.defaultMessage}</span>
                    </c:forEach>
                </div>
                <input type="submit" name="addIndividual" class="btn btn-primary" style="margin-right: 10px" value="Add Individual">
                <a href="${pageContext.request.contextPath}/" class="btn btn-danger">Home</a>
            </form>
        </c:if>
        <c:if test="${clientType == 'legalEntity'}">
            <jsp:useBean id="legal" class="ua.profitsoft.strymeneshenko.data.LegalEntity" scope="session"/>
            <div class="jumbotron">
                <h1 class="text-center">Add Legal Entity client</h1>
            </div>

            <form method="post" action="${pageContext.request.contextPath}/addLegalEntity">
                <div class="form-group">
                    <label for="nameOrganization">Name Organization:</label>
                    <input type="text" class="form-control" id="nameOrganization" name="nameOrganization">
                    <c:forEach var="er" items="${requestScope['org.springframework.validation.BindingResult.legalEntity'].getFieldErrors('nameOrganization')}">
                        <span class="text-danger">${er.defaultMessage}</span>
                    </c:forEach>
                </div>
                <div class="form-group">
                    <label for="addressOrganization">Address:</label>
                    <input type="text" class="form-control" id="addressOrganization" name="adress">
                    <c:forEach var="er" items="${requestScope['org.springframework.validation.BindingResult.legalEntity'].getFieldErrors('adress')}">
                        <span class="text-danger">${er.defaultMessage}</span>
                    </c:forEach>
                </div>
                <input type="submit" name="addLegalEntity" style="margin-right: 10px" class="btn btn-primary" value="Add LegalEntity">
                <a href="${pageContext.request.contextPath}/" class="btn btn-danger">Home</a>
            </form>
        </c:if>
</div>
</body>
</html>
