<%--
    Author : Viktor Bako
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Add product">
    <jsp:attribute name="body">
        <form:form class="form-horizontal" role="form" method="post" modelAttribute="product"  action="${pageContext.request.contextPath}/product/create">
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Name:</label>
                <div class="col-sm-10">
                    <form:input class="form-control" path="name" id="name" required="required" />
                    <form:errors path="name" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Producer:</label>
                <div class="col-sm-10">
                    <form:input class="form-control" path="producer" id="producer" required="required" />
                    <form:errors path="producer" cssClass="help-block"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Weight (kg):</label>
                <div class="col-sm-10">
                    <form:input size="8" class="form-control" path="weight" id="weight" required="required" pattern="[0-9]+(\.[0-9]+)?" />
                    <form:errors path="weight" cssClass="help-block"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Description:</label>
                <div class="col-sm-10">
                    <form:textarea class="form-control" name="description" id="description" path="description" />
                    <form:errors path="description" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Create</button>
                </div>
            </div>
        </form:form>
    </jsp:attribute>
</my:pagetemplate>