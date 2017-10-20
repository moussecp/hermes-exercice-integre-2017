<%@ page language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<div class="control-group">
    <f:label cssClass="control-label" path="label"><spring:message code="product.label"/></f:label>
    <div class="controls">
        <f:input path="label"/>
        <f:errors path="label"/>
    </div>
</div>

<div class="control-group">
    <f:label cssClass="control-label" path="description"><spring:message code="product.description"/></f:label>
    <div class="controls">
        <f:input path="description"/>
        <f:errors path="description"/>
    </div>
</div>

<div class="control-group">
    <f:label cssClass="control-label" path="price"><spring:message code="product.price"/></f:label>
    <div class="controls">
        <f:input path="price"/>
        <f:errors path="price"/>
    </div>
</div>

<div class="control-group">
    <div class="controls">
        <button class="btn btn-success">
            <spring:message code="save"/>
        </button>
        <spring:url value="/products" var="productsUrl"/>
        <a href="${productsUrl}" class="btn btn-danger">
            <spring:message code="product.list.back"/>
        </a>
    </div>
</div>

