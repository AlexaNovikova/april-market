angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.init = function () {
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
            });
    };

     $scope.updateCart = function () {
             $http.get(contextPath + '/api/v1/cart/showProducts')
                 .then(function (response) {
                     $scope.productsInCart = response.data;
                 });
         };

    $scope.createNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.p)
            .then(function successCallback(response) {
                $scope.init();
                $scope.p=null;
                },
                function errorCallback(response){
                console.log(response.data);
                alert(response.data.message);
            });
            };

    $scope.addProductToCart = function (productId) {
          $http({
              url: contextPath + '/api/v1/cart/add',
              method: 'GET',
              params: {
                  id: productId,
                  temp: 'empty'
              }
          }).then(function (response) {
             $scope.updateCart();
          });
      }

       $scope.removeProductFromCart = function (productId) {
                $http({
                    url: contextPath + '/api/v1/cart/remove',
                    method: 'GET',
                    params: {
                        id: productId,
                        temp: 'empty'
                    }
                }).then(function (response) {
                   $scope.updateCart();
                });
            }

  $scope.cleanCart = function () {
             $http.get(contextPath + '/api/v1/cart/deleteAll')
                 .then(function (response) {
                     $scope.updateCart();
                 });
         };



     $scope.init();

});