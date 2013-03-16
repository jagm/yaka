angular
    .module('kanban', [])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {templateUrl: 'templates/board-list.html', controller: BoardListCtrl})
            .when('/board/:boardId', {templateUrl: 'templates/board-detail.html', controller: BoardDetailCtrl})
            .otherwise({redirectTo: '/'});
    }])
    .filter('jodaDate', function () {
        return function (date) {
            return new Date(date[0], date[1] - 1, date[2], date[3], date[4], date[5]);
        }
    })
    .filter('friendlyDate', function () {
        return function (date) {
            --date[1]; // In JS: 0 - Jan, 11 - Dec
            return moment(date).fromNow();
        }
    });