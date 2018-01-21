angular.module('LF', [])
    .controller('item', function ($scope, $http) {
        $http.get('/items').then(function (response) {
            $scope.items = response.data;
        });
    });