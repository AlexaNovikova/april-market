angular.module('app').controller('cartController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.loadCart = function () {
        $http({
            url: contextPath + '/api/v1/cart/showProducts',
            method: 'GET',
              params:
              {
              cartName: 'cart'
               }
        }).then(function (response) {
            $scope.cartDto = response.data;
        });
    };

    $scope.clearCart = function () {
        $http({
            url: contextPath + '/api/v1/cart/clear',
            method: 'GET',
              params: {
             cartName: 'cart'
              }
        }).then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.aprilMarketCurrentUser) {
            return true;
        } else {
            return false;
        }
    };

 $scope.addToCart = function (productId) {
         $http({
             url: contextPath + '/api/v1/cart/add/',
             method: 'GET',
             params: {
                 prodId: productId,
                 cartName: 'cart'
             }
         }).then(function (response) {
             $scope.loadCart();
         });
     }

 $scope.createOrder = function (telephone,email, address) {
        $http({
                    url: contextPath + '/api/v1/orders',
                    method: 'POST',
                    params: {
                     telephone: telephone,
                     email: email,
                     address: address,
                     temp: 'empty'
                     }
                }).then(function successCallback(response) {
                alert('Ваш заказ сформирован');
                $scope.loadCart();
                 })
                   .catch(function errorCallback (response){
                   console.log(response.data);
                   alert(response.data.message);
                 });
            };
    $scope.loadCart();
});