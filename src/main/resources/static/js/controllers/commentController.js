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
        
        vm.comment = {};
        
        init();

        function init() {
        	getComments(vm.watchlist.id);
        }
        
        function addComment(commentInput) {
        	vm.comment = {};
        	vm.comment.commentContent = commentInput.commentContent;
        	vm.comment.createdDate = new Date();
            vm.watchlist.comment.push(vm.comment);
            commentInput.commentContent='';
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
    };
})();