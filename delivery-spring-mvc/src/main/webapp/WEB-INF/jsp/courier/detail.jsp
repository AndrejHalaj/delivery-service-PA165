<%-- 
    Document   : detail
    Created on : Dec 16, 2016, 5:15:49 PM
    Author     : Kristian Mateka
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<my:pagetemplate title="Courier detail">
    <jsp:attribute name="body">
        <h2>
            <c:out value="${courierDetail.firstName}"/>
            <c:out value="${courierDetail.lastName}"/>
        </h2>

        <table class="no-lines-table" padding="10px">
            <tr>
                <td><strong>Id:</strong></td>
                <td><c:out value="${courierDetail.id}"/></td>
            </tr>
            <tr>
                <td><strong>Email:</strong></td>
                <td><a href="mailto:<c:out value='${courierDetail.email}'/>"><c:out value="${courierDetail.email}"/></a></td>
            </tr>

            <tr>
                <td>
                    <h2>List of shipments</h2>
                </td>
            </tr>

            <table class="table">
                <thead>
                    <tr>
                        <th width="40">Id</th>
                        <th width="200">Sender</th>
                        <th width="200">Receiver</th>
                        <th width="200">State</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${courierDetail.shipmentsList}" var="shipment">
                        <tr>
                            <td><c:out value="${shipment.id}"/></td>
                            <td><c:out value="${shipment.sender.firstName} ${shipment.sender.lastName}"/></td>
                            <td><c:out value="${shipment.receiver.firstName} ${shipment.receiver.lastName}"/></td>
                            <td><c:out value="${shipment.shipmentState}"/></td>

                            <td>
                                <form method="post" action="${pageContext.request.contextPath}/shipment/detail/${shipment.id}">
                                    <button class="btn btn-success" type="submit">Detail</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </table>
        <br/>
    </jsp:attribute>
</my:pagetemplate>
