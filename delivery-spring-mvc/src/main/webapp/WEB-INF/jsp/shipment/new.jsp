<%--
  Created by IntelliJ IDEA.
  User: lukas.gryc
  Date: 15.12.2016
  Time: 14:31
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
        <form:form method="post" action="${pageContext.request.contextPath}/new" modelAttribute="shipmentForm">
            <%-- id has to be stored, otherwise it would get lost

            <form:hidden path="id"/>
 --%>

            <%-- Tracking ID
            <s:bind path="trackingId">
                <label>Tracking ID:</label>
                <div>
                    <form:input path="trackingId" id="trackingId" type="text" placeholder="tracking ID" />
                    <form:errors path="trackingId" />
                </div>
            </s:bind>
            --%>

            <%-- Courier
            <s:bind path="courier">
                <label>Courier:</label>
                <div>
                    <form:input path="courier" id="courier" type="text" placeholder="name" />
                    <form:errors path="courier" />
                </div>
             </s:bind>
             --%>

            <%-- Sender
            <s:bind path="sender">
                <label>Sender:</label>
                <div>
                    <form:input path="sender" id="sender" type="text" placeholder="name" />
                    <form:errors path="sender" />
                </div>
            </s:bind>
--%>
            <%-- Receiver
            <s:bind path="receiver">
                <label>Receiver:</label>
                <div>
                    <form:input path="receiver" id="receiver" type="text" placeholder="name" />
                    <form:errors path="receiver" />
                </div>
            </s:bind>
--%>
            <%-- price --%>
            <s:bind path="price">
                <label>Price:</label>
                <div>
                    <form:input path="price" id="price" type="text" placeholder="0.00$" />
                    <form:errors path="price" />
                </div>
            </s:bind>

            <%-- Distance --%>
            <s:bind path="distance">
                <label>Distance:</label>
                <div>
                    <form:input path="distance" id="distance" type="text" placeholder="0.0 km" />
                    <form:errors path="distance" />
                </div>
            </s:bind>

            <%-- Shipment state
            <s:bind path="shipmentState">
                <label>Shipment state:</label>
                <div>
                    <form:input path="shipmentState" id="shipmentState" type="text" placeholder="state" />
                    <form:errors path="shipmentState" />
                </div>
            </s:bind>
 --%>
            <%-- !!! TODO: create/deliver time, products  !!! --%>

            <%-- Create button --%>
            <button type="submit">Create</button>
        </form:form>
    </jsp:attribute>
</my:pagetemplate>