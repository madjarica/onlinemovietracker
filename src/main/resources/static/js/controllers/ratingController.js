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
        vm.ratings = {};
        vm.ratings = vm.selectedWatchlist.rating;
        vm.rateMark;
        vm.getAverageRate = getAverageRate;
        vm.operation;
        vm.count;
        vm.rating;
        vm.rating = {};
        vm.saveRating = saveRating;
        vm.addRating = addRating;
        vm.deleteRating = deleteRating;
        vm.getRatings = getRatings;
        	

        // Rating functions
        
        init();
        
        function init(){
        	getRatings(vm.selectedWatchlist.id);
        }
        
        function hoveringOver(rateMark) {
            vm.overStar = rateMark;
            vm.percent = 100 * (rateMark / vm.max);
        }
        
        function getRatings(id) {
            WatchlistService.getWatchlist(id).then(function (response) {
                vm.selectedWatchlist = response;
                console.log(vm.selectedWatchlist);
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
            	console.log(response);
                init();
            }), function (error) {

            }
        }
        
        function getAverageRate(id) {
        	WatchlistService.getWatchlist(id).then(function(response){
        		vm.selectedWatchlist = response;
        		console.log(vm.selectedWatchlist);
        	}).then(function(){
        		vm.rating = {};
        		vm.ratings = vm.selectedWatchlist.rating;
        		console.log(vm.ratings);
        		var count = 0;
        		var pom = 0;
        		if(vm.ratings != undefined){
        		vm.ratings.forEach(function(rating) {
        			if(rating.rateMark != null) {
        				pom = pom + rating.rateMark;
        				count++;
        				console.log(pom);
        			}        			
        		})
        		console.log(vm.rating.rateMark);
        		vm.selectedWatchlist.averageRate = Math.round((pom / count)*100)/100;
        		vm.count = count;
        		console.log(pom);
        		console.log(vm.count);
        		console.log(vm.selectedWatchlist.averageRate);
        	}
        	}).then(function(){
        		WatchlistService.saveWatchlist(vm.selectedWatchlist).then(function(response){
        			console.log("jupi");
        		})
        	})
        }
         
        	function addRating(rateMark){
            	vm.rating = {};
                vm.rating.rateMark = rateMark;
                console.log(vm.rating);
                RatingService.saveRating(vm.rating)
                    .then(function (response) {
                        vm.selectedWatchlist.rating.push(response);
                        console.log(response);
                    })
                    .then(function () {
                    WatchlistService.saveWatchlist(vm.selectedWatchlist).then(function (response) {
                    	vm.selectedWatchlist = response;
                    	console.log(vm.selectedWatchlist);
                    	 vm.ratings = vm.selectedWatchlist.rating;
                        console.log(vm.selectedWatchlist.averageRate);
                    	console.log(vm.selectedWatchlist);
                    }).then(function (){
                        getAverageRate(vm.selectedWatchlist.id);
                    });
                });
        }
        
        function deleteRating(id) {
            RatingService.deleteRating(id)
            vm.rating = {};
        }
//        
        // End of Rating functions

    }
})();