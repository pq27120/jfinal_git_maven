'use strict';

dormitory.factory('dormitories', ['$http', function ($http) {
    var url = "http://172.31.8.38:8086/dormitory";
    $http.defaults.headers = {'Content-type': 'application/json'}
    var factory = {};

    factory.all = function () {
        var dormitories = $http.get(url).then(function (resp) {
            return resp.data;
        });
        return dormitories;
    };

    factory.get = function (id) {
        var dormitory = $http.get(url + '/' + id).then(function (resp) {
            return resp.data;
        });
        return dormitory;
    };

    factory.add = function(dormitory) {
        $http({
            url: url,
            dataType: 'json',
            method: 'POST',
            data: {name :dormitory['name']}
        }).success(function(resp){
            return resp;
        });
    };

    factory.update = function(dormitory) {
        $http({
            url: url + '/' +dormitory.id,
            dataType: 'json',
            method: 'PUT',
            data: {name :dormitory['name']},
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            }
        }).success(function(resp){
            return resp;
        });
    };

    factory.delete = function(id) {
        $http({
            url: url + '/' +id,
            dataType: 'json',
            method: 'DELETE',
            data: {}
        }).success(function(resp){
            return resp;
        });
    };

    return factory;
}]);