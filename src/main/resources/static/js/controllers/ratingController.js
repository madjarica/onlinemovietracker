(function () {
    angular.module('app')
        .controller('RatingController', RatingController);

    RatingController.$inject = ['$location', '$http', '$route', 'RatingService', 'WatchlistService', 'AuthenticationService', 'MovieService'];

    function RatingController($location, $http, $route, RatingService, WatchlistService, AuthenticationService, MovieService) {

        var vm = this;

        vm.max = 10;
        vm.isReadonly = false;
        vm.hoveringOver = hoveringOver;
        vm.ratingStates = [{
            stateOn: 'glyphicon-star', stateOff: 'glyphicon-star-empty'
        }];
        vm.selectRating = selectRating;
        vm.selectedRating = {};
        vm.selectedWatchlist = WatchlistService.selectedWatchlist;
        vm.watchlistCollection = WatchlistService.selectedCollection;
        vm.ratings = {};
        vm.ratings = vm.selectedWatchlist.rating;
        vm.rateMark;
        vm.getAverageRate = getAverageRate;
        vm.operation;
        vm.count;
        vm.rating = {};
        vm.saveRating = saveRating;
        vm.addRating = addRating;
        vm.deleteRating = deleteRating;
        vm.getRatings = getRatings;

        // Rating functions

        init();

        function init() {
            if ($location.path() === "/movie-details") {
                vm.selectedWatchlist = WatchlistService.selectedWatchlist;
            }
            getRatings(vm.selectedWatchlist.id);
        }

        function hoveringOver(rateMark) {
            vm.overStar = rateMark;
            vm.percent = 100 * (rateMark / vm.max);
        }

        function getRatings(id) {
            WatchlistService.getWatchlist(id).then(function (response) {
                vm.selectedWatchlist = response;
            }).then(function () {
                vm.ratings = vm.selectedWatchlist.rating;
            })
        }

        function selectRating(rating) {
            vm.selectedRating = angular.copy(rating);
        }

        function saveRating(rating) {
//        	rating = vm.selectedWatchlist.id;
            RatingService.saveRating(rating).then(function (response) {
                init();
            }, function (error) {

            })
        }

        function getAverageRate(id) {
            WatchlistService.getWatchlist(id).then(function (response) {
                vm.selectedWatchlist = response;
            }).then(function () {
                vm.rating = {};
                vm.ratings = vm.selectedWatchlist.rating;
                var count = 0;
                var pom = 0;
                if (vm.ratings != undefined) {
                    vm.ratings.forEach(function (rating) {
                        if (rating.rateMark != null) {
                            pom = pom + rating.rateMark;
                            count++;
                        }
                    });
                    console.log(vm.selectedWatchlist.averageRate);
                    // vm.selectedWatchlist.averageRate = Math.round((pom / count)*100)/100;
                    // vm.selectedWatchlist.averageRate = (pom / count).toFixed(2);
                    vm.count = count;
                }
            }).then(function () {
                WatchlistService.saveWatchlist(vm.selectedWatchlist).then(function (response) {
                    console.log("sacuvao watchlistu");
                })
            })
        }

        function addRating(rateMark) {
            vm.rating = {};
            vm.rating.rateMark = rateMark;
            WatchlistService.setWatchlistForRedirect(MovieService.movieDetails.id).then(function (resposne) {
                WatchlistService.selectedWatchlist = resposne;
                vm.selectedWatchlist = WatchlistService.selectedWatchlist;
            }).then(function () {
                RatingService.saveRating(vm.rating)
                    .then(function (response) {
                        vm.selectedWatchlist.rating.push(response);
                        console.log(vm.selectedWatchlist.rating);
                    }).then(function () {
                    WatchlistService.saveWatchlist(vm.selectedWatchlist).then(function (response) {
                        vm.selectedWatchlist = response;
                        vm.ratings = vm.selectedWatchlist.rating;
                        console.log(vm.selectedWatchlist.rating);
                    }).then(function () {
                        // getAverageRate(vm.selectedWatchlist.id);
                        WatchlistService.getAverageRating(vm.selectedWatchlist.video.id).then(function (response) {
                            MovieService.movieDetails.averageRate = response.toFixed(2);
                        })
                    });
                });
            })
        }

        function deleteRating(id) {
            RatingService.deleteRating(id);
            vm.rating = {};
        }

//        
        // End of Rating functions

    }
})();