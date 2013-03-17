KanbanApp.Controllers = {

    BoardListCtrl: function ($scope, $http) {
        $http.get('/kanban/board/list').success(function (data) {
            $scope.boards = data;
        });
    },

    BoardDetailCtrl: function ($scope, $http, $routeParams, $rootScope) {
        $scope.boardId = $routeParams.boardId;
        $http.get('/kanban/board/read/' + $scope.boardId).success(function (data) {
            $scope.board = data;
            $rootScope.boardName = data.name;
            $rootScope.boardId = data.id;
        });
        $http.get('/kanban/issues/list/' + $scope.boardId).success(function (data) {
            $scope.issues = data;
        });
    },

    AddIssueCtrl: function ($scope, $http) {

    }

}
