(function () {
    angular.module("app")
        .factory('AuthenticationService', AuthenticationService);

    AuthenticationService.$inject = ['$http', '$q'];

    function AuthenticationService($http, $q) {

        var service = {
            saveUser: saveUser,
            requestNewPassword: requestNewPassword,
            requestHashedEmail: requestHashedEmail,
            changePassword: changePassword,
            sendCaptcha: sendCaptcha,
            currentUsername: null,
            currentUserRoles: null
        };

        return service;

        function sendCaptcha(data) {
            var def = $q.defer();
            var req = {
                method: 'POST',
                url: "users/captcha",
                data: data
            };

            return $http(req).success(function (response) {
                return response;
            }).error(function () {
                def.reject("Failed");
            });

            return def.promise;
        }

        function changePassword(username, password) {
            var def = $q.defer();
            var req = {
                method: 'POST',
                url: "users/change-password/" + username,
                data: password
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
        
        function requestHashedEmail(username) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "users/hashed-email/" + username
            };

            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;
        }

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

        function requestNewPassword(email) {
            var def = $q.defer();
            var req = {
                method: 'POST',
                url: "users/request-new-password/" + email + "/"
            };
            $http(req)
                .success(function (data) {
                    def.resolve(data);
                })
                .error(function (error) {
                    console.log(error);
                    def.reject(error.message);
                });
            return def.promise;
        }

    }
})();