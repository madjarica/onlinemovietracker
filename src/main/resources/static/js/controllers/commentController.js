(function () {
    angular.module('app')
        .controller('CommentController', CommentController);

    CommentController.$inject = ['$location', 'CommentService', '$filter', '$rootScope', 'WatchlistService', 'AuthenticationService', 'NotificationService'];

    function CommentController($location, CommentService, $filter, $rootScope, WatchlistService, AuthenticationService, NotificationService) {

        var vm = this;
        vm.addComment = addComment;
        vm.selectComment = selectComment;
        vm.saveComment = saveComment;
        vm.deleteComment = deleteComment;
        vm.selectCommentToReport = selectCommentToReport;
        vm.commentForm;
        vm.importError = "";
        vm.commentContent;
        vm.selectedWatchlist = WatchlistService.selectedWatchlist;
        vm.notification = {};
        vm.comments = vm.selectedWatchlist.comment;
        vm.comment = {};
        console.log(vm.comment);
        vm.username = AuthenticationService.currentUsername;
        vm.userComment = {};
        vm.selectedComment = {};
        vm.newMessage = {};


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
            vm.comment.createdDate = new Date();

            CommentService.saveComment(vm.comment).then(function (response) {
                vm.watchlistCollection.comments.push(response);
                WatchlistService.saveWatchlistCollection(vm.watchlistCollection)

            }).then(function () {
                vm.commentContent = "";
                console.log(vm.commentContent);
                vm.notification.sender = vm.username;
                vm.notification.reciver = vm.selectedWatchlist.watchlistUser;
                vm.notification.read = false;
                vm.notification.watchlist = vm.selectedWatchlist;
                vm.notification.type = "notification_comment";
                NotificationService.saveNotification(vm.notification).then(function (response) {
                    console.log(response);
                });
            });
        }

        function selectComment(comment) {
            vm.selectedComment = angular.copy(comment);
            console.log(vm.selectedComment);
        }

        function saveComment(comment) {
            comment.createdDate = new Date();
            CommentService.saveComment(comment).then(function (response) {
                init();
            });
        }

        function getComments(id) {
            WatchlistService.getWatchlist(id).then(function (response) {
                vm.selectedWatchlist = response;
            }).then(function () {
                vm.comments = vm.selectedWatchlist.comment;
            })
        }

        function deleteComment(id) {
            CommentService.deleteComment(id).then(function () {
                getComments(vm.selectedWatchlist.id);
            }, function (error) {

            });
            vm.comment = {};
        }

        function selectCommentToReport(comment) {
            vm.newMessage.comment = comment;
            vm.newMessage.watchlist = vm.selectedWatchlist;
            vm.newMessage.readState = false;
            vm.newMessage.sentBy = vm.username;
            console.log(vm.newMessage);
        }
    }
})();