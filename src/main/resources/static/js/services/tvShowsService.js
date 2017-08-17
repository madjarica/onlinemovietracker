(function () {
    angular.module("app")
        .factory('TvShowsService', TvShowsService);

    TvShowsService.$inject = ['$http', '$q'];

    function TvShowsService($http, $q) {

        var service = {
            getTVShowByTitle: getTVShowByTitle,
            getTvShowyId: getTvShowyId,
            getTvShowTrailer: getTvShowTrailer,
            getTvShowImdbId: getTvShowImdbId,
            getTvShowDetails: getTvShowDetails,
            getTvShowByIdBackend : getTvShowByIdBackend,
            saveTvShow : saveTvShow,
            searchTvShowInLocalDatabase: searchTvShowInLocalDatabase,
            tvShowDetails: {}
        };

        return service;

        function getTvShowDetails(id) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "/tvshows/" + id
            };

            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;
        }

        function getTVShowByTitle (title) {
            var def = $q.defer();

            var settings = {
                "async": false,
                "crossDomain": true,
                "url": "https://api.themoviedb.org/3/search/tv?page=1&query=" + title + "&language=en-US&api_key=550e1867817e4bf3266023c5274d8858",
                "method": "GET",
                "headers": {},
                "data": "{}"
            }

            return $.ajax(settings).success(function (response) {
                def.resolve(response);
            }).error(function () {
                def.reject("Failed");
            });

            return def.promise;

        }

        function getTvShowyId (id) {
            var def = $q.defer();

            var settings = {
                "async": false,
                "crossDomain": true,
                "url": "https://api.themoviedb.org/3/tv/" + id + "?api_key=550e1867817e4bf3266023c5274d8858&language=en-US",
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

        function getTvShowTrailer(id) {
            var def = $q.defer();

            var settings = {
                "async": false,
                "crossDomain": true,
                "url": "https://api.themoviedb.org/3/tv/" + id + "/videos?api_key=550e1867817e4bf3266023c5274d8858&language=en-US",
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

        function getTvShowImdbId(id) {
            var def = $q.defer();

            var settings = {
                "async": false,
                "crossDomain": true,
                "url": "https://api.themoviedb.org/3/tv/" + id + "/external_ids?language=en-US&api_key=550e1867817e4bf3266023c5274d8858",
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

        function getTvShowByIdBackend(id){
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "/tvshows/getTvShow/" + id
            }
            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;

        }

        function saveTvShow(tvShow){
            var def = $q.defer();
            console.log(tvShow);
            var req = {
                method: tvShow.id ? 'PUT': 'POST',
                url: "/tvshows/",
                data: tvShow
            }
            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;

        }
        function searchTvShowInLocalDatabase(search){
            var def = $q.defer();
            console.log(search);
            var req = {
                method: 'GET',
                url: "tvshows/search/title/" + search,
            }
            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;
        }
    }
})();