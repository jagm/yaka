KanbanApp.Controllers = (function () {

    var errorHandling = function (data) {
        KanbanApp.Notify.error(data.errorMessage, "Error occurred");
    };

    var postRequest = function ($http, url, params, successCallback) {

        var FORM_HTTP_HEADER = { headers: {'Content-Type': 'application/x-www-form-urlencoded'} };

        $http.post(
                KanbanApp.Configuration.contextPath + url,
                $.param(params),
                FORM_HTTP_HEADER
            )
            .error(errorHandling)
            .success(successCallback);
    }

    return {

        BoardListCtrl: function ($scope, $rootScope, $http) {
            $http.get(KanbanApp.Configuration.contextPath + '/board/list')
                .error(errorHandling)
                .success(function (data) {
                    $rootScope.boards = data;
                });
        },

        BoardDetailCtrl: function ($http, $scope, $routeParams, $rootScope, board, issues) {
            $scope.boardId = $routeParams.boardId;

            $rootScope.boardId = board.id;
            $rootScope.boardName = board.name;

            $scope.board = board;
            $scope.issues = issues;

            $scope.changeState = function (issueId, stateId) {
                postRequest($http, '/issues/change-state', {issue_id: issueId, state_id: stateId}, function (data) {
                    if (data.errors && data.errors.length) {
                        KanbanApp.Notify.error(data.errors, 'error!!1')
                    } else {
                    }
                    console.log(data);
                });
            };

            $scope.changeOrder = function (issues) {
                var order = issues.map(function (issue) {
                    return parseInt(issue.replace('issue_', ''), 10);
                });
                postRequest($http, '/issues/change-order', {order: order}, function (data) {
                    if (data.errors && data.errors.length) {
                        KanbanApp.Notify.error(data.errors, 'error!!1')
                    } else {

                    }
                    console.log(data);
                });
            };

            $scope.delete = function (issueId) {
                postRequest($http, '/issues/delete', {issue_id: issueId}, function (data) {
                    if (data) {
                        $scope.issues = $scope.issues.filter(function (issue) {
                            return issue.id !== issueId;
                        })
                    }
                    console.log('[Issue delete][response]: ', data);
                });
            }

        },

        BoardDetailCtrlResolve: {
            board: function ($http, $q, $route) {
                var deferred = $q.defer();
                $http.get(KanbanApp.Configuration.contextPath + '/board/read/' + $route.current.params.boardId)
                    .error(errorHandling)
                    .success(function (data) {
                        deferred.resolve(data);
                    });
                return deferred.promise;

            },
            issues: function ($http, $q, $route) {
                var deferred = $q.defer();
                $http.get(KanbanApp.Configuration.contextPath + '/issues/list/' + $route.current.params.boardId)
                    .error(errorHandling)
                    .success(function (data) {
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
                postRequest($http, '/issues/add', issue, function (data) {
                    if (data.errors && data.errors.length) {
                        KanbanApp.Notify.error(data.errors, 'error!!1')
                    } else {

                    }
                    console.log(data);
                });
            };
        },

        AddStateCtrl: function ($scope, $http, $route) {
            $scope.state = {
                board: $scope.boardId
            };

            $scope.save = function (state) {
                postRequest($http, '/board/add-state', state, function (data) {
                    if (data.errors && data.errors.length) {

                        KanbanApp.Notify.error(data.errors, 'error!!1')
                    } else {
                        console.log('success!');
                        $route.reload();
                    }
                    console.log(data);
                });
            };
        },

        AddBoardCtrl: function ($scope, $http, $rootScope) {
            $scope.board = {};
            $scope.save = function (board) {
                postRequest($http, '/board/add', board, function (data) {
                    if (data.errors && data.errors.length) {
                        KanbanApp.Notify.error(data.errors.map(function (el) {
                            return el.field + ' ' + el.defaultMessage;
                        }), 'error!!!');
                    } else {
                        $('#add-board-modal').modal('hide');
                        $rootScope.boards = data.boards;
                    }
                    console.log(data);
                });
            };
        }

    };
})();
