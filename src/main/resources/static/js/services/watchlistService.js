(function () {
    angular.module("app")
        .factory('WatchlistService', WatchlistService);

    WatchlistService.$inject = ['$http', '$q'];

    function WatchlistService($http, $q) {

        var service = {
            getWatchlists: getWatchlists,
            getWatchlist: getWatchlist,
            saveWatchlist: saveWatchlist,
            getUserWatchlist: getUserWatchlist,
            getWatchlists: getWatchlists,
            deleteWatchlist: deleteWatchlist,
            changeWatchDate: changeWatchDate,
            getwatchlistDetails: getwatchlistDetails,
            getByTitle: getByTitle,
            userWatchlist: [],
            selectedWatchlist: {}
        };

        return service;

        function changeWatchDate(id, date) {
            var def = $q.defer();
            var req = {
                method: 'POST',
                url: "watchlists/change-watch-date/" + id,
                data: date
            };
            $http(req)
                .success(function (data) {
                    def.resolve(data);
                })
                .error(function (error) {
                    def.reject(error.message);
                });
            return def.promise;
        }

        function getWatchlists() {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "watchlists/"
            };

            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;
        }

        function getWatchlist(id){
        	var def = $q.defer();
            var req = {
                method: 'GET',
                url: "/watchlists/" + id,
            };

            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;
        }

        function saveWatchlist(watchlist) {
            var def = $q.defer();
            var req = {
                method: watchlist.id ? 'PUT': 'POST',
                url: "/watchlists/",
                data: watchlist
            };

            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;
        }

        function deleteWatchlist(id) {
            var def = $q.defer();
            var req = {
                method: 'DELETE',
                url: "/watchlists/" + id,
            };

            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;
        }

        function getUserWatchlist(username) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: '/watchlists/get-user-watchlist/' + username
            }

            $http(req).success(function (response) {
                console.log(username);
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;

        }
        
        function getwatchlistDetails(id) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "/watchlists/" + id
            };

            $http(req).success(function (response) {


        function getByTitle(search) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: '/watchlists/get-by-title/' + search
            }

            $http(req).success(function (response) {
                console.log(search);
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;

        }
    }
})();