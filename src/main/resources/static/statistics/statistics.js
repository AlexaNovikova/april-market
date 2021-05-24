angular.module('app').controller('statisticsController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.showStatistic = function () {
        $http({
            url: contextPath + '/api/v1/statistics',
            method: 'GET'
        }).then(function (response) {
            $scope.myStatistics = response.data;
        });
    };
 $scope.showStatistic();
});