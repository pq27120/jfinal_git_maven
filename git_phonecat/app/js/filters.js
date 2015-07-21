'use strict';

dormitory.filter('paging', function () {
        return function (input, start) {
            if (input) {
                return input.slice(start);
            }
        };
    })
    .filter('name', function () {
        return function (input, dormitoryFilterdInput) {
            if (input) {
                var result = new Array();
                for (var i = 0; i < input.length; i++) {
                    if (input[i].name.toLowerCase().indexOf(dormitoryFilterdInput.toLowerCase()) != -1) {
                        result.push(input[i]);
                    }
                }
                return result;
            }
        };
    })
;