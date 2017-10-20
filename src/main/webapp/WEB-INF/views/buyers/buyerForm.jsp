<%@ page language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<div class="control-group">
    <f:label cssClass="control-label" path="firstName"><spring:message code="buyer.first_name"/></f:label>
    <div class="controls">
        <f:input path="firstName"/> <!-- TODO CHECK THIS -->
        <f:errors path="firstName"/>
    </div>
</div>

<div class="control-group">
    <f:label cssClass="control-label" path="lastName"><spring:message code="buyer.last_name"/></f:label>
    <div class="controls">
        <f:input path="lastName"/> <!-- TODO CHECK THIS -->
        <f:errors path="lastName"/>
    </div>
</div>

<div class="control-group">
    <f:label cssClass="control-label" path="birthLocation"><spring:message code="buyer.birth_location"/></f:label>
    <div class="controls">
        <f:input path="birthLocation"/> <!-- TODO CHECK THIS -->
        <f:errors path="birthLocation"/>
    </div>
</div>

<div class="control-group">
    <div class="controls">
        <button class="btn btn-success">
            <spring:message code="save"/>
        </button>
        <spring:url value="/buyers" var="buyerUrl"/>
        <a href="${buyerUrl}" class="btn btn-danger">
            <spring:message code="buyer.list.back"/>
        </a>
    </div>
</div>
