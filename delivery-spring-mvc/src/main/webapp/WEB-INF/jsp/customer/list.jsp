<%--
    Author : Andrej Ha;aj
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<my:pagetemplate title="All users">
<jsp:attribute name="body">

  <div class="event_container animated fadeIn">
    <table class="table">
        <thead>
        <tr>
            <th width="40">Id</th>
            <th width="200">First name</th>
            <th width="200">Last name</th>
            <th>Email</th>
            <th width="250">City</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${customers}" var="customer">
            <tr>
                <td><c:out value="${customer.id}"/></td>
                <td><a href="${pageContext.request.contextPath}/customer/detail/${customer.id}"><c:out value="${customer.firstName}"/></a></td>
                <td><c:out value="${customer.lastName}"/></td>
                <td><c:out value="${customer.emailAddress}"/></td>
                <td><c:out value="${customer.city}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
  </div>
</jsp:attribute>
</my:pagetemplate>