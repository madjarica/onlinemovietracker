(function(){
angular.module('app')
    .controller('CommentController', CommentController);
    
    CommentController.$inject = ['$location' ,'CommentService', '$filter', '$rootScope', 'WatchlistService', 'AuthenticationService'];
   
    function CommentController($location ,CommentService, $filter, $rootScope, WatchlistService, AuthenticationService) {
    	
    	var vm = this;
        vm.addComment = addComment;
        vm.selectComment = selectComment;
        vm.saveComment = saveComment;
        vm.commentForm;
        vm.importError = "";
        vm.selectedWatchlist = WatchlistService.selectedWatchlist;
        vm.comments = vm.selectedWatchlist.comment;
        vm.comment = {};
        console.log(vm.comment);
        vm.username = AuthenticationService.currentUsername;
        vm.userComment = {};
        
        
        init();

        function init() {
//        	getComments(vm.watchlist.id);
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
        	//vm.comment.userComment.username
        	vm.comment.createdDate = new Date();
            vm.comments.push(vm.comment);
            CommentService.saveComment(vm.comment).then(function(response){
            	getComments(vm.selectedWatchlist.id);
            	});
            	//getUserComment(vm.userComment.username)
            
        }
        
        function selectComment(comment){
            vm.selectedComment = angular.copy(comment);
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