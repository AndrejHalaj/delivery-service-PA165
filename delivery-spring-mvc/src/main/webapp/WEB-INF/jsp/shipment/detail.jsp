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
        <form:form class="form-horizontal" modelAttribute="shipmentForm">

            <form:hidden path="id" />

             <s:bind path="trackingId">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="control-label col-sm-2">Tracking ID:</label>
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
                    <label class="control-label col-sm-2">State:</label>
                    <div class="col-sm-10">
                        <form:input class="form-control" path="shipmentState" id="shipmentState" type="text"
                                    placeholder="Shipment state" readonly="true"/>
                        <form:errors path="shipmentState" />
                    </div>
                </div>
            </s:bind>

            <%-- Courier dropdown --%>
            <s:bind path="courier">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="control-label col-sm-2">Courier:</label>
                    <div class="col-sm-10">
                        <c:choose>
                            <%-- select box while in edit mode --%>
                            <c:when test="${detailOnly=='false'}">
                               <form:select class="form-control" path="courier">
                                   <c:forEach var="courier" items="${couriers}">
                                       <c:choose>
                                           <c:when test="${courier.id == shipmentForm.courier.id}">
                                               <form:option value="${courier}" label="${courier.firstName} ${courier.lastName}"
                                                            selected="selected" />
                                           </c:when>
                                           <c:otherwise>
                                               <form:option value="${courier}" label="${courier.firstName} ${courier.lastName}" />
                                           </c:otherwise>
                                       </c:choose>
                                   </c:forEach>
                               </form:select>
                            </c:when>
                            <%-- just a line in display mode --%>
                            <c:otherwise>
                                <form:input class="form-control" path="courier.wholeName" id="courier" type="text"
                                            placeholder="courier" readonly="true" />
                            </c:otherwise>

                        </c:choose>
                        <form:errors path="courier" />
                    </div>
                </div>
            </s:bind>

            <%-- Sender --%>
            <form:hidden path="sender"/>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="control-label col-sm-2">Sender:</label>
                <div class="col-sm-10">
                    <form:input class="form-control" path="sender.wholeName" id="sender" type="text" placeholder="Name"
                    readonly="true" />
                    <form:errors path="sender" />
                </div>
            </div>

            <%-- Receiver dropdown --%>
            <s:bind path="receiver">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="control-label col-sm-2">Receiver:</label>
                    <div class="col-sm-10">
                       <c:choose>
                            <%-- dropdown in edit mode --%>
                            <c:when test="${detailOnly=='false'}">
                                <form:select class="form-control" path="receiver">
                                   <c:forEach var="receiver" items="${receivers}">
                                       <c:choose>
                                           <c:when test="${receiver.id == shipmentForm.receiver.id}">
                                               <form:option value="${receiver}" label="${receiver.firstName} ${receiver.lastName}"
                                                            selected="selected" />
                                           </c:when>
                                           <c:otherwise>
                                               <form:option value="${receiver}" label="${receiver.firstName} ${receiver.lastName}" />
                                           </c:otherwise>
                                       </c:choose>
                                   </c:forEach>
                                </form:select>
                            </c:when>
                            <%-- otherwise just a simple line --%>
                            <c:otherwise>
                                <form:input class="form-control" path="receiver.wholeName" id="receiver" type="text" placeholder="Name"
                                            readonly="true"/>
                            </c:otherwise>
                        </c:choose>
                        <form:errors path="receiver" />
                    </div>
                </div>
            </s:bind>

            <%-- price --%>
            <s:bind path="price">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="control-label col-sm-2">Price:</label>
                    <div class="col-sm-10">
                        <form:input class="form-control" path="price" id="price" type="text" placeholder="0.00$"
                                    readOnly="${detailOnly=='true' ? 'true' : 'false'}"/>
                        <form:errors path="price" />
                    </div>
                </div>
            </s:bind>

            <%-- Distance --%>
            <s:bind path="distance">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="control-label col-sm-2">Distance:</label>
                    <div class="col-sm-10">
                        <form:input class="form-control" path="distance" id="distance" type="text" placeholder="0.0 km"
                                    readOnly="${detailOnly=='true' ? 'true' : 'false'}"/>
                        <form:errors path="distance" />
                    </div>
                </div>
            </s:bind>

            <%-- Shipment created date --%>
            <s:bind path="shipmentCreated">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="control-label col-sm-2">Created:</label>
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

            <%-- Products list with checkboxes --%>
            <h3>Products</h3>
            <c:forEach var="product" items="${products}">
                <form:checkbox path="productsList" class="form-control" value="${product}" label="${product.name}"
                    checked="${shipmentForm.id==product.shipmentId ? 'checked' : ''}"/><br>
            </c:forEach>

            <div class="ship_detail_ctrl_btns">
                <%-- Update button only visible on edit view --%>
                <c:if test="${detailOnly=='false'}">
                    <form:button type="submit" formethod="post" formaction="${pageContext.request.contextPath}/shipment/update/${shipmentForm.id}" class="btn btn-success">Update</form:button>
                </c:if>
                <%-- Back button --%>
                <form:button type="submit" formmethod="get" formaction="${pageContext.request.contextPath}/shipment/list" class="btn btn-primary">Back</form:button>
            </div>
        </form:form>
    </jsp:attribute>
</my:pagetemplate>
