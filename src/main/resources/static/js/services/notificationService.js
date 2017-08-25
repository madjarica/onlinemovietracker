(function () {
    angular.module("app")
        .factory('NotificationService', NotificationService);

    NotificationService.$inject = ['$http', '$q'];

    function NotificationService($http, $q) {

        var service = {
            getUserNotifications: getUserNotifications,
            saveNotification: saveNotification,
            notifications: [],
            number: {}
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

        function saveNotification(notification) {
            var def = $q.defer();
            var req = {
                method: notification.id ? 'PUT' : 'POST',
                url: "user_notifications",
                data: notification}
            $http(req)
	            .success(function (data) {
	                def.resolve(data);
	            })
                .error(function () {
                    def.reject("Failed");
                });
            return def.promise;
        }

    }
})();