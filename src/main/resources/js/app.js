angular
    .module('kanban', [])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {templateUrl: 'templates/board-list.html', controller: KanbanApp.Controllers.BoardListCtrl})
            .when('/board/:boardId', {templateUrl: 'templates/board-detail.html', controller: KanbanApp.Controllers.BoardDetailCtrl})
            .otherwise({redirectTo: '/'});
    }])
    .filter('jodaDate', function () {
        return KanbanApp.Filters.jodaDate;
    })
    .filter('friendlyDate', function () {
        return KanbanApp.Filters.friendlyDate;
    });