<%--
  Created by IntelliJ IDEA.
  User: Jamik
  Date: 14.12.2016
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Shipments">
    <jsp:attribute name="body">

            <%-- Add buttonm, ... --%>
            <div class="shipment_control">
                <form method="get" action="${pageContext.request.contextPath}/shipment/new">
                    <button class="btn btn-primary" type="submit">Create</button>
                </form>
            </div>

            <%-- Shipments list. --%>
            <div class="shipments_container">
                <c:forEach var="shipment" items="${shipments}">
                <div class="shipment_item">
                    <h1 class="shipment_tracking_id">${shipment.trackingId} </h1>
                    <div class="shipment_base_info">
                        <table>
                            <tr>
                                <td>Courier: </td>
                                <td>${shipment.courier.firstName} ${shipment.courier.lastName}</td>
                            </tr>
                            <tr>
                                <td>Sender: </td>
                                <td>${shipment.sender.firstName} ${shipment.sender.lastName}</td>
                            </tr>
                            <tr>
                                <td>Status: </td>
                                <td>${shipment.shipmentState}</td>
                            </tr>
                        </table>
                    </div>

                    <%-- Control buttons for each shipment --%>
                    <div class="shipment_control_buttons">
                        <c:choose>
                            <%-- Deliver button only shows for TRANSFERED shipments --%>
                            <c:when test="${shipment.shipmentState == 'TRANSFERED'}">
                                <form method="post" action="${pageContext.request.contextPath}/shipment/deliver/${shipment.id}">
                                    <button class="btn btn-default" type="submit">Deliver</button>
                                </form>
                            </c:when>

                            <%-- Cancel button only shows for NEW shipmentst --%>
                            <c:when test="${shipment.shipmentState == 'NEW'}">
                                <form method="post" action="${pageContext.request.contextPath}/shipment/cancel/${shipment.id}">
                                    <button class="btn btn-default" type="submit">Cancel</button>
                                </form>
                            </c:when>
                        </c:choose>

                        <%-- Detail button --%>
                        <form method="post" action="${pageContext.request.contextPath}/shipment/detail/${shipment.id}">
                            <button class="btn btn-default" type="submit">Edit</button>
                        </form>
                    </div>
                </div>
                </c:forEach>
            </div>

    </jsp:attribute>
</my:pagetemplate>
