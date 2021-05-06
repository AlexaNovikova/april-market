angular.module('app', []).controller('indexController', function ($scope, $http) {
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
                     $scope.productsInCart=$scope.cart.items;
                     $scope.totalCount=$scope.cart.itemsCount;
                      $scope.totalPrice=$scope.cart.totalPrice;
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

     $scope.loadPage(1);

});