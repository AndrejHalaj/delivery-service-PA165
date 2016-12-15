<%--
    Author : Andrej Ha;aj
--%>

<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="backgroundimg" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><c:out value="${title}"/></title>
        <!-- bootstrap loaded from content delivery network -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"  crossorigin="anonymous">
        <link href="${pageContext.request.contextPath}/resources/animate.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/style.css" rel="stylesheet">
        <jsp:invoke fragment="head"/>
    </head>
    <body background="<c:out value='${backgroundimg}'/>">
        <!-- navigation bar -->
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}">Delivery Manager</a>
                </div>
                <div>
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/shipment/list">Shipments</a></li>
                        <li><a href="${pageContext.request.contextPath}/customer/list">All customers</a></li>
                        <li><a href="${pageContext.request.contextPath}/courier/list">Couriers</a></li>
                    </ul>
                    <c:if test="${empty authenticatedUser}">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="${pageContext.request.contextPath}/customer/new"><span class="glyphicon glyphicon-user"></span>Customer Sign Up</a></li>
                            <li><a href="${pageContext.request.contextPath}/customer/login_page"><span class="glyphicon glyphicon-log-in"></span>Customer Login</a></li>
                        </ul>
                    </c:if>
                    <c:if test="${not empty authenticatedUser}">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="${pageContext.request.contextPath}/customer/detail/${authenticatedUser.id}"><span class="glyphicon glyphicon-user"></span>"${authenticatedUser.emailAddress}"</a></li>
                            <li><a href="${pageContext.request.contextPath}/customer/logout"><span class="glyphicon glyphicon-log-in"></span>Logout</a></li>
                        </ul>
                    </c:if>

                </div>
            </div>
        </nav>

        <div class="container">
            <!-- page title -->
            <c:if test="${not empty title and title != 'Sporting Events Manager'}">
                <div class="page-header">
                    <c:if test="${title != 'Events' and title != 'Competition results' and title != 'Competition'
                        and title != 'All users'}">
                            <h1><c:out value="${title}"/></h1>
                    </c:if>
                    <c:if test="${title == 'Events' or title == 'Competition results' or title == 'Competition'
                        or title == 'All users'}">
                            <h1 style=" color: white;"><c:out value="${title}"/></h1>
                    </c:if>
                </div>
            </c:if>

            <!-- alerts -->
            <c:if test="${not empty alert_danger}">
                <div class="alert alert-danger" role="alert">
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    <c:out value="${alert_danger}"/>
                </div>
            </c:if>
            <c:if test="${not empty alert_info}">
                <div class="alert alert-info" role="alert"><c:out value="${alert_info}"/></div>
            </c:if>
            <c:if test="${not empty alert_success}">
                <div class="alert alert-success fadeOut animated" id="alest-success" style="animation-delay: 3s; animation-duration: 1s;" role="alert">
                    <c:out value="${alert_success}"/>
                </div>
                <script type="text/javascript">
                    setTimeout(function() { document.getElementById("alest-success").style.display = "none" }, 4000);
                </script>
            </c:if>
            <c:if test="${not empty alert_warning}">
                <div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
            </c:if>

            <jsp:invoke fragment="body"/>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/jquery.datetimepicker.css" />
        <script src="${pageContext.request.contextPath}/resources/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/resources/jquery.datetimepicker.full.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/functions.js"></script>

        <script>
            jQuery("#startTime").datetimepicker();
            jQuery("#endTime").datetimepicker();
        </script>
    </body>
</html>