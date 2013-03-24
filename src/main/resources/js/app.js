var app = angular
    .module('kanban', [])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {templateUrl: 'templates/welcome.html'})
            .when('/board/:boardId', {templateUrl: 'templates/board-detail.html', controller: KanbanApp.Controllers.BoardDetailCtrl, resolve: KanbanApp.Controllers.BoardDetailCtrlResolve})
            .otherwise({redirectTo: '/'});
    }])
    .filter('jodaDate', function () {
        return KanbanApp.Filters.jodaDate;
    })
    .filter('friendlyDate', function () {
        return KanbanApp.Filters.friendlyDate;
    })
    .directive('kanbanIssues', function () {
        return KanbanApp.Directives.issuesColumn
    });

app.run(function ($http, $rootScope) {
    $http.get('/kanban/version/list').success(function (data) {
        KanbanApp.Configuration.versions = data;
        $rootScope.versions = data;
    });
});


KanbanApp.Notify = (function () {

    $(function () {
        console.log($("#notify-container"));
        $("#notify-container").notify();
    });

    return {
        error: function (message, title) {
            $("#notify-container")
                .notify("create", "error-template", { title: title, text: message });
        }
    }

})();
