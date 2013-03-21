KanbanApp.Controllers = {

    BoardListCtrl: function ($scope, $http) {
        $http.get('/kanban/board/list').success(function (data) {
            $scope.boards = data;
        });
    },

    BoardDetailCtrl: function ($http, $scope, $routeParams, $rootScope, board, issues) {
        $scope.boardId = $routeParams.boardId;

        $rootScope.boardId = board.id;
        $rootScope.boardName = board.name;

        $scope.board = board;
        $scope.issues = issues;

        $scope.changeState = function (issueId, stateId) {
            $http.post(
                '/kanban/issues/change-state',
                $.param({issue_id: issueId, state_id: stateId}),
                {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}
            ).success(function (data) {
                    if (data.errors && data.errors.length) {
                        KanbanApp.Notify.error(data.errors, 'error!!1')
                    } else {

                    }
                    console.log(data);
                });
        };

    },

    BoardDetailCtrlResolve: {
        board: function ($http, $q, $route) {
            var deferred = $q.defer();
            $http.get('/kanban/board/read/' + $route.current.params.boardId).success(function (data) {
                deferred.resolve(data);
            });
            return deferred.promise;

        },
        issues: function ($http, $q, $route) {
            var deferred = $q.defer();
            $http.get('/kanban/issues/list/' + $route.current.params.boardId).success(function (data) {
                deferred.resolve(data);
            });
            return deferred.promise;
        }
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
