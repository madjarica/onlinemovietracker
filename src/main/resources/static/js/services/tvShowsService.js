(function () {
    angular.module("app")
        .factory('TvShowsService', TvShowsService);

    TvShowsService.$inject = ['$http', '$q'];

    function TvShowsService($http, $q) {

        var service = {
            getTVShowByTitle: getTVShowByTitle,
            getTvShowyId: getTvShowyId,
            getTvShowTrailer: getTvShowTrailer,
            getTvShowImdbId: getTvShowImdbId
        };

        return service;

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
    }
})();