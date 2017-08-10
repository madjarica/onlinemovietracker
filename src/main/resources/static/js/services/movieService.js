(function () {
    angular.module("app")
        .factory('MovieService', MovieService);

    MovieService.$inject = ['$http', '$q'];

    function MovieService($http, $q) {

        var service = {
            getMovieByTitle: getMovieByTitle,
            getMovieById: getMovieById,
            getMovieTrailer: getMovieTrailer,
            getMovieByIdBackend:getMovieByIdBackend
        };

        return service;
        
        function getMovieByTitle (title) {
            var def = $q.defer();

            var settings = {
                "async": false,
                "crossDomain": true,
                "url": "https://api.themoviedb.org/3/search/movie?include_adult=false&page=1&query=" + title + "&language=en-US&api_key=550e1867817e4bf3266023c5274d8858",
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

        function getMovieById (id) {
            var def = $q.defer();

            var settings = {
                "async": false,
                "crossDomain": true,
                "url": "https://api.themoviedb.org/3/movie/" + id + "?api_key=550e1867817e4bf3266023c5274d8858&language=en-US",
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

        function getMovieTrailer(id) {
            var def = $q.defer();

            var settings = {
                "async": false,
                "crossDomain": true,
                "url": "https://api.themoviedb.org/3/movie/" + id + "/videos?language=en-US&api_key=550e1867817e4bf3266023c5274d8858",
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
        function getMovieByIdBackend(id){
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "/movies/getMovie/" + id
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