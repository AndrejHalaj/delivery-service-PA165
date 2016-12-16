<%--
  Created by IntelliJ IDEA.
  User: lukas.gryc
  Date: 15.12.2016
  Time: 10:21
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

                <h1 class="shipment_tracking_id">${shipmentForm.trackingId} </h1>
                <div class="shipment_base_info">
                    <table>
                        <%-- courier name --%>
                        <tr>
                            <td>Courier: </td>
                            <td>${shipmentForm.courier.firstName} ${shipmentForm.courier.lastName}</td>
                        </tr>
                        <%-- sender name --%>
                        <tr>
                            <td>Sender: </td>
                            <td>${shipmentForm.sender.firstName} ${shipmentForm.sender.lastName}</td>
                        </tr>
                            <%-- receiver name --%>
                        <tr>
                            <td>Receiver: </td>
                            <td>${shipmentForm.receiver.firstName} ${shipmentForm.receiver.lastName}</td>
                        </tr>
                        <%-- house number & city --%>
                        <tr>
                            <td>Address</td>
                            <td>${shipmentForm.receiver.houseNumber} ${shipmentForm.receiver.city}</td>
                        </tr>
                        <%-- postal code & country --%>
                        <tr>
                            <td></td>
                            <td>${shipmentForm.receiver.postalCode} ${shipmentForm.receiver.country}</td>
                        </tr>
                        <%-- shipment status --%>
                        <tr>
                            <td>Status: </td>
                            <td>${shipmentForm.shipmentState}</td>
                        </tr>
                        <%-- price --%>
                        <tr>
                            <td>Price: </td>
                            <td>${shipmentForm.price}</td>
                        </tr>
                        <%-- distance --%>
                        <tr>
                            <td>Distance: </td>
                            <td>${shipmentForm.distance}</td>
                        </tr>
                        <%-- shipment created date --%>
                        <tr>
                            <fmt:formatDate value="${shipmentForm.shipmentCreated}" var="createDateFormated" pattern="yyyy/MM/dd HH:mm" type="both" />
                            <td>Created: </td>
                            <td>${createDateFormated}</td>
                        </tr>
                        <%-- delivery date only visible when state is DELIVERED --%>
                        <c:choose>
                            <c:when test="${shipmentForm.shipmentState == 'DELIVERED'}">
                                <tr>
                                    <fmt:formatDate value="${shipmentForm.shipmentDelivered}" var="deliveryDateFormated" pattern="yyyy/MM/dd HH:mm" type="both" />
                                    <td>Delivered: </td>
                                    <td>${deliveryDateFormated}</td>
                                </tr>
                            </c:when>
                        </c:choose>

                    </table>
                </div>
                <div class="products_container">
                    <h2>Producs:</h2>
                    <c:forEach var="item" items="${shipmentForm.productsList}">
                        <div class="product_row">
                            <table>
                                <tr>
                                    <td>Name:</td>
                                    <td>${item.name}</td>
                                    <td>${item.weight}</td>
                                </tr>

                            </table>
                        <div>
                    </c:forEach>
                </div>

    </jsp:attribute>
</my:pagetemplate>
