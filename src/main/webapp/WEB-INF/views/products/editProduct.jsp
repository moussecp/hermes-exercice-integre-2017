<!DOCTYPE html>
<%@ page language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
        <spring:message code="watto_cashier"/>
        -
        <spring:message code="edit"/>
    </title>

    <spring:url value="/css/bootstrap.min.css" var="cssUrl"/>
    <link href="${cssUrl}" rel="stylesheet">
</head>
<body>

<div id="container" class="container">

    <h1>
        <spring:message code="product.edit"/>
    </h1>

    <spring:url value="/products/{productId}" var="editProductUrl">
        <spring:param name="productId" value="${product.id}"/>
    </spring:url>
    <f:form action="${editProductUrl}" method="POST" modelAttribute="product" cssClass="form-horizontal">
        <c:import url="productForm.jsp"/>
    </f:form>
</div>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="../js/bootstrap.min.js"></script>
</body>
</html>
