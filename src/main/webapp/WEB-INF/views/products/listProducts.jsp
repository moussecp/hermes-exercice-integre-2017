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
        <spring:message code="product_list"/>
    </title>

    <spring:url value="/css/bootstrap.min.css" var="cssUrl"/>
    <link href="${cssUrl}" rel="stylesheet">
</head>
<body>

<div id="container" class="container">

    <h1>
        <spring:message code="product_list"/>
    </h1>

    <p>
        <spring:url value="/products/add" var="addProductUrl"/>
        <a href="${addProductUrl}" class="btn btn-success">
            <spring:message code="add"/>
        </a>

        <spring:url value="/home" var="homeUrl"/>
        <a href="${homeUrl}" class="btn btn-danger">
            <spring:message code="back_home"/>
        </a>
    </p>

    <table class="table table-striped table-bordered table-hover">
        <tr>
            <th><spring:message code="product.label"/></th>
            <th><spring:message code="product.description"/></th>
            <th><spring:message code="product.price"/></th>
            <th><spring:message code="product.category"/></th>
            <th><spring:message code="product.department"/></th>
            <th></th>
        </tr>

        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.label}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td>${product.category.name}</td>
                <td>${product.category.department}</td>
                <td>
                    <spring:url value="/products/{productId}/edit" var="editProductUrl">
                        <spring:param name="productId" value="${product.id}"/>
                    </spring:url>
                    <a href="${editProductUrl}" class="btn btn-info">
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
