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
                            <td>Sender: </td>
                            <td>${shipment.sender.firstName} ${shipment.sender.lastName}</td>
                        </tr>
                        <tr>
                            <td>Status: </td>
                            <td></td>
                        </tr>
                    </table>
                </div>

            </div>
            </c:forEach>
        </div>

    </jsp:attribute>
</my:pagetemplate>
