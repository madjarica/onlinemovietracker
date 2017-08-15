(function () {
    angular.module("app")
        .factory('AuthenticationService', AuthenticationService);

    AuthenticationService.$inject = ['$http', '$q'];

    function AuthenticationService($http, $q) {

        var service = {

        };

        return service;


    }
})();