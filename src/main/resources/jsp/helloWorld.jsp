<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="en" ng-app="kanban">

<head>
    <meta charset="utf-8">
    <title>Kanban</title>

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" media="screen">
</head>

<body>

<div class="navbar navbar-static-top navbar-inverse">
    <div class="navbar-inner">
        <div class="brand">
            <a href="${pageContext.request.contextPath}">Kanban</a>
            <span ng-show="boardName"> /
                <a href="#/board/{{boardId}}">{{ boardName }}</a>
            </span>
        </div>
        <ul class="nav pull-right">
            <li class="active"><a href="#">Link</a></li>
            <li><a href="#">Link</a></li>
            <li><a href="#">Link</a></li>
        </ul>
    </div>
</div>

<div class="container-fluid">

    <div ng-view></div>
</div>

<script>
    var KanbanApp = {
        Configuration: {}
    };
</script>

<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.0.4/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/js/filters.js"></script>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
<script src="${pageContext.request.contextPath}/js/controllers.js"></script>
</body>

</html>