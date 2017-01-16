<%--
    Author : Viktor Bako
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<my:pagetemplate title="Products">
<jsp:attribute name="body">

  <div class="event_container animated fadeIn">

    <c:if test="${loggedUser.isCourier==false}">
  	<a href="${pageContext.request.contextPath}/product/new" class="btn btn-primary" style="float:right">
  		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Add product
    </a>
    </c:if>

    <table class="table">
        <thead>
        <tr>
            <th width="40">Id</th>
            <th>Name</th>
            <th>Producer</th>
            <th>Weight</th>
            <th width="60"></th>
            <th width="80"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${products}" var="product">
            <tr>
                <td><c:out value="${product.id}"/></td>
                <td><a href="${pageContext.request.contextPath}/product/detail/${product.id}"><c:out value="${product.name}"/></a></td>
                <td><c:out value="${product.producer}"/></td>
                <td><c:out value="${product.weight}"/> kg</td>
                <c:if test="${loggedUser.isCourier==false}">
                <td>
                    <a href="${pageContext.request.contextPath}/product/detail/${product.id}" class="btn btn-success">Edit</a>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/product/delete/${product.id}">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
  </div>
</jsp:attribute>
</my:pagetemplate>