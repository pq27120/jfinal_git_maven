'use strict';

dormitory.controller('ListCtrl', ['$scope', '$filter', 'dormitories',
    function ($scope, $filter, dormitories) {
        $scope.loadList = function () {
            dormitories.all().then(function (dormitories) {
                $scope.dormitories = dormitories;
            });
        };

        //init load data
        $scope.loadList();

        $scope.deleteDormitory = function ($index, id) {
            if (confirm("确定删除？")) {
                dormitories.delete(id);
                $scope.dormitories.splice($index, 1);
            }
        };

        //设置分页
        //初始化分页参数
        $scope.itemsPerPage = 10;
        $scope.currentPage = 0;

        $scope.prevPage = function () {
            if ($scope.currentPage > 0) {
                $scope.currentPage--;
            }
        };

        $scope.prevPageDisabled = function () {
            return $scope.currentPage == 0;
        };

        //书名搜索关键词，主要用于更新books数组
        $scope.dormitoryFilterdInput = '';

        $scope.pageCount = function () {
            if ($scope.dormitories) {
                //根据用户输入来过滤更新数组，主要用来更新页数
                $scope.updatePage = $filter('name')($scope.dormitories, $scope.dormitoryFilterdInput);
                //向上取整求出总页数
                return Math.ceil($scope.updatePage.length / $scope.itemsPerPage);
            } else {
                return false;
            }
        };

        $scope.nextPage = function () {
            if ($scope.currentPage < $scope.pageCount()) {
                $scope.currentPage++;
            }
        };

        $scope.nextPageDisabled = function () {
            return $scope.currentPage + 1 == $scope.pageCount();
        };

        $scope.$watch('dormitoryFilterdInput', function () {
            //console.log('change');
            if ($scope.pageCount()<=$scope.currentPage) {
                $scope.currentPage = 0;
            }
        })

        $scope.$watch('itemsPerPage', function () {
            //console.log('change');
            if ($scope.pageCount()<=$scope.currentPage) {
                $scope.currentPage = 0;
            }
        })
    }
]);

dormitory.controller('ViewCtrl', ['$scope', '$routeParams', 'dormitories',
    function ($scope, $routeParams, dormitories) {
        //用指令代替了这块功能，该controller和directiveCtrl完全相同
        //books.get($routeParams.id).then(function (dormitory) {
        //    $scope.dormitory = dormitory;
        //});
    }
]);

dormitory.controller('EditCtrl', ['$scope', '$routeParams', '$location', 'dormitories',
    function ($scope, $routeParams, $location, dormitories) {
        dormitories.get($routeParams.id).then(function (dormitory) {
            $scope.dormitory = dormitory;
        });

        $scope.new = function (dormitory) {
            dormitories.update(dormitory);
            $location.path('/');
        };
    }
]);

dormitory.controller('NewCtrl', ['$scope', '$location', 'dormitories',
    function ($scope, $location, dormitories) {
        $scope.new = function (dormitory) {
            dormitories.add(dormitory);
            $location.path('/');
        };
    }
]);

dormitory.controller('directiveCtrl', ['$scope', '$routeParams', 'dormitories',
    function ($scope, $routeParams, dormitories) {
        dormitories.get($routeParams.id).then(function (dormitory) {
            $scope.dormitory = dormitory;
        });
    }
]);