'use strict';

dormitory.factory('dormitories', ['$http', function ($http) {
    var url = "http://172.31.8.38:8086/dormitory";
    $http.defaults.headers = {'Content-type': 'application/json'}
    var factory = {};

    factory.all = function () {
        var dormitories = $http.get(url).then(function (resp) {
            alert(resp.data);
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
        $http.post(url, dormitory).then(function(resp){
            return resp;
        });
    };

    factory.update = function(dormitory) {
        $http.put(url + '/' + dormitory.id, dormitory).then(function(resp){
            return resp;
        });
    };

    factory.delete = function(id) {
        $http.delete(url + '/' + id).then(function(resp){
            return resp;
        });
    };

    return factory;
}]);