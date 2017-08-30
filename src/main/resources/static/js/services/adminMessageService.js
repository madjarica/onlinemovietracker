(function () {
    angular.module("app")
        .factory('AdminMessageService', AdminMessageService);

    AdminMessageService.$inject = ['$http', '$q'];

    function AdminMessageService($http, $q) {

        var service = {
            getAdminMessages: getAdminMessages,
            saveAdminMessage : saveAdminMessage,
            deleteAdminMessage: deleteAdminMessage,
            adminMessages : [],
            number : 0
        };

        function getAdminMessages() {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "/admin-messages"
            }

            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;
        }

        function saveAdminMessage(adminMessage) {
            var def = $q.defer();
            var req = {
                method: adminMessage.id ? 'PUT' : 'POST',
                url: "/admin-messages",
                data: adminMessage
            }

            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;
        }

        function deleteAdminMessage(id){

            var def = $q.defer();
            var req = {
                method: 'DELETE',
                url: 'admin-messages/' + id
            }
            $http(req)
                .success(function (data) {
                    def.resolve(data);
                })
                .error(function () {
                    def.reject("Failed");
                });
            return def.promise;
        }

        return service;
    }
})();