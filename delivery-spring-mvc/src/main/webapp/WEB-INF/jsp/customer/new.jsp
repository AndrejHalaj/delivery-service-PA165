<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Registration">
    <jsp:attribute name="body">

        <form:form class="form-horizontal" role="form" method="post" modelAttribute="customerCreate"  action="${pageContext.request.contextPath}/customer/create">
            <div class="form-group">
                <label class="control-label col-sm-2" for="emailInput">Email:</label>
                <div class="col-sm-10">
                    <form:input type="email" class="form-control" path="emailAddress" id="emailInput" placeholder="Email..." />
                     <form:errors path="emailAddress" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="password">Password:</label>
                <div class="col-sm-10">
                    <form:input type="password" path="password" class="form-control" id="password" placeholder="Enter password" />
                     <form:errors path="password" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="firstNameInput">First name:</label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" path="firstName" id="firstNameInput" placeholder="First name..." />
                     <form:errors path="firstName" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="lastNameInput">Last name:</label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control"  path="lastName" id="lastNameInput" placeholder="Last name..." />
                     <form:errors path="lastName" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="houseNumberInput">House number:</label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control"  path="houseNumber" id="houseNumberInput" placeholder="House number..." />
                     <form:errors path="houseNumber" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="postalCodeInput">Postal code:</label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control"  path="postalCode" id="postalCodeInput" placeholder="Postal code..." />
                     <form:errors path="postalCode" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="cityInput">City:</label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control"  path="city" id="cityInput" placeholder="City..." />
                     <form:errors path="city" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="countryInput">Country:</label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control"  path="country" id="countryInput" placeholder="Country..." />
                     <form:errors path="country" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="phoneNumberInput">Phone number:</label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control"  path="phoneNumber" id="phoneNumberInput" placeholder="Phone number..." />
                     <form:errors path="phoneNumber" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Sign up</button>
                </div>
            </div>
        </form:form>
    </jsp:attribute>
</my:pagetemplate>