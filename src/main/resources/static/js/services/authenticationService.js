(function () {
    angular.module("app")
        .factory('AuthenticationService', AuthenticationService);

    AuthenticationService.$inject = ['$http', '$q'];

    function AuthenticationService($http, $q) {

        var service = {
            saveUser: saveUser
        };

        return service;

        function saveUser(user) {
            var def = $q.defer();
            var req = {
                method: user.id ? 'PUT' : 'POST',
                url: "users",
                data: user
            };
            $http(req)
                .success(function (data) {
                    def.resolve(data);
                })
                .error(function (error) {
                    def.reject(error.message);
                });
            return def.promise;
        }

    }
})();