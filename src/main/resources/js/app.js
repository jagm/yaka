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
        return KanbanApp.Directives.issuesColumn;
    })
    .directive('kanbanNotify', function () {
        return KanbanApp.Directives.notify;
    })
    .directive('kanbanResetOnHidden', function () {
        return KanbanApp.Directives.modalFormResetOnHidden;
    });


app.run(function ($http, $rootScope) {
    $rootScope.authenticated = KanbanApp.Configuration.authenticated;
    if (KanbanApp.Configuration.authenticated) {
        $http.get(KanbanApp.Configuration.contextPath + '/version/list').success(function (data) {
            KanbanApp.Configuration.versions = data;
            $rootScope.versions = data;
            $rootScope.colors = KanbanApp.Configuration.colors;
        });
    }

});
