(function() {
	angular.module("app")
		.factory('RatingService', RatingService);
	
	RatingService.$inject = ['$http', '$q'];
	
	function RatingService($http, $q) {

		
		var service = {			
			getRatings: getRatings,	
			deleteRating: deleteRating,
            deleteRatingByWatchlistId: deleteRatingByWatchlistId,
    		saveRating: saveRating,
            getRatings: getRatings,
            selectedRating: {}
        };

        return service;
		
        function deleteRating(id) {
            var def = $q.defer();
            var req = {
                method: 'DELETE',
                url: "ratings/" + id
            };
            $http(req)
	            .success(function (data) {
	                def.resolve(data);
	            })
                .error(function () {
                    def.reject("Failed");
                });
            return def.promise;
        }

        function deleteRatingByWatchlistId(id) {
            var def = $q.defer();
            var req = {
                method: 'DELETE',
                url: "ratings/remove-by-watchlist-id/" + id
            };
            $http(req)
	            .success(function (data) {
	                def.resolve(data);
	            })
                .error(function () {
                    def.reject("Failed");
                });
            return def.promise;
        }

        function saveRating(rating) {
            var def = $q.defer();
            var req = {
                method: rating.id ? 'PUT' : 'POST',
                url: "ratings",
                data: rating
            };
            $http(req)
	            .success(function (data) {
	                def.resolve(data);
	            })
                .error(function () {
                    def.reject("Failed");
                });
            return def.promise;
        }
        
        function getRatings() {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "ratings"
            }
            $http(req)
	            .success(function (data) {
	                def.resolve(data);
	            })
                .error(function () {
                    def.reject("Failed to get ratings");
                });
            return def.promise;
        }               
        
    };
}());