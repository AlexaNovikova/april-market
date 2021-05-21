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


    $scope.showMyOrders = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.myOrders = response.data;
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
                     $scope.currentUserName=$scope.user.username;
                    $scope.user.username = null;
                    $scope.user.password = null;

                    $scope.showMyOrders();
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


 $scope.createOrder = function (telephone,email,address) {
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
                $scope.showMyOrders();
                $scope.updateCart();
                 },
                 function errorCallback(response){
                 console.log(response.data);
                 alert(response.data.message);
             }
                 );
            };

 $scope.register = function (login, password, email) {
        $http({
                    url: contextPath + '/api/v1/register',
                    method: 'POST',
                    params: {
                     login: login,
                     password: password,
                     email: email,
                     temp: 'empty'
                     }
                }).then(function successCallback(response) {
                alert('Вы успешно зарегистрированы! Войдите в магазин используя ваш логин и пароль.');

                 },
                 function errorCallback(response){
                 console.log(response.data);
                 alert(response.data.message);
             }
                 );
            };
    if ($localStorage.aprilMarketCurrentUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.aprilMarketCurrentUser.token;
        $scope.currentUserName=$localStorage.aprilMarketCurrentUser.username;
        $scope.showMyOrders();
    }

      $scope.loadPage(1);
      $scope.updateCart();

});