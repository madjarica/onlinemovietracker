(function () {
    angular.module('app')
        .controller('NotificationController', NotificationController);

    NotificationController.$inject = ['$location', '$http', '$route', 'NotificationService', 'AuthenticationService'];

    function NotificationController($location, $http, $route, NotificationService, AuthenticationService) {

        var vm = this;
        vm.markAsRead = markAsRead;
        vm.username = AuthenticationService.currentUsername;
        vm.notifications = NotificationService.notifications;
        vm.number = NotificationService.number;


        getNotifications();
        setInterval(function () {
            getNotifications();
        }, 5000);


        function getNotifications() {
            if (AuthenticationService.currentUsername !== null) {
                NotificationService.getUserNotifications(vm.username).then(function (response) {
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
                        console.log(response);
                    });
                }
            }
        }

    }
})();