(function () {
    angular.module("app")
        .factory('HomeService', HomeService);

    HomeService.$inject = ['$http', '$q'];

    function HomeService($http, $q) {

        var service = {
            getOnTvFromService: getOnTvFromService,
            getInTheatersService: getInTheatersService,
            getOnTvTodayFromService: getOnTvTodayFromService,
            getLatestThreeMovies: getLatestThreeMovies,
            getLatestThreeTvShows: getLatestThreeTvShows
        };

        return service;

        function getLatestThreeMovies() {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "/movies/get-latest-three"
            };
            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;
        }
        
        function getLatestThreeTvShows() {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "/tvshows/get-latest-three"
            };
            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;
        }

        function getOnTvFromService() {
            var def = $q.defer();

            var settings = {
                "async": false,
                "crossDomain": true,
                "url": "https://api.themoviedb.org/3/tv/on_the_air?page=1&language=en-US&api_key=550e1867817e4bf3266023c5274d8858",
                "method": "GET",
                "headers": {},
                "data": "{}"
            };

            return $.ajax(settings).success(function (response) {
                def.resolve(response);
            }).error(function () {
                def.reject("Failed");
            });

            return def.promise;
        }

        function getOnTvTodayFromService() {
            var def = $q.defer();

            var settings = {
                "async": false,
                "crossDomain": true,
                "url": "https://api.themoviedb.org/3/tv/airing_today?api_key=550e1867817e4bf3266023c5274d8858&language=en-US&page=1",
                "method": "GET",
                "headers": {},
                "data": "{}"
            };

            return $.ajax(settings).success(function (response) {
                def.resolve(response);
            }).error(function () {
                def.reject("Failed");
            });

            return def.promise;
        }

        function getInTheatersService() {
            var def = $q.defer();

            var settings = {
                "async": false,
                "crossDomain": true,
                "url": "https://api.themoviedb.org/3/movie/now_playing?page=1&language=en-US&api_key=550e1867817e4bf3266023c5274d8858",
                "method": "GET",
                "headers": {},
                "data": "{}"
            };

            return $.ajax(settings).success(function (response) {
                def.resolve(response);
            }).error(function () {
                def.reject("Failed");
            });

            return def.promise;
        }
    }
})();