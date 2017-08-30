(function () {
    angular.module('app')
        .controller('RatingController', RatingController);

    RatingController.$inject = ['$location', '$http', '$route', 'RatingService', 'WatchlistService'];

    function RatingController($location, $http, $route, RatingService, WatchlistService) {

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
        vm.rating = {};

        // Rating functions
        
        init();
        
        function init(){
        	getRatings(vm.selectedWatchlist.id);
        }
        
        function hoveringOver(value) {
            vm.overStar = value;
            vm.percent = 100 * (value / vm.max);
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
        	vm.rating = rating;
            RatingService.saveRating(rating).then(function (response) {
            	console.log(response);
                init();
            }, function (error) {

            })

        }
        
        function deleteRating(id) {
            RatingService.deleteRating(id)
            vm.rating = {};
        }
        
        // End of Rating functions

    }
})();