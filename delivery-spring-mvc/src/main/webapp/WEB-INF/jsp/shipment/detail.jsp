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
        <form:form class="form-horizontal" method="post"  action="${pageContext.request.contextPath}/shipment/update/update/${shipmentForm.id}" modelAttribute="shipmentForm">

             <s:bind path="trackingId">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="control-label col-sm-2">Tracking ID</label>
                    <div class="col-sm-10">
                        <form:input class="form-control" path="trackingId" id="trackingId" type="text"
                                    placeholder="Tracking ID" readonly="true"/>
                        <form:errors path="trackingId" />
                    </div>
                </div>
             </s:bind>

            <%-- Shipment state --%>
            <s:bind path="shipmentState">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="control-label col-sm-2">State</label>
                    <div class="col-sm-10">
                        <form:input class="form-control" path="shipmentState" id="shipmentState" type="text"
                                    placeholder="Shipment state" readonly="true"/>
                        <form:errors path="shipmentState" />
                    </div>
                </div>
            </s:bind>

            <%-- Sender --%>
            <s:bind path="sender">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="control-label col-sm-2">Receiver</label>
                    <div class="col-sm-10">
                        <form:input class="form-control" path="sender.wholeName" id="sender" type="text" placeholder="Name"
                        readonly="true" />
                        <form:errors path="sender" />
                    </div>
                </div>
            </s:bind>

                <%-- Receiver dropdown --%>
            <s:bind path="receiver">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="control-label col-sm-2">Receiver</label>
                    <div class="col-sm-10">
                       <form:select class="form-control" path="receiver">
                           <c:forEach var="receiver" items="${receivers}">
                                <form:option value="${receiver}" label="${receiver.firstName} ${receiver.lastName}"/>
                           </c:forEach>
                       </form:select>
                       <form:errors path="receiver" />
                    </div>
                </div>
             </s:bind>

            <%-- price --%>
            <s:bind path="price">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="control-label col-sm-2">Price:</label>
                    <div class="col-sm-10">
                        <form:input class="form-control" path="price" id="price" type="text" placeholder="0.00$" />
                        <form:errors path="price" />
                    </div>
                </div>
            </s:bind>

            <%-- Distance --%>
            <s:bind path="distance">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="control-label col-sm-2">Distance:</label>
                    <div class="col-sm-10">
                        <form:input class="form-control" path="distance" id="distance" type="text" placeholder="0.0 km" />
                        <form:errors path="distance" />
                    </div>
                </div>
            </s:bind>

            <%-- Shipment created date --%>
            <s:bind path="shipmentCreated">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="control-label col-sm-2">Created</label>
                    <div class="col-sm-10">
                        <fmt:formatDate value="${shipmentForm.shipmentCreated}" var="createdDateFormated" pattern="yyyy/MM/dd HH:mm" type="both" />
                        <form:input class="form-control" path="shipmentCreated" value="${createdDateFormated}" id="shipmentCreated"
                                    type="text" placeholder="" readonly="true"/>
                        <form:errors path="shipmentCreated" />
                    </div>
                </div>
             </s:bind>

            <%-- Shipment delivered date --%>
            <c:choose>
               <c:when test="${shipmentForm.shipmentState == 'DELIVERED'}">

                    <s:bind path="shipmentDelivered">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="control-label col-sm-2">Delivered</label>
                            <div class="col-sm-10">
                                <fmt:formatDate value="${shipmentForm.shipmentDelivered}" var="deliveredDateFormated" pattern="yyyy/MM/dd HH:mm" type="both" />
                                <form:input class="form-control" path="shipmentDelivered" value="${deliveredDateFormated}" id="shipmentDelivered"
                                            type="text" placeholder="" readonly="true"/>
                                <form:errors path="shipmentDelivered" />
                            </div>
                        </div>
                    </s:bind>
                </c:when>
            </c:choose>

            <%-- List of products --%>
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
                <%--
                <table>
                    <tr>
                        <td>products</td>
                        <td> <form:checkboxes path="productsList" items="${products}" /></td>
                    </tr>
                </table>
                --%>
            </div>
        </form:form>
    </jsp:attribute>
</my:pagetemplate>
