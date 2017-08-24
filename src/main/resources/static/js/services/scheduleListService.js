(function () {
    angular.module("app")
        .factory('ScheduleListService', ScheduleListService);

    ScheduleListService.$inject = ['$http', '$q'];

    function ScheduleListService($http, $q) {

        var service = {
            getUserScheduleList: getUserScheduleList,
            saveSchedule: saveSchedule,
            deleteSchedule: deleteSchedule
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
                    def.reject("Failed to get schedule list");
                });

            return def.promise;
        }
        
        function saveSchedule(schedule) {
            var def = $q.defer();

            var req = {
                method:schedule.id ? 'PUT':'POST',
                url: "schedule-list",
                data: schedule
            }

            return $http(req).success(function (response) {
                def.resolve(response);
            })
                .error(function (error) {
                    def.reject(error);
                })

            return def.promise;
        }

        function deleteSchedule(id) {
            var def = $q.defer();

            var req = {
                method:'DELETE',
                url: "schedule-list/" + id
            }

            return $http(req).success(function (response) {
                def.resolve(response);
            })
                .error(function (error) {
                    def.reject(error);
                })

            return def.promise;
        }

    }
})();