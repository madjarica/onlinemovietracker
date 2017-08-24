(function () {
    angular.module('app')
        .controller('NotificationController', NotificationController);

    NotificationController.$inject = ['$location', '$http', '$route', 'NotificationService', 'AuthenticationService'];

    function NotificationController($location, $http, $route, NotificationService, AuthenticationService) {

        var vm = this;
        vm.username = AuthenticationService.currentUsername;
        vm.notifications = [];
        vm.number;

        getNotifications();
        setInterval(function(){
            getNotifications();
        }, 5000);

        function getNotifications() {
            NotificationService.getUserNotifications(vm.username).then(function (response) {
                if(vm.notifications.length !== response.data.length){
                    vm.notifications = response.data;
                    vm.number = vm.notifications.length;
                }
            });
        }

    }
})();