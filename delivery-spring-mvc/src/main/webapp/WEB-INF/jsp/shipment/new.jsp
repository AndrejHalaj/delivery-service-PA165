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
        <form:form class="form-horizontal" method="post"  action="${pageContext.request.contextPath}/shipment/create" modelAttribute="shipmentForm">

             <div class="form-group ${status.error ? 'has-error' : ''}">

                <label class="control-label col-sm-2">Receiver:</label>
                <div class="col-sm-10">
                    <form:select class="form-control" path="customerReceiverId">
                        <c:forEach var="customer" items="${customerList}">
                            <form:option value="${customer.id}" label="${customer.firstName} ${customer.lastName}" />
                        </c:forEach>
                    </form:select>
               </div>
             </div>

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

            <c:forEach var="item" items="${shipmentForm.productsList}">
                <div class="product_row">
                    <table>
                        <tr>
                            <td>Name:</td>
                            <td>${item.name}</td>
                            <td></td>
                        </tr>

                    </table>
                <div>
            </c:forEach>


            <%-- Create button --%>
            <button class="btn btn-default" type="submit">Create</button>
        </form:form>
    </jsp:attribute>
</my:pagetemplate>