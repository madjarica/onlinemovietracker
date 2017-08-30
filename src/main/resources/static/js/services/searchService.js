(function () {
    angular.module("app")
        .factory('SearchService', SearchService);

    SearchService.$inject = ['$http', '$q'];

    function SearchService($http, $q) {

        var service = {
            getMovies: getMovies,
            getTvShows: getTvShows,
            getVideos: getVideos,
            getVideoByTitle: getVideoByTitle
        };

        return service;

        var moviesList = [];
        var tvShowsList = [];
        var videoList = [];
        
        function getVideos() {
            var def = $q.defer();

            var req = {
                method: 'GET',
                url: "videos"
            };

            return $http(req)
                .success(function (response) {
                    return videoList = response.data;
                })
                .error(function () {
                    return def.reject("Failed to get videos");
                });

            return def.promise;

        }

        function getMovies() {
            var def = $q.defer();

            var req = {
                method: 'GET',
                url: "movies"
            };

            return $http(req)
                .success(function (response) {
                    return moviesList = response.data;
                })
                .error(function () {
                    return def.reject("Failed to get movies");
                });

            return def.promise;
        }

        function getTvShows() {
            var def = $q.defer();

            var req = {
                method: 'GET',
                url: "tvshows"
            };

            return $http(req)
                .success(function (response) {
                    return tvShowsList = response.data;
                })
                .error(function () {
                    return def.reject("Failed to get tvshows");
                });

            return def.promise;
        }
        
        function getVideoByTitle(video) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "videos/search/video/" + video
            }
            return $http(req)
	            .success(function (response) {
	                return videossList = response.data;
	            })
	            .error(function () {
	                return def.reject("Failed to get videos with given criteria");
	            });
        }
    }
})();