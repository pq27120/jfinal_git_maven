'use strict';

dormitory.directive('myDirective', function () {
    return {
        restrict: 'E',
        controller: 'directiveCtrl',
        templateUrl: 'views/directive.html'
    }
})
;