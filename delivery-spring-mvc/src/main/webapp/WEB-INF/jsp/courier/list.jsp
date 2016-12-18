<%-- 
    Document   : list
    Created on : Dec 14, 2016, 7:50:38 PM
    Author     : Kristian Mateka
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<my:pagetemplate title="Couriers list">
<jsp:attribute name="body">

  <div class="event_container animated fadeIn">
    <table class="table">
        <thead>
        <tr>
            <th width="40">Id</th>
            <th width="200">First name</th>
            <th width="200">Last name</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${couriers}" var="courier">
            <tr>
                <td><c:out value="${courier.id}"/></td>
                <td><a href="${pageContext.request.contextPath}/courier/detail/${courier.id}"><c:out value="${courier.firstName}"/></a></td>
                <td><c:out value="${courier.lastName}"/></td>
                <td><c:out value="${courier.email}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
  </div>
</jsp:attribute>
</my:pagetemplate>