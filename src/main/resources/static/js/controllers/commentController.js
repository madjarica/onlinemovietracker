(function(){
angular.module('app')
    .controller('CommentController', CommentController);
    
    CommentController.$inject = ['$location' ,'CommentService', '$filter', '$rootScope', 'WatchlistService', 'AuthenticationService'];
   
    function CommentController($location ,CommentService, $filter, $rootScope, WatchlistService, AuthenticationService) {
    	
    	var vm = this;
        vm.addComment = addComment;
        vm.selectComment = selectComment;
        vm.saveComment = saveComment;
        vm.deleteComment = deleteComment;
        vm.commentForm;
//        vm.commentInput = {}
        vm.importError = "";
        vm.selectedWatchlist = WatchlistService.selectedWatchlist;
        vm.comments = vm.selectedWatchlist.comment;
        vm.comment = {};
        console.log(vm.comment);
        vm.username = AuthenticationService.currentUsername;
        vm.userComment = {};
        vm.selectedComment = {};
        
        
        init();

        function init() {
        	getComments(vm.selectedWatchlist.id);
        	getUserComment(vm.username)
        }
        
        function getUserComment(username) {
            console.log(username);
            CommentService.getUserComment(username).then(function (response) {
                vm.userComment = response;
                CommentService.userComment = response;
                console.log(vm.userComment)
            });
        }
        
        function addComment(commentContent) {
        	console.log('click');
        	console.log(vm.comment);
        	vm.comment.commentContent = commentContent;
        	vm.comment.commentUser = vm.username;
//        	vm.comment.watchlist = vm.selectedWatchlist;
        	vm.comment.createdDate = new Date();
            vm.comments.push(vm.comment);
//            commentInput.commentContent='';
            CommentService.saveComment(vm.comment);
            //.then(function(response){
//            	WatchlistService.selectedWatchlist = response;
//            	vm.selectedWatchlist.comment.push(vm.selectedComment);
//            	console.log(vm.selectedComment);
//            	})
//            	.then(function(){
            		WatchlistService.saveWatchlist(vm.selectedWatchlist).then(function (response){
            			WatchlistService.selectedWatchlist = response;
            			vm.selectedWatchlist = WatchlistService.selectedWatchlist;
            			console.log(vm.selectedWatchlist);
            			vm.comments = vm.selectedWatchlist.comment;
            			console.log(vm.comments);
            		});
//            	});
            	//getUserComment(vm.userComment.username)
            
        }
        
        function selectComment(comment){
        	vm.selectedComment = angular.copy(comment);
        	console.log(vm.selectedComment);
        }
        
        function saveComment(comment){
        	comment.createdDate = new Date();
        	CommentService.saveComment(comment).then(function(response){
                init();
            }, function(error){

            })
           
        }
        
        function getComments(id){
        	WatchlistService.getWatchlist(id).then(function(response){
        		vm.selectedWatchlist = response;
        	}).then(function(){
        		vm.comments = vm.selectedWatchlist.comment;
        	})        	
        }
        
      //Get all comments
        function handleSuccessComment(data, status) {
            vm.comments = data;
        }
        
        function deleteComment(id){
        	CommentService.deleteComment(id).then(function(){
                getComments(vm.selectedWatchlist.id);                
            }, function(error){

            });
            vm.comment = {};
        }
    };
})();