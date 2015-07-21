'use strict';

var dormitory = angular.module('dormitory', []);

dormitory.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.
        when('/', {
            controller: 'ListCtrl',
            templateUrl: 'app/view/list.html'
        }).when('/edit/:id', {
            controller: 'EditCtrl',
            templateUrl: 'app/view/edit.html'
        }).when('/view/:id', {
            controller: 'ViewCtrl',
            templateUrl: 'app/view/view.html'
        }).when('/new', {
            controller: 'NewCtrl',
            templateUrl: 'app/view/edit.html'
        }).otherwise({
            redirectTo: '/'
        });
}]);