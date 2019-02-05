<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 17.01.2019
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Show all contracts</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>--%>
    <%--<script src="${pageContext.request.contextPath}/js/application.js"></script>--%>
    <script>
        class Client{
            constructor(){
                this.id = 0;
                this.adress = '';
                this.toString = () => "Client {" + this.id + "; " + this.adress + "}";
            }
        }
        var client = new Client();
        function deleteContract() {
            return confirm("Are you sure that you want to delete contract?");
        }
        function getList() {
            return fetch("${pageContext.request.contextPath}/loadContracts", {
                method: "GET",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).then(function (resp) {
                let jsonObj = resp.json();
                console.log(jsonObj);
                return jsonObj;
            }).then(function (myContracts) {
                for(i in myContracts){
                    const $tbody = document.getElementById('tableBody__for_add_js');
                    let tr = document.createElement('tr');
                    let td1 = document.createElement('td');
                    let td2 = document.createElement('td');
                    let td3 = document.createElement('td');
                    let td4 = document.createElement('td');
                    let td5 = document.createElement('td');
                    let td6 = document.createElement('td');
                    let td7 = document.createElement('td');
                    client.id = myContracts[i].client.id;
                    client.adress = myContracts[i].client.adress;
                    td1.innerHTML = myContracts[i].number;
                    td2.innerHTML = client;
                    td3.innerHTML = new Date(myContracts[i].dateConclusion);
                    td4.innerHTML = new Date(myContracts[i].startDate);
                    td5.innerHTML = new Date(myContracts[i].endDate);
                    var text = '';
                    for(j in myContracts[i].persons){
                        text += myContracts[i].persons[j].lastName + "<br>";
                    }
                    td6.innerHTML = text;
                    tr.appendChild(td1);
                    tr.appendChild(td2);
                    tr.appendChild(td3);
                    tr.appendChild(td4);
                    tr.appendChild(td5);
                    tr.appendChild(td6);

                    $tbody.appendChild(tr);
                }
                return myContracts;
            });
        }
        console.log(getList());
    </script>
</head>
<body>
<div class="container-fluid">
    <div class = "jumbotron">
        <h1 class="text-center">All contracts here</h1>
    </div>
    <ul class = "list-group">
        <li class = "list-group-item" ><a href="${pageContext.request.contextPath}/view/addNewClient.jsp" class="btn btn-success btn-block">Add new Client</a></li>
        <li class = "list-group-item" ><a href="${pageContext.request.contextPath}/view/addNewInsuredPerson.jsp" class="btn btn-success btn-block">Add new Insured Person</a></li>
        <li class = "list-group-item" ><a href="${pageContext.request.contextPath}/addContract" class="btn btn-success btn-block">Add new Contract</a></li>
    </ul>
<table class="table" id="table_js">
        <tr>
            <th>№</th>
            <th>Client</th>
            <th>Date conclusion</th>
            <th>Start date</th>
            <th>End date</th>
            <th colspan="4">Persons list</th>
        </tr>
    <c:forEach var="con" items="${contracts}">
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
                <form action="${pageContext.request.contextPath}/showInfoContract" method="get">
                    <input type="hidden" name="contractId" value="${con.number}">
                    <input type="submit" class="btn btn-info" value="View contract information">
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/deleteContract" method="post">
                    <input type="hidden" name="contractId" value="${con.number}">
                    <input type="submit" class="btn btn-danger" value="Delete contract" onclick="return deleteContract()">
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/goEditContract" method="get">
                    <input type="hidden" name="contractId" value="${con.number}">
                    <input type="submit" class="btn btn-success" value="Edit contract">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
    <div class="jumbotron">
        <h2 class="text-center">Conclusion of contract data using JS</h2>
    </div>
    <table class = "table">
        <tr>
            <th>№</th>
            <th>Client</th>
            <th>Date conclusion</th>
            <th>Start date</th>
            <th>End date</th>
            <th colspan="4">Persons list</th>
        </tr>
        <tbody id = "tableBody__for_add_js">

        </tbody>
    </table>
</div>
</body>
</html>
