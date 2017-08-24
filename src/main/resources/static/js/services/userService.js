(function () {
    angular.module("app")
        .factory('UserService', UserService);

    UserService.$inject = ['$http', '$q'];

    function UserService($http, $q) {

        var service = {
            getUsers: getUsers,
            getUserById: getUserById,
            updateUser: updateUser
        };

        return service;

        function updateUser(id, data) {
            var def = $q.defer();
            var req = {
                method: 'PUT',
                url: "/users/update-user/" + id,
                data: data
            };

            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;
        }

        function getUserById(id) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "/users/" + id
            };

            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;

        }
        
        function getUsers() {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "/users/"
            };

            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;
        }

    }
})();