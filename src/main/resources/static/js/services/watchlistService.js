(function () {
    angular.module("app")
        .factory('WatchlistService', WatchlistService);

    WatchlistService.$inject = ['$http', '$q'];

    function WatchlistService($http, $q) {

        var service = {
            getWatchlists: getWatchlists,
            saveWatchlist: saveWatchlist,
            getUserWatchlist: getUserWatchlist,
            deleteWatchlist: deleteWatchlist,
            userWatchlist: [],
            selectedWatchlist: {}
        };

        return service;

        function getWatchlists() {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "/watchlists/"
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

    }
})();