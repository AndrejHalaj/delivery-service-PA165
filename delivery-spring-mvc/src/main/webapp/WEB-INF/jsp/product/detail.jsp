<%--
    Author : Viktor Bako
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fomr" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Product detail">
    <jsp:attribute name="body">

    	<form:form class="form-horizontal" role="form" method="post" modelAttribute="product"  action="${pageContext.request.contextPath}/product/detail/${product.id}/update">
            <form:hidden path="id" />
            <form:hidden path="shipment" />

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

            <c:if test="${loggedUser.isCourier==false}">
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-success">Update</button>
                </div>
            </div>
            </c:if>
            <c:if test="${loggedUser.isCourier==true}">
                    <form:button type="submit" formmethod="get" formaction="${pageContext.request.contextPath}/products/list" class="btn btn-primary">Back</form:button>
            </c:if>
        </form:form>
    	
    </jsp:attribute>
</my:pagetemplate>