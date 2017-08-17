(function () {
    angular.module("app")
        .factory('EpisodeService', EpisodeService);

    EpisodeService.$inject = ['$http', '$q'];

    function EpisodeService($http, $q) {

        var service = {
            saveEpisode: saveEpisode
        };

        function saveEpisode(episode){
            var def = $q.defer();
            console.log(episode);
            var req = {
                method: episode.id ? 'PUT': 'POST',
                url: "/episode/",
                data: episode
            }
            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;
        }

        return service;
    }
})();