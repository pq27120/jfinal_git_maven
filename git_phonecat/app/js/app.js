'use strict';

var book = angular.module('book', []);

book.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.
        when('/', {
            controller: 'ListCtrl',
            templateUrl: 'view/list.html'
        }).when('/edit/:id', {
            controller: 'EditCtrl',
            templateUrl: 'view/edit.html'
        }).when('/view/:id', {
            controller: 'ViewCtrl',
            templateUrl: 'view/view.html'
        }).when('/new', {
            controller: 'NewCtrl',
            templateUrl: 'view/edit.html'
        }).otherwise({
            redirectTo: '/'
        });
}]);