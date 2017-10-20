<!DOCTYPE html>
<%@ page language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
        <spring:message code="watto_cashier"/>
        -
        <spring:message code="buyer_directory"/>
    </title>

    <spring:url value="/css/bootstrap.min.css" var="cssUrl"/>
    <link href="${cssUrl}" rel="stylesheet">
</head>
<body>

<div id="container" class="container">

    <h1>
        <spring:message code="buyer_directory"/>
    </h1>

    <p>
        <spring:url value="/buyers/add" var="addBuyerUrl"/>
        <a href="${addBuyerUrl}" class="btn btn-success">
            <spring:message code="add"/>
        </a>

        <spring:url value="/home" var="homeUrl"/>
        <a href="${homeUrl}" class="btn btn-danger">
            <spring:message code="back_home"/>
        </a>
    </p>

    <table class="table table-striped table-bordered table-hover">
        <tr>
            <th><spring:message code="buyer.first_name"/></th>
            <th><spring:message code="buyer.last_name"/></th>
            <th><spring:message code="buyer.birth_location"/></th>
            <th>&nbsp;</th>
        </tr>

        <c:forEach items="${buyers}" var="buyer">
            <tr>
                <td>${buyer.firstName}</td>
                <td>${buyer.lastName}</td>
                <td>${buyer.birthLocation}</td>
                <td>
                    <spring:url value="/buyers/{buyerId}/edit" var="editBuyerUrl">
                        <spring:param name="buyerId" value="${buyer.id}"/>
                    </spring:url>
                    <a href="${editBuyerUrl}" class="btn btn-info">
                        <spring:message code="edit"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<spring:url value="/js/bootstrap.min.js" var="jsUrl"/>
<script src="${jsUrl}"></script>
<c:import url="../common/alert-script.jsp"/>
<c:import url="../common/success-script.jsp"/>

</body>
</html>
