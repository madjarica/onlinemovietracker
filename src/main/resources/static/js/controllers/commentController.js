(function () {
    angular.module('app')
        .controller('CommentController', CommentController);

    CommentController.$inject = ['$location', 'CommentService', '$filter', '$rootScope', 'WatchlistService', 'AuthenticationService', 'NotificationService', ];

    function CommentController($location, CommentService, $filter, $rootScope, WatchlistService, AuthenticationService, NotificationService) {

        var vm = this;
        vm.addComment = addComment;
        vm.selectComment = selectComment;
        vm.saveComment = saveComment;
        vm.deleteComment = deleteComment;
        vm.selectCommentToReport = selectCommentToReport;
        vm.addCommentWatchlistCollection = addCommentWatchlistCollection;
        vm.commentForm;
        vm.importError = "";
        vm.commentContent;
        vm.selectedWatchlist = WatchlistService.selectedWatchlist;
        vm.numberOfComments = WatchlistService.selectedWatchlist.numberOfComments;
        console.log(vm.numberOfComments);
        vm.notification = {};
        vm.comments = vm.selectedWatchlist.comment;
        vm.comment = {};
        vm.username = AuthenticationService.currentUsername;
        vm.roles = {};
        vm.roles = AuthenticationService.curentUserRoles;
        console.log(vm.roles);
        vm.commentUser = {};
        vm.selectedComment = {};
        vm.newMessage = {};
        vm.watchlistCollection = WatchlistService.selectedCollection;


        init();

        function init() {
            getComments(vm.selectedWatchlist.id);
            getUserComment(vm.username);

            if ($location.path() === '/watchlist') {
                WatchlistService.getUserWatchlistCollection(WatchlistService.userOfCollection).then(function (response) {
                    vm.watchlistCollection = response;
                }).then(function () {
                    getCommentsWatchlistCollection(vm.watchlistCollection.id);
                });
            }
        }

        function getUserComment(username) {
            CommentService.getUserComment(username).then(function (response) {
                vm.userComment = response;
                CommentService.userComment = response;
            });
        }

        function addComment(commentContent) {
            vm.comment.commentContent = commentContent;
            vm.comment.commentUser= vm.username;
            vm.comment.createdDate = new Date();
            console.log(vm.comment);
            vm.comment.id = null;
            CommentService.saveComment(vm.comment)
                .then(function (response) {
                    vm.comment = response;
                    vm.selectedWatchlist.comment.push(response);
                })
                .then(function () {
                	console.log(vm.selectedWatchlist);
                WatchlistService.saveWatchlist(vm.selectedWatchlist).then(function (response) {
                    WatchlistService.selectedWatchlist = response;
                    vm.selectedWatchlist = WatchlistService.selectedWatchlist;
                    vm.comments = vm.selectedWatchlist.comment;
                    vm.commentContent = "";

                    //Notification for comment
                    vm.notification.sender = vm.username;
                    vm.notification.reciver = vm.selectedWatchlist.watchlistUser;
                    vm.notification.read = false;
                    vm.notification.trashed = false;
                    vm.notification.watchlist = vm.selectedWatchlist;
                    vm.notification.type = "notification_comment";
                    console.log(vm.comment.id);
                    vm.notification.comment = vm.comment.id;
                    NotificationService.saveNotification(vm.notification).then(function (response) {
                    });
                });
                vm.numberOfComments++;
                console.log(vm.numberOfComments);
                vm.selectedWatchlist.numberOfComments = vm.numberOfComments;
                console.log(vm.selectedWatchlist.numberOfComments);
            });      
        }

        function selectComment(comment) {
            vm.selectedComment = angular.copy(comment);
        }

        function saveComment(comment) {
            comment.createdDate = new Date();
            CommentService.saveComment(comment).then(function (response) {
                init();
            }, function (error) {

            })

        }

        function getComments(id) {
            WatchlistService.getWatchlist(id).then(function (response) {
                vm.selectedWatchlist = response;
            }).then(function () {
                vm.comments = vm.selectedWatchlist.comment;
            })
        }

        function deleteComment(id) {
            	WatchlistService.saveWatchlist(vm.selectedWatchlist).then(function (response) {
                    WatchlistService.selectedWatchlist = response;
                    vm.selectedWatchlist = WatchlistService.selectedWatchlist;
                    console.log(vm.selectedWatchlist);
                    	});
                    vm.numberOfComments--;
                    console.log(vm.numberOfComments);
                    vm.selectedWatchlist.numberOfComments = vm.numberOfComments;
                    console.log(vm.selectedWatchlist.numberOfComments);
                    CommentService.deleteComment(id).then(function () {
            	if ($location.path() === '/watchlist') {
                    WatchlistService.getUserWatchlistCollection(vm.username).then(function (response) {
                        vm.watchlistCollection = response;
                    }).then(function () {
                        getCommentsWatchlistCollection(vm.watchlistCollection.id);
                        vm.commentContent = "";
                    });
                } else {
                    getComments(vm.selectedWatchlist.id);
                }
                    });
            vm.comment = {};
        }

        function selectCommentToReport(comment) {
            vm.newMessage.comment = comment;
            vm.newMessage.readState = false;
            vm.newMessage.sentBy = vm.username;
            vm.newMessage.trashed = false;
            if ($location.path === '/watchlist') {
                vm.newMessage.watchlist = vm.selectedWatchlist;
            }
        }

        function addCommentWatchlistCollection(commentContent) {
            vm.comment.commentContent = commentContent;
            vm.comment.commentUser = vm.username;
            vm.comment.createdDate = new Date();
            vm.comment.id = null;
            CommentService.saveComment(vm.comment)
                .then(function (response) {
                    console.log(response);
                    vm.watchlistCollection.comment.push(response);
                }).then(function () {
                WatchlistService.saveWatchlistCollection(vm.watchlistCollection).then(function () {
                        console.log(vm.watchlistCollection);
                        getCommentsWatchlistCollection(vm.watchlistCollection.id);
                    }
                )
            }).then(function () {
                vm.notification.sender = vm.username;
                vm.notification.reciver = vm.watchlistCollection.username;
                vm.notification.read = false;
                vm.notification.trashed = false;
                vm.notification.type = "notification_list_comment";
                console.log(vm.comment.id);
                vm.notification.comment = vm.comment.id;
                NotificationService.saveNotification(vm.notification).then(function (response) {
                });
            });
        }

        function getCommentsWatchlistCollection(id) {
            CommentService.getCommentsWatchlistCollection(id).then(function (response) {
                vm.comments = response;
                console.log(vm.comments);
            })
        }

    }
})();