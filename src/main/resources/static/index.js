angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

   $scope.loadPage = function (page) {
           $http({
               url: contextPath + '/api/v1/products',
               method: 'GET',
               params: {
                   p: page
               }
           }).then(function (response) {
               $scope.productsPage = response.data;

               let minPageIndex = page - 2;
               if (minPageIndex < 1) {
                   minPageIndex = 1;
               }

               let maxPageIndex = page + 2;
               if (maxPageIndex > $scope.productsPage.totalPages) {
                   maxPageIndex = $scope.productsPage.totalPages;
               }

               $scope.paginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
               console.log("PAGE FROM BACKEND")
               console.log($scope.productsPage);

           });
       };


     $scope.updateCart = function () {
             $http.get(contextPath + '/api/v1/cart/showProducts')
                 .then(function (response) {
                     $scope.cart=response.data;

                 });
         };

    $scope.createNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.p)
            .then(function successCallback(response) {
                $scope.loadPage(1);
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

    $scope.saveCart = function () {
                 $http.get(contextPath + '/api/v1/cart/save')
                     .then(function (response) {
                         $scope.updateCart();
                     });
             };

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

 $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

  $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.aprilMarketCurrentUser = {username: $scope.user.username, token: response.data.token};
                    $scope.user.username = null;
                    $scope.user.password = null;

                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
    };

    $scope.clearUser = function () {
        delete $localStorage.aprilMarketCurrentUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.aprilMarketCurrentUser) {
            $scope.currentUserName=$localStorage.aprilMarketCurrentUser.username;
            return true;
        } else {
            return false;
        }
    };

    $scope.whoAmI = function () {
        $http({
            url: contextPath + '/api/v1/users/me',
            method: 'GET'
        }).then(function (response) {
            alert(response.data.username + ' ' + response.data.email);
        });
    };


    if ($localStorage.aprilMarketCurrentUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.aprilMarketCurrentUser.token;
    }

      $scope.loadPage(1);
      $scope.updateCart();

});