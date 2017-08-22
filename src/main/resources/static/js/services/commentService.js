(function() {
	angular.module("app")
		.factory('CommentService', CommentService);
	
	commentService.$inject = ['$http', '$q'];
	
	function commentService($http, $q) {

		
		var service = {
    		saveComment: saveComment,
            deleteComment: deleteComment,
            getComments: getComments,
            getCommentByWatchlistId: getCommentByWatchlistId,
            selectedWatchlist: {}
        }

        return service;
		
		  /**
         * Saving comment into database
         * @param {Object} comment
         * @return {Object} data
         */
        function saveComment(comment) {
            var def = $q.defer();
            var req = {
                method: comment.id ? 'PUT' : 'POST',
                url: "comments",
                data: comment}
            $http(req)
	            .success(function (data) {
	                def.resolve(data);
	            })
                .error(function () {
                    def.reject("Failed");
                });
            return def.promise;
        }
        
        /**
         * Deleting comment from database
         * @param {Long} id
         * @return {Object} data
         */
        function deleteComment(id) {
            var def = $q.defer();
            var req = {
                method: 'DELETE',
                url: "comments/" + id
            }
            $http(req)
	            .success(function (data) {
	                def.resolve(data);
	            })
                .error(function () {
                    def.reject("Failed");
                });
            return def.promise;
        }
        
        /**
         * Getting all comments from database
         * @return {Object} data
         */
        function getComments() {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "comments"
            }
            $http(req)
	            .success(function (data) {
	                def.resolve(data);
	            })
                .error(function () {
                    def.reject("Failed to get comments");
                });
            return def.promise;
        }
        
        /**
         * Getting all comments for single watchlist
         * @param {Long} id
         * @return {Object} data
         */
        function getCommentByWatchlistId(id) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "comments/watchlist/" + id
            }
            $http(req)
	            .success(function (data) {
	                def.resolve(data);
	            })
	            .error(function () {
	                return def.reject("Failed to get comments");
	            });
            return def.promise;
        }
        
    };
}());        