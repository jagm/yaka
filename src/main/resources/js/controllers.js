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

        /*$scope.$on('$', function() {
         $('#inner-content-div').slimScroll({
         height: '250px'
         });
         });*/
    },

    AddIssueCtrl: function ($scope, $http) {
        $scope.issue = {
            board: {id: $scope.boardId }
        }

        $scope.save = function (issue) {
            issue.state_id = $scope.board.states[0].id;
            console.log(issue);
            $http.post(
                '/kanban/issues/add',
                $.param(issue),
                {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}
            ).success(function (data) {
                    if (data.errors && data.errors.length) {

                        KanbanApp.Notify.error(data.errors, 'error!!1')
                    } else {

                    }
                    console.log(data);
                });
        };
    }

}
