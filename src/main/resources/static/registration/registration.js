angular.module('app').controller('registrationController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.isUserLoggedIn = function () {
        if ($localStorage.aprilMarketCurrentUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.register = function () {
           $http.post(contextPath + '/api/v1/register', $scope.newUser)
               .then(function successCallback(response) {
                  alert('Вы успешно зарегистрированы! Войдите в магазин используя ваш логин и пароль.');
                   },
                   function errorCallback(response){
                   console.log(response.data);
                   alert(response.data.message);
               });
       };
});