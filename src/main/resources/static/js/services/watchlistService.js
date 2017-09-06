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
            deleteWatchlist: deleteWatchlist,
            changeWatchDate: changeWatchDate,
            getUserWatchlistCollection: getUserWatchlistCollection,
            saveWatchlistCollection: saveWatchlistCollection,
            getVideoById: getVideoById,
            getAverageRating: getAverageRating,
            setWatchlistForRedirect: setWatchlistForRedirect,
            getLatestThree: getLatestThree,
            currentUserWatchlist:[],
            userWatchlist: [],
            selectedWatchlist: {},
            selectedCollection: {},
            userOfCollection : ""
        };

        return service;

        function getVideoById(id) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "videos/" + id
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
                url: "/watchlists/" + id
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
                url: "/watchlists/" + id
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

        
        function getUserWatchlistCollection(username) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: '/watchlist-collection/find-by-user/' + username
            }

            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;
        }

        function saveWatchlistCollection(watchlistCollection) {
            var def = $q.defer();
            var req = {
                method: 'POST',
                url: "/watchlist-collection/",
                data: watchlistCollection
            };

            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;
        }
        
        function getAverageRating(id) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: '/watchlists/get-ratings/' + id
            }

            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;
        }

        function setWatchlistForRedirect(id) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: '/watchlists/get-for-redirect/' + id
            }

            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;
        }

        function getLatestThree() {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: '/watchlists/get-latest-three/'
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