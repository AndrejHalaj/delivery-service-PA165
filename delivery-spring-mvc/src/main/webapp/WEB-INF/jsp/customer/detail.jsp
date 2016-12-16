<%--
    Author : Andrej Ha;aj
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<my:pagetemplate title="User profile">
    <jsp:attribute name="body">
        <h2>
            <c:out value="${customerDetail.firstName}"/>
            <c:out value="${customerDetail.lastName}"/>
        </h2>

        <table class="no-lines-table" padding="10px">
            <tr>
                <td><strong>Id:</strong></td>
                <td><c:out value="${customerDetail.id}"/></td>
            </tr>
            <tr>
                <td><strong>Address:</strong></td>
                <td><c:out value="${customerDetail.houseNumber}, ${customerDetail.postalCode} ${customerDetail.city}, ${customerDetail.country}"/></td>
            </tr>
            <tr>
                <td><strong>Email:</strong></td>
                <td><a href="mailto:<c:out value='${customerDetail.emailAddress}'/>"><c:out value="${customerDetail.emailAddress}"/></a></td>
            </tr>
            <tr>
                <td><strong>Phone number:</strong></td>
                <td><c:out value="${customerDetail.phoneNumber}"/></td>
            </tr>
        </table>
        <br/>
        <c:if test="${customerDetail.id == authenticatedUser.id}">
            <a href="${pageContext.request.contextPath}/user/update/${customerDetail.id}"><button type="button" class="btn btn-primary btn-md">Edit details</button></a>
        </c:if>
    </jsp:attribute>
</my:pagetemplate>