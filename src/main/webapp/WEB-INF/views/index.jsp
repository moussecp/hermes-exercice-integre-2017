<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<%@ page language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         session="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
        <spring:message code="watto_cashier"/>
        -
        <spring:message code="home"/>
    </title>

    <spring:url value="/css/bootstrap.min.css" var="cssUrl"/>
    <link href="${cssUrl}" rel="stylesheet">
</head>
<body>

<div id="container" class="container">
    <h1>
        <spring:message code="watto_cashier"/>
    </h1>

    <spring:url value="/buyers" var="buyerUrl"/>
    <a href="${buyerUrl}" class="btn btn-large btn-block btn-primary">
        <spring:message code="buyer_directory"/>
    </a>

    <spring:url value="/products" var="productUrl"/>
    <a href="${productUrl}" class="btn btn-large btn-block btn-primary">
        <spring:message code="list_products"/>
    </a>
</div>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<spring:url value="/js/bootstrap.min.js" var="jsUrl"/>
<script src="${jsUrl}"></script>
</body>
</html>
