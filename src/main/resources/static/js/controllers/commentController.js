(function(){
angular.module('app')
    .controller('CommentController', CommentController);
    
    CommentController.$inject = ['$location' ,'CommentService', '$filter', '$rootScope', 'WatchlistService'];
   
    function BookmarkDetailsController($location ,CommentService, $filter, $rootScope, WatchlistService) {
    	
    	var vm = this;
        vm.addComment = addComment;
        vm.deleteComment = deleteComment;
        vm.saveComment = saveComment;
        vm.comment;
        vm.selectedComment;
        vm.commentForm;
        vm.importError = "";
        
        vm.comment = {};
        
        init();

        function init() {
        	getComments(vm.watchlist.id);
        }
        
        function addComment(commentContent) {
        	vm.comment = {};
        	vm.comment.commentContent = commentContent;
        	vm.comment.createdDate = new Date();
            vm.watchlist.comment.push(vm.comment);
            CommentService.saveComment(vm.comment).then(function(response){
            	getComments(vm.watchlist.id)})
            
        }
        
        function getComments(id){
        	watchlistService.getWatchlist(id).then(function(response){
        		vm.watchlist = response;
        	}).then(function(){
        		vm.comments = vm.watchlist.comment;
        	})        	
        }
        
      //Get all comments
        function handleSuccessComment(data, status) {
            vm.comments = data;
        }
    };
})();