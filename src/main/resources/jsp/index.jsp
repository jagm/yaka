<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en" ng-app="kanban">

<head>
    <meta charset="utf-8">
    <title>Kanban</title>

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/ui.notify.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" media="screen">
</head>

<body>

<div class="navbar navbar-static-top navbar-inverse">
    <div class="navbar-inner">
        <div class="brand">
            <a href="${pageContext.request.contextPath}">YAKA</a>
            <span ng-show="boardName"> /
                <a href="#/board/{{boardId}}">{{ boardName }}</a>
            </span>
        </div>
        <ul class="nav pull-right">
            <%--<li class="active"><a href="#">Link</a></li>
            <li><a href="#">Link</a></li>--%>
            <security:authorize access="isAuthenticated()">
                <li><a href="${pageContext.request.contextPath}/j_spring_security_logout">Logout</a></li>
            </security:authorize>
        </ul>
    </div>
</div>

<div class="container-fluid">

    <security:authorize access="isAuthenticated()">
        <ng-include src="'templates/board-list.html'"></ng-include>
    </security:authorize>

    <div class="app-view" ng-view>
    </div>
</div>

<ng-include src="'templates/notify.html'"></ng-include>

<script>
    var KanbanApp = {
        Configuration: {
            authenticated: <security:authorize access="isAnonymous()">!</security:authorize>true
        }
    };
</script>

<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.0.4/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/js/slimScrollHorizontal.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.notify.js"></script>
<script src="${pageContext.request.contextPath}/js/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/js/filters.js"></script>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
<script src="${pageContext.request.contextPath}/js/controllers.js"></script>
<script src="${pageContext.request.contextPath}/js/directives.js"></script>
</body>

</html>