(function () {
    angular.module("app")
        .factory('NotificationService', NotificationService);

    NotificationService.$inject = ['$http', '$q'];

    function NotificationService($http, $q) {

        var service = {
            getUserNotifications: getUserNotifications
        };

        return service;

        function getUserNotifications(username) {
            var def = $q.defer();

            var req = {
                method: 'GET',
                url: "user_notifications/by-user/" + username
            };

            return $http(req)
                .success(function (response) {
                    def.resolve(response);
                })
                .error(function () {
                    return def.reject("Failed to get notifications");
                });

            return def.promise;
        }

    }
})();