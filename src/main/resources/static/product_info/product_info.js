angular.module('app').controller('productInfoController', function ($scope, $http, $localStorage, $routeParams, $location) {
    const contextPath = 'http://localhost:8189/market';

 $scope.hasBeenOrderedByUser = function () {
            $http({
                url: contextPath + '/api/v1/orders/hasOrdered',
                method: 'GET',
                 params: {
                    id: $scope.prod.id,
                   temp: 'empty'
                   }
            }).then(function (response) {
              $scope.productHasBeenOrderedByUser = response.data;
           });
      };

  $scope.loadProduct = function () {
          $http({
              url: contextPath + '/api/v1/products/' + $routeParams.productIdParam,
              method: 'GET'
          }).then(function (response) {
              $scope.prod = response.data.productDto;
              $scope.comments = response.data.comments;
              $scope.hasBeenOrderedByUser();
          });
      };

$scope.addComment = function (comment) {
        $http({
                    url: contextPath + '/api/v1/products/addComment',
                    method: 'POST',
                    params: {
                     comment: comment,
                     id: $scope.prod.id,
                     username: $localStorage.aprilMarketCurrentUser.username,
                     temp: 'empty'
                     }
                }).then(function successCallback(response) {
                alert('Ваш комментарий добавлен. Спасибо!');
                $scope.loadProduct();
                 });
            };

     $scope.isUserLoggedIn = function () {
          if ($localStorage.aprilMarketCurrentUser) {
              return true;
          } else {
              return false;
          }
      };


    $scope.loadProduct();
});