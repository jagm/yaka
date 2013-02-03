<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Kanban</title>

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>

<body>

<div class="navbar navbar-static-top navbar-inverse">
    <div class="navbar-inner">
        <a class="brand" href="${pageContext.request.contextPath}">Kanban</a>
        <ul class="nav pull-right">
            <li class="active"><a href="#">Link</a></li>
            <li><a href="#">Link</a></li>
            <li><a href="#">Link</a></li>
        </ul>
    </div>
</div>

<div class="container-fluid">

    <h1>${message}</h1>

</div>


<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>

</html>