(function () {
    angular.module('app')
        .controller('NotificationController', NotificationController);

    NotificationController.$inject = ['$location', '$http', '$route', 'NotificationService', 'AuthenticationService', 'WatchlistService', 'MovieService', 'webNotification'];

    function NotificationController($location, $http, $route, NotificationService, AuthenticationService, WatchlistService, MovieService, webNotification) {

        var vm = this;
        vm.markAsRead = markAsRead;
        vm.trash = trash;
        vm.deleteNotification = deleteNotification;
        vm.goToComment = goToComment;
        vm.username = AuthenticationService.currentUsername;
        vm.notifications = NotificationService.notifications;
        vm.number = NotificationService.number;

        getNotifications();

        setInterval(function () {
            getNotifications();
        }, 5000);



        function getNotifications() {
            if (AuthenticationService.currentUsername !== null) {
                NotificationService.getUserNotifications(AuthenticationService.currentUsername).then(function (response) {
                    vm.number = NotificationService.number;
                    if (vm.notifications.length !== response.data.length) {
                        vm.notifications = response.data;
                        NotificationService.notifications = vm.notifications;
                        vm.number = 0;
                        for (var i = 0; i < vm.notifications.length; i++) {
                            if (vm.notifications[i].read === false) {
                                vm.number++;
                            }
                        }
                        NotificationService.number = vm.number;

                        // // Google chrome notifications, maybe for implementation
                        // webNotification.showNotification('Example Notification', {
                        //     body: 'Notification Text...',
                        //     icon: 'my-icon.ico',
                        //     onClick: function onNotificationClicked() {
                        //         console.log('Notification clicked.');
                        //     },
                        //     autoClose: 4000 //auto close the notification after 4 seconds (you can manually close it via hide function)
                        // }, function onShow(error, hide) {
                        //     if (error) {
                        //         window.alert('Unable to show notification: ' + error.message);
                        //     } else {
                        //         console.log('Notification Shown.');
                        //
                        //         setTimeout(function hideNotification() {
                        //             console.log('Hiding notification....');
                        //             hide(); //manually close the notification (you can skip this if you use the autoClose option)
                        //         }, 5000);
                        //     }
                        // });

                    }
                });
            }
        }

        function markAsRead() {
            vm.number = 0;
            NotificationService.number = vm.number;
            for (var i = 0; i < NotificationService.notifications.length; i++) {
                if (NotificationService.notifications[i].read === false) {
                    NotificationService.notifications[i].read = true;
                    NotificationService.saveNotification(NotificationService.notifications[i]).then(function (response) {
                    });
                }
            }
        }

        function trash(notification) {
            notification.trashed = true;
            NotificationService.saveNotification(notification).then(function (response) {
                console.log(response);
            })
        }

        function deleteNotification(notification) {
            NotificationService.deleteNotification(notification.id).then(function () {
                getNotifications();
            })
        }

        function goToComment(notify) {
            WatchlistService.selectedWatchlist = notify.watchlist;
            MovieService.movieDetails = notify.watchlist.video;
            $location.url('/movie-details');
            // $location.hash('comment' +
            if ($location.path() === '/movie-details') {
                setTimeout(function () {
                    console.log(document.getElementById('comment' + notify.comment) );
                    document.getElementById('about-tab').classList.remove('active');
                    document.getElementById('about').setAttribute('aria-expanded', 'false');
                    document.getElementById('about').classList.remove('active');
                    document.getElementById('comments-tab').classList.add('active');
                    document.getElementById('comments').setAttribute('aria-expanded', 'true');
                    document.getElementById('comments').classList.add('active');
                    document.getElementById('comments').classList.add('in');
                    document.getElementById('comment' + notify.comment).setAttribute('style', 'background-color: #BFEFFF; transition: background-color 1s linear;')
                    document.getElementById('comment' + notify.comment).scrollIntoView();
                }, 250)
            }


        }

    }
})();