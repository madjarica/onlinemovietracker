(function () {
    angular.module('app')
        .controller('AdminMessageController', AdminMessageController);

    AdminMessageController.$inject = ['AdminMessageService', 'NotificationService', 'AuthenticationService'];

    function AdminMessageController(AdminMessageService, NotificationService, AuthenticationService) {

        var vm = this;
        // vm.selectCommentToReport = selectCommentToReport;
        vm.saveAdminMessage = saveAdminMessage;
        vm.postReply = postReply;
        vm.adminMessages = [];
        vm.newMessage = {};
        vm.username = AuthenticationService.currentUsername;
        vm.reply  = {};
        vm.notification = {};

        getAdminMessages();

        function getAdminMessages() {
            AdminMessageService.getAdminMessages().then(function (response) {
                vm.adminMessages = response;
                console.log(response);
            })
        }

        
        function saveAdminMessage(adminMessage) {
            console.log(adminMessage);
            AdminMessageService.saveAdminMessage(adminMessage).then(function (response) {
                getAdminMessages();
            })
        }
        
        function postReply(reply, adminMessage) {

            vm.notification.type = "notification_admin";
            vm.notification.sender = vm.username;
            vm.notification.reciver = adminMessage.sentBy;
            vm.notification.message = reply;
            vm.notification.read = false;
            NotificationService.saveNotification(vm.notification).then(function (response) {
                console.log(response);
            })
        }
    }

})();