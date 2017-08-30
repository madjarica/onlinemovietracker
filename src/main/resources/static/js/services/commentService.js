(function() {
	angular.module("app")
		.factory('CommentService', CommentService);

	CommentService.$inject = ['$http', '$q'];

	function CommentService($http, $q) {


		var service = {
    		saveComment: saveComment,
            deleteComment: deleteComment,
            getComments: getComments,
            getUserComment: getUserComment,
            getCommentByWatchlistId: getCommentByWatchlistId,
            getCommentsWatchlistCollection: getCommentsWatchlistCollection,
            userComment: [],
            selectedComment: {}
        }

        return service;

		  /**
         * Saving comment into database
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

        function getUserComment(username) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: '/watchlists/get-user-comment/' + username
            }

            $http(req).success(function (response) {
                console.log(username);
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;

        }

        function getCommentsWatchlistCollection(id){
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: '/watchlist-collection/get-comments/' + id
            }

            $http(req).success(function (response) {
                def.resolve(response);
            }).error(function (error) {
                def.reject(error);
            });

            return def.promise;
        }


    };
}());