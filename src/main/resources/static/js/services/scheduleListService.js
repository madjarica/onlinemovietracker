(function () {
    angular.module("app")
        .factory('ScheduleListService', ScheduleListService);

    ScheduleListService.$inject = ['$http', '$q'];

    function ScheduleListService($http, $q) {

        var service = {
            getUserScheduleList: getUserScheduleList
        };

        return service;

        function getUserScheduleList(username) {
            var def = $q.defer();

            var req = {
                method: 'GET',
                url: "schedule-list/by-user/" + username
            };

            return $http(req)
                .success(function (response) {
                    def.resolve(response);
                })
                .error(function () {
                    return def.reject("Failed to get schedule list");
                });

            return def.promise;
        }

    }
})();